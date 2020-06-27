package fi.henrimakela.backbase_challenge.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import fi.henrimakela.backbase_challenge.data_classes.City
import java.io.IOException
import java.lang.reflect.Type


class CityRepository(val context: Context) {


    private val cityList: List<String> = mutableListOf()
    private val REPOSITORY_TAG = "CityRepository"

    fun loadCities(): ArrayList<City> {
        var jsonString = getJsonDataFromAsset(context, "cities.json")

        val founderListType: Type = object : TypeToken<ArrayList<City?>?>() {}.type
        val gson = Gson()
        val cityList: ArrayList<City> =
            gson.fromJson(jsonString, founderListType)

        //sort to alphabetical order
        var sortedList = ArrayList<City>(cityList.sortedWith(compareBy {it.name}))
        return sortedList
    }

    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
}