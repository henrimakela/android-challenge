package fi.henrimakela.backbase_challenge.util

import android.util.Log
import fi.henrimakela.backbase_challenge.data_classes.City
import java.util.concurrent.TimeUnit

class ListFilterer {

    companion object{
        fun filterResults(list : ArrayList<City>, constraint: String): ArrayList<City>{

            val startTime = System.nanoTime()
            val searchWord = constraint.trim()

            if(searchWord.isNullOrEmpty()){
                return list
            }
            var filteredList: ArrayList<City> = arrayListOf()


            //first, binary search and return the first object that matches with the searchword
            var index = binarySearch(list, searchWord)


            //then go backwards and forwards from the found index until there's no more matching objects
            if (index > -1){
                var bIndex = index;
                var fIndex = index;

                //go backwards on the list
                while(bIndex > 0){
                    if(list[bIndex].name.startsWith(searchWord, true)){
                        filteredList.add(list[bIndex])
                        bIndex--
                    }
                    else{
                        break
                    }
                }
                // go forward on the list
                while(fIndex < list.size){
                    if(list[fIndex].name.startsWith(searchWord, true)){
                        filteredList.add(list[fIndex])
                        fIndex++
                    }
                    else{
                        break
                    }
                }

            }

            val endTime = System.nanoTime()

            // log the execution time
            println("Binary search execution time: ${TimeUnit.MILLISECONDS.convert(endTime -
                    startTime, TimeUnit.NANOSECONDS)} ms, (1)")

            return filteredList
        }

        fun filterResultsLinear(list : ArrayList<City>, constraint: String) : ArrayList<City>{
            val startTime = System.nanoTime()
            val searchWord = constraint.trim()

            if(searchWord.isNullOrEmpty()){
                return list
            }
            var filteredList: ArrayList<City> = arrayListOf()

            list.forEach {
                if(it.name.startsWith(searchWord, true)){
                    filteredList.add(it)
                }
            }
            val endTime = System.nanoTime()


            Log.d("TimingLoggerDemo","Linear search execution time: ${TimeUnit.MILLISECONDS.convert(endTime -
                    startTime, TimeUnit.NANOSECONDS)} ms, (1)")
            return filteredList
        }


        private fun binarySearch(list: List<City>, searchWord: String): Int{

            // Needs to be a sorted list. First look from the middle of the list. If the object in the middle starts with the search word, return it.
            // Otherwise check if the value is lower or higher and start searching either from the middle of the lower or the higher part.
            // Repeat until the object is found

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


}