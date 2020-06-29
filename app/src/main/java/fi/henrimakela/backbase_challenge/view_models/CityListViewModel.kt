package fi.henrimakela.backbase_challenge.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fi.henrimakela.backbase_challenge.data_classes.City
import fi.henrimakela.backbase_challenge.repository.CityRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.java.KoinJavaComponent.inject

class CityListViewModel : ViewModel(), KoinComponent {

    var selectedCity : MutableLiveData<City> = MutableLiveData()
    private var _cityList : MutableLiveData<ArrayList<City>> = MutableLiveData()
    private var _isLoadingCities : MutableLiveData<Boolean> = MutableLiveData()

    private val repository: CityRepository by inject()

    val cityList: LiveData<ArrayList<City>> get() = _cityList
    val isLoadingCities: LiveData<Boolean> get() =  _isLoadingCities

    init {
        GlobalScope.launch {
            getCities()
        }
    }

    private fun getCities(){
            _isLoadingCities.postValue(true)
            val cities = repository.loadCities()
            _cityList.postValue(cities)
            _isLoadingCities.postValue(false)
    }

    fun setSelectedCity(city: City){
        selectedCity.value = city
    }

}