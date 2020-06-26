package fi.henrimakela.backbase_challenge.repository

import android.util.Log

class CityRepository {

    private val cityList: List<String> = mutableListOf()
    private val REPOSITORY_TAG = "CityRepository"

    fun loadCities(){
        Log.d(REPOSITORY_TAG, "Loading cities...")
    }
}