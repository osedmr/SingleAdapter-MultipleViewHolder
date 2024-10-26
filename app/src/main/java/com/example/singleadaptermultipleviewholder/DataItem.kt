package com.example.singleadaptermultipleviewholder

sealed class DataItem {
    data class Item(val id: Int, val name: String) : DataItem()
    data class Header(val name: String) : DataItem()

}