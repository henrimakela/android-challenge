package fi.henrimakela.backbase_challenge.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import fi.henrimakela.backbase_challenge.R
import fi.henrimakela.backbase_challenge.data_classes.City
import fi.henrimakela.backbase_challenge.util.ListFilterer
import java.util.*
import kotlin.collections.ArrayList


class CityListAdapter(var cityList: ArrayList<City>, val listener: OnCitySelectedListener) :
    RecyclerView.Adapter<CityViewHolder>(), Filterable {

    private var cityListFull: ArrayList<City> = ArrayList(cityList)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.city_list_item, parent, false)
        return CityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.title.text = "${cityList[position].name} ${cityList[position].country}"
        holder.subtitle.text =
            "lat: ${cityList[position].coord.lat} lon: ${cityList[position].coord.lon}"

        holder.itemView.setOnClickListener {
            listener.OnCitySelected(cityList[position])
        }

    }

    fun setCities(cities: ArrayList<City>) {
        this.cityList = cities
        this.cityListFull = ArrayList(cities)

        notifyDataSetChanged()
    }


    override fun getFilter(): Filter {
        return filter
    }

    private val filter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {


            val results = FilterResults()
            results.values = ListFilterer.filterResults(cityListFull, constraint.toString())

            return results
        }

        override fun publishResults(p0: CharSequence?, results: FilterResults?) {
            cityList.clear()
            cityList.addAll(results?.values as ArrayList<City>)
            notifyDataSetChanged()
        }

    }

}


