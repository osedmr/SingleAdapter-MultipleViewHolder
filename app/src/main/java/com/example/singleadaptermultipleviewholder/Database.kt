package com.example.singleadaptermultipleviewholder

object Database {
    const val VIEW_TYPE_ITEM =1
    const val VIEW_TYPE_HEADER =2


    fun getItems() : ArrayList<DataItem>{
        val totalList = ArrayList<DataItem>()


        val itemList = (1 .. 100).map {
            DataItem.Item(it, "Item $it")

        }
        val headerList = listOf("A", "B", "C", "D", "E").map {
            DataItem.Header("Header $it")
        }

        val itemsPerHeader = itemList.size / headerList.size

        for ((headerIndex, header) in headerList.withIndex()){
            totalList.add(header)
            val startIndex = headerIndex * itemsPerHeader
            val endIndex = if (headerIndex == headerList.size - 1) itemList.size else (headerIndex + 1) * itemsPerHeader
            totalList.addAll(itemList.subList(startIndex, endIndex))
        }

        return totalList
    }
}