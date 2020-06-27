package fi.henrimakela.backbase_challenge.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import fi.henrimakela.backbase_challenge.R
import fi.henrimakela.backbase_challenge.view_models.CityListViewModel

/**
 * A simple [Fragment] subclass.
 */
class MapFragment : Fragment(), OnMapReadyCallback {

    private var mMap: GoogleMap? = null
    private lateinit var viewModel: CityListViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val supportMapFragment =
            childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        supportMapFragment?.getMapAsync(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(findNavController().getViewModelStoreOwner(R.id.app_nav_graph)).get(CityListViewModel::class.java)


    }

    override fun onMapReady(map: GoogleMap?) {
        mMap = map
        println("Map Fragment map ready")

        viewModel.selectedCity.observe(viewLifecycleOwner,  Observer {
            println("Map Fragment Selected city: ${it.name}")
            mMap?.addMarker(
                MarkerOptions().position(LatLng(it.coord.lat, it.coord.lon))
                    .title(it.name).icon(
                        BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_RED)
                    ))

            mMap?.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(
                        it.coord.lat,
                        it.coord.lon
                    ), 8f
                )
            )
        })

    }

}
