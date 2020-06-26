package fi.henrimakela.backbase_challenge.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fi.henrimakela.backbase_challenge.R
import kotlinx.android.synthetic.main.city_list_item.view.*

class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.city_title)
}