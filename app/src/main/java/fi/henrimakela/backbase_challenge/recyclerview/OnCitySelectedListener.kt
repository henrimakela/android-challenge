package fi.henrimakela.backbase_challenge.recyclerview

import fi.henrimakela.backbase_challenge.data_classes.City

interface OnCitySelectedListener {
    fun OnCitySelected(city: City)
}