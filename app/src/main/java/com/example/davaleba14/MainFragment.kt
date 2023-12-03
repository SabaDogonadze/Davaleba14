package com.example.davaleba14

import android.util.Log.d
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.davaleba14.databinding.FragmentMainBinding
import kotlinx.coroutines.launch
import kotlin.random.Random


class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate),ItemRecyclerAdapter.Listener {

    private var adapter: ItemRecyclerAdapter ?= null
    private val viewModel:ItemsViewModel by viewModels()



    override fun setUp() {
        setUpRecycler()
    }


    override fun setOnClickListeners() {
        d("ItemsViewModel1", "addNewItem called")
        binding.btnAddCar.setOnClickListener{
            viewModel.addNewItem(id = Random.nextInt(), icon = R.drawable.car, title = "Mercedes", itemType =ItemType.CAR)
        }
        binding.btnAddBike.setOnClickListener{
            viewModel.addNewItem(id = Random.nextInt(), icon = R.drawable.moto1, title = "YAMAHA", itemType =ItemType.BIKE)
        }
     }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.itemsFlow.collect {
                    adapter!!.submitList(it)
                }
            }
        }
    }


    private fun setUpRecycler() {
        adapter = ItemRecyclerAdapter(this)
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerView.adapter = adapter
        adapter!!.submitList(viewModel.items)
    }


    override fun deleteItem(item: Data) {
        /*val currentList = adapter?.currentList?.toMutableList()
        currentList?.remove(item)
        adapter?.submitList(currentList)*/
        viewModel.deleteItem(item)
    }

    override fun refreshApp(){

       binding.refresh.setOnRefreshListener {
            viewModel.clearData()
           binding.refresh.isRefreshing = false
       }

    }
    



}
