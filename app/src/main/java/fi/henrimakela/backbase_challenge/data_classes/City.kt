package fi.henrimakela.backbase_challenge.data_classes


import java.io.Serializable

data class City(val country: String, val name: String, val _id: Int, val coord: Coordinate) : Serializable


