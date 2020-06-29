package fi.henrimakela.backbase_challenge



import fi.henrimakela.backbase_challenge.data_classes.City
import fi.henrimakela.backbase_challenge.data_classes.Coordinate
import fi.henrimakela.backbase_challenge.util.ListFilterer
import org.hamcrest.CoreMatchers.*

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers


import org.junit.Test



class ListFilterTest {

    var cityList = mutableListOf(
        City("UA", "Laspi", 4363523, Coordinate(65.0232, 25.0503)),
        City("RU", "Vinogradova", 436352656, Coordinate(65.0232, 25.0503)),
        City("IQ", "Qarah Gawl", 436354723, Coordinate(65.0232, 25.0503)),
        City("Fi", "Oulun Lääni", 4363, Coordinate(65.0232, 25.0503)),
        City("Fi", "Oulainen", 43993523, Coordinate(65.0232, 25.0503)),
        City("FI", "Oulu", 9963523, Coordinate(65.0232, 25.0503))
    ).sortedWith(compareBy {it.name})

    @Test
    fun `should return full list of cities when search word is empty`(){
        var filteredList = ListFilterer.filterResults(ArrayList(cityList), "")
        assertThat(filteredList, `is`(cityList))
    }

    @Test
    fun `should return list with city names starting with the search word`(){
        var filteredList = ListFilterer.filterResults(ArrayList(cityList), "oul")

        assertThat(filteredList, Matchers.hasItem(Matchers.hasProperty("name", `is`("Oulu"))))
        assertThat(filteredList, Matchers.hasItem(Matchers.hasProperty("name", `is`("Oulainen"))))
        assertThat(filteredList, Matchers.hasItem(Matchers.hasProperty("name", `is`("Oulun Lääni"))))

    }

    @Test
    fun `should ignore empty spaces in front of the search word`(){
        var filteredList = ListFilterer.filterResults(ArrayList(cityList), "  oulu")
        assertThat(filteredList, Matchers.hasItem(Matchers.hasProperty("name", `is`("Oulu"))))
    }

    @Test
    fun `should ignore case of the search word`(){
        var filteredList = ListFilterer.filterResults(ArrayList(cityList), "OULU")
        assertThat(filteredList, Matchers.hasItem(Matchers.hasProperty("name", `is`("Oulu"))))
    }


}