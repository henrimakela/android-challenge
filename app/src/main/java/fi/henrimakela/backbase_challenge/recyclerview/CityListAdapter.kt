package fi.henrimakela.backbase_challenge.recyclerview
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fi.henrimakela.backbase_challenge.R
import fi.henrimakela.backbase_challenge.data_classes.City


class CityListAdapter(var cityList: List<City>) : RecyclerView.Adapter<CityViewHolder>() {

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

    }

    fun setCities(cities: List<City>){
        this.cityList = cities
        notifyDataSetChanged()
    }
}


