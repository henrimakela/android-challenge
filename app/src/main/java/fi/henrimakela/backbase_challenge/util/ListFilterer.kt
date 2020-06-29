package fi.henrimakela.backbase_challenge.util

import fi.henrimakela.backbase_challenge.data_classes.City

class ListFilterer {

    companion object{
        fun filterResults(list : ArrayList<City>, constraint: String): ArrayList<City>{

            val searchWord = constraint.trim()

            if(searchWord.isNullOrEmpty()){
                return list
            }
            var filteredList: ArrayList<City> = arrayListOf()

            /*//linear approach
            list.forEach {
                if(it.name.startsWith(searchWord, true)){
                    filteredList.add(it)
                }
            }*/


            //non-linear approach
            var index = binarySearch(list, searchWord)

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

            return filteredList
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


}