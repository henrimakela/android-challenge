package fi.henrimakela.backbase_challenge.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager

import fi.henrimakela.backbase_challenge.R
import fi.henrimakela.backbase_challenge.data_classes.City
import fi.henrimakela.backbase_challenge.recyclerview.CityListAdapter
import fi.henrimakela.backbase_challenge.recyclerview.OnCitySelectedListener
import fi.henrimakela.backbase_challenge.repository.CityRepository
import fi.henrimakela.backbase_challenge.view_models.CityListViewModel
import kotlinx.android.synthetic.main.fragment_city_list.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 */
class CityListFragment : Fragment(), OnCitySelectedListener {

    private lateinit var viewModel: CityListViewModel
    private lateinit var cityListAdapter: CityListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_city_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(findNavController().getViewModelStoreOwner(R.id.app_nav_graph)).get(CityListViewModel::class.java)
        cityListAdapter = CityListAdapter(arrayListOf(), this)
        city_recycler_view.adapter = cityListAdapter
        city_recycler_view.layoutManager = LinearLayoutManager(this.context)

        viewModel.isLoadingCities.observe(viewLifecycleOwner, Observer {
            if(it){
                progress_indicator.visibility = View.VISIBLE
                progress_label.visibility = View.VISIBLE
            }
            else{
                progress_indicator.visibility = View.GONE
                progress_label.visibility = View.GONE
            }
        })

        viewModel.cityList.observe(viewLifecycleOwner, Observer {
            println(it)
            cityListAdapter.setCities(it)
        })


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.city_list_menu, menu)

        var searchItem = menu.findItem(R.id.action_search)
        val searchView: SearchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                cityListAdapter.filter.filter(newText)
                return false
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun OnCitySelected(city: City) {
        viewModel.setSelectedCity(city)

        Navigation.findNavController(this.requireView()).navigate(R.id.mapFragment)
    }

}
