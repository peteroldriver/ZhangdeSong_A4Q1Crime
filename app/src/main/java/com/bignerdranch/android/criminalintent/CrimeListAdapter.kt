package com.bignerdranch.android.criminalintent

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bignerdranch.android.criminalintent.databinding.ListItemCrimeBinding
import com.bignerdranch.android.criminalintent.databinding.MoreSeriousListItemCrimeBinding
import kotlin.random.Random

class Layout1CrimeHolder(
    private val binding: ListItemCrimeBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

class Layout2CrimeHolder(
    private val binding: MoreSeriousListItemCrimeBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(crime: Crime) {
        binding.crimeTitle2.text = crime.title
        binding.crimeDate2.text = crime.date.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.callPoliceButton.setOnClickListener {
            Toast.makeText(binding.callPoliceButton.context, "Info Send to Police", Toast.LENGTH_SHORT).show()
        }
    }
}

class CrimeListAdapter(
    private val crimes: List<Crime>
) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("TAG", "viewType: $viewType")
        if(viewType == 1){
            val inflater = LayoutInflater.from(parent.context)
            val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
            return Layout1CrimeHolder(binding)
        }
        else{
            val inflater = LayoutInflater.from(parent.context)
            val binding = MoreSeriousListItemCrimeBinding.inflate(inflater, parent, false)
            return Layout2CrimeHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val crime = crimes[position]
        if(crime.requiePolice == 1){
            (holder as Layout1CrimeHolder).bind(crime)
        }
        else{
            (holder as Layout2CrimeHolder).bind(crime)
        }
    }

    override fun getItemCount() = crimes.size

    override fun getItemViewType(position: Int): Int {
        //return super.getItemViewType(position)
        return crimes.get(position).requiePolice
    }
}
