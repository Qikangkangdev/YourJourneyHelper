package com.example.yourjourneyhelper.fragments.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.yourjourneyhelper.R
import com.example.yourjourneyhelper.data.models.Means
import com.example.yourjourneyhelper.data.models.Tickets
import com.example.yourjourneyhelper.databinding.RowLayoutBinding
import com.example.yourjourneyhelper.fragments.list.ListFragmentDirections


class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    var dataList = emptyList<Tickets>()

    class MyViewHolder(private val binding: RowLayoutBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind(tickets: Tickets){
                    binding.titleTxt.setText(tickets.title)
                    binding.pricesTxt.setText(tickets.price)
                    binding.hoursTxt.setText(tickets.hours)
                    binding.startTxt.setText(tickets.start)
                    when(tickets.means){
                        Means.HighSpeedRailway ->{
                            binding.meansTxt.setText("HighSpeedRailway")
                        }
                        Means.Train -> {
                            binding.meansTxt.setText("Train")
                        }
                        Means.Airplane -> {
                            binding.meansTxt.setText("Airplane")
                        }
                    }
                }

                companion object{
                    fun from(parent: ViewGroup): MyViewHolder{
                        val layoutInflater = LayoutInflater.from(parent.context)
                        val binding = RowLayoutBinding.inflate(layoutInflater, parent, false)
                        return MyViewHolder(binding)
                    }
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = dataList[position]


        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(tickets: List<Tickets>){

        val ticketsDiffUtil=TicketsDiffUtil(dataList, tickets)
        val ticketsDiffResult = DiffUtil.calculateDiff(ticketsDiffUtil)
        this.dataList= tickets
        ticketsDiffResult.dispatchUpdatesTo(this)
    }


}