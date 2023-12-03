package com.example.davaleba14

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.davaleba14.databinding.Item2RecyclerviewBinding
import com.example.davaleba14.databinding.ItemRecyclerviewBinding

class ItemRecyclerAdapter(val listener:Listener):androidx.recyclerview.widget.ListAdapter<Data, RecyclerView.ViewHolder>(object :
    DiffUtil.ItemCallback<Data>() {
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
      return oldItem.id == newItem.id

    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem == newItem
    }

}) {

    interface Listener{
        fun deleteItem(item: Data)
    }

    companion object{
        const val CAR = 1
        const val BIKE = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  RecyclerView.ViewHolder {
        return if(viewType == CAR){
            CarViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }else {
            BikeViewHolder(Item2RecyclerviewBinding.inflate(LayoutInflater.from(parent.context),parent, false))
        }
    }

    override fun onBindViewHolder(holder:  RecyclerView.ViewHolder, position: Int) {
        if(holder is CarViewHolder){
            holder.bind()
        }else if (holder is BikeViewHolder){
            holder.bind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(currentList[position].itemType == ItemType.CAR){
            CAR
        }else
            BIKE
    }

    inner class CarViewHolder(private val binding: ItemRecyclerviewBinding):RecyclerView.ViewHolder(binding.root){

        fun bind() {
            val item = currentList[adapterPosition]
            binding.ivMoto.setImageResource(item.icon)
            binding.tvTitle.text = item.title
            binding.ivDelete.setOnClickListener{
                listener.deleteItem(item)
            }
        }

    }

    inner class BikeViewHolder(private val binding: Item2RecyclerviewBinding):RecyclerView.ViewHolder(binding.root){
        fun bind() {
           val item = currentList[adapterPosition]
            binding.ivCar.setImageResource(item.icon)
            binding.tvTitle.text = item.title
            binding.ivDelete.setOnClickListener{
                listener.deleteItem(item)
            }
        }
    }
}