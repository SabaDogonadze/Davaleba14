package com.example.davaleba14

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ItemsViewModel : ViewModel() {

    val items = mutableListOf<Data>(
        /*Data(id = 0, icon = R.drawable.car, title = "Mercedes"),
        Data(id = 1, icon = R.drawable.car1, title = "Mercedes"),
        Data(id = 2, icon = R.drawable.moto1, title = "YAMAHA"),
        Data(id = 3, icon = R.drawable.moto2, title = "YAMAHA") ,*/

    )
    private val _itemsFlow = MutableStateFlow<List<Data>>(emptyList())
    val itemsFlow: SharedFlow<List<Data>> = _itemsFlow.asStateFlow()


    fun addNewItem(
        id: Int,
        icon: Int,
        title: String,
        itemType: ItemType = ItemType.CAR
    ) {
        viewModelScope.launch {
            d("ItemsViewModel", "addNewItem called")
            val currentList = _itemsFlow.value.toMutableList()
            currentList.add(Data(id = id, icon = icon, title = title, itemType = itemType))
            _itemsFlow.value = currentList
        }
    }

    fun clearData() {
        viewModelScope.launch {
            _itemsFlow.emit(emptyList())
        }
    }

    fun deleteItem(item: Data) {
        viewModelScope.launch {
            val currentList = _itemsFlow.value.toMutableList()
            currentList.remove(item)
            _itemsFlow.value = currentList
        }
    }



}