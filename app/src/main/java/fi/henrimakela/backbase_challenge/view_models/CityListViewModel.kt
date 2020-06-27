package fi.henrimakela.backbase_challenge.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fi.henrimakela.backbase_challenge.data_classes.City
import fi.henrimakela.backbase_challenge.repository.CityRepository
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.java.KoinJavaComponent.inject

class CityListViewModel : ViewModel(), KoinComponent {

    private var _cityList : MutableLiveData<ArrayList<City>> = MutableLiveData()
    private val repository: CityRepository by inject()
    val cityList: LiveData<ArrayList<City>> get() = _cityList


    init {
        getCities()
    }

    private fun getCities(){
        val cities = repository.loadCities()
        _cityList.postValue(cities)
    }

}