package fi.henrimakela.backbase_challenge.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import fi.henrimakela.backbase_challenge.R
import kotlinx.android.synthetic.main.fragment_city_list.*

/**
 * A simple [Fragment] subclass.
 */
class CityListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_city_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigate_btn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.mapFragment)
        }

    }

}
