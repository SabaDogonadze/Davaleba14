package com.example.davaleba14

data class Data(
    val id: Int,
    val icon: Int,
    val title: String,
    val itemType: ItemType = ItemType.CAR
)

enum class ItemType{
    CAR,
    BIKE
}
