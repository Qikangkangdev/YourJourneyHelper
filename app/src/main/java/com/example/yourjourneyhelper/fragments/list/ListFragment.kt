package com.example.yourjourneyhelper.fragments.list

import android.os.Bundle
import android.view.*
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.yourjourneyhelper.R
import com.example.yourjourneyhelper.data.models.Tickets
import com.example.yourjourneyhelper.databinding.FragmentListBinding
import com.example.yourjourneyhelper.fragments.list.adapter.ListAdapter
import com.example.yourjourneyhelper.viewmodel.SharedViewModel
import com.example.yourjourneyhelper.viewmodel.TicketsViewModel

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null

    private val binding: FragmentListBinding
        get() = _binding!!

    private val adapter: ListAdapter by lazy {
        ListAdapter()
    }

    private lateinit var drawerLayout: androidx.drawerlayout.widget.DrawerLayout

    private val mTicketsViewModel: TicketsViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        mTicketsViewModel.getAllData.observe(viewLifecycleOwner) {
            mSharedViewModel.checkIfDatabaseEmpty(it)
            adapter.setData(it)
        }
        mSharedViewModel.emptyDataBase.observe(viewLifecycleOwner) {
            showEmptyDatabaseViews(it)
        }

        binding.fab.setOnClickListener {
            findNavController().navigate((R.id.action_listFragment_to_addFragment))
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        drawerLayout = requireActivity().findViewById(R.id.drawerLayout)
        if (item.itemId == R.id.callDrawerLayout) {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showEmptyDatabaseViews(emptyDatabase: Boolean) {
        if (emptyDatabase) {
            binding.noDataImageView.visibility = View.VISIBLE
            binding.noDataTextView.visibility = View.VISIBLE
        } else {
            binding.noDataImageView.visibility = View.INVISIBLE
            binding.noDataTextView.visibility = View.INVISIBLE
        }

    }
}