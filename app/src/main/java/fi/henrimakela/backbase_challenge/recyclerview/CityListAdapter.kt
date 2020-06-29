package fi.henrimakela.backbase_challenge.recyclerview
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import fi.henrimakela.backbase_challenge.R
import fi.henrimakela.backbase_challenge.data_classes.City
import java.util.*
import kotlin.collections.ArrayList


class CityListAdapter(var cityList: ArrayList<City>, val listener: OnCitySelectedListener) : RecyclerView.Adapter<CityViewHolder>(), Filterable {

    private var cityListFull: ArrayList<City> = ArrayList(cityList)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.city_list_item, parent, false)
        return CityViewHolder(view )
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

    fun setCities(cities: ArrayList<City>){
        this.cityList = cities
        this.cityListFull = ArrayList(cities)

        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return filter
    }

    private val filter :Filter = object : Filter(){
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: ArrayList<City> = ArrayList()

            if(constraint == null || constraint.isEmpty()){
                filteredList.addAll(cityListFull)
            }
            else {



                //linear approach
                /*var searchWord = constraint.toString()
                cityListFull.forEach {
                    if(it.name.startsWith(searchWord, true)){
                        filteredList.add(it)
                    }
                }

            */

                //non-linear approach
                var searchWord = constraint.toString().trim()
                var index = binarySearch(cityListFull, searchWord)

                if (index > -1){
                    var bIndex = index;
                    var fIndex = index;

                    //go backwards on the list
                    while(bIndex > 0){
                        if(cityListFull[bIndex].name.startsWith(searchWord, true)){
                            filteredList.add(cityListFull[bIndex])
                            bIndex--
                        }
                        else{
                            break
                        }
                    }
                    // go forward on the list
                    while(fIndex < cityListFull.size){
                        if(cityListFull[fIndex].name.startsWith(searchWord, true)){
                            filteredList.add(cityListFull[fIndex])
                            fIndex++
                        }
                        else{
                            break
                        }
                    }

                }
            }

            val results = FilterResults()
            results.values = filteredList

            return results
        }

        override fun publishResults(p0: CharSequence?, results: FilterResults?) {
            cityList.clear()
            cityList.addAll(results?.values as ArrayList<City>)
            notifyDataSetChanged()
        }

    }

    private fun binarySearch(list: List<City>, searchWord: String): Int{
        var low = 0
        var high = list.size - 1

        while(low <= high){
            var mid = (low + high) / 2
            if(list.get(mid).name.startsWith(searchWord, true)){
                return mid
            }
            else if(searchWord.toLowerCase() < list[mid].name.toLowerCase()){
                high = mid - 1
            }
            else{
                low = mid + 1
            }

        }

        return -1
    }


}


