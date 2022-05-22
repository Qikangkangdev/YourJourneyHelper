package com.example.yourjourneyhelper.fragments.add

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.yourjourneyhelper.R
import com.example.yourjourneyhelper.data.models.Tickets
import com.example.yourjourneyhelper.databinding.FragmentAddBinding
import com.example.yourjourneyhelper.viewmodel.SharedViewModel
import com.example.yourjourneyhelper.viewmodel.TicketsViewModel
import java.lang.invoke.MethodHandle

class AddFragment : Fragment() {

    private var _binding:FragmentAddBinding? = null
    private val binding:FragmentAddBinding
    get() = _binding!!

    private val mTicketsViewModel: TicketsViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        binding.propertiesSpinner.onItemSelectedListener = mSharedViewModel.listener
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_add){
            insertDataToDb()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDb(){
        val mTitle = binding.titleEt.text.toString()
        val mMeans = binding.propertiesSpinner.selectedItem.toString()
        val mPrices = binding.pricesEt.text.toString()
        val mHours = binding.hoursEt.text.toString()
        val mStart = binding.startEt.text.toString()

        val validation = mSharedViewModel.verifyDataFromUser(mTitle, mMeans, mPrices)
        if(validation){
            val newData = Tickets(
                0,
                mTitle,
                mPrices,
                mSharedViewModel.parseMeans(mMeans),
                mHours,
                mStart
            )
            mTicketsViewModel.insertData(newData)
            Toast.makeText(requireContext(), "Data is added.", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else {
            Toast.makeText(requireContext(), "Data is not valid", Toast.LENGTH_SHORT).show()
        }
    }


}