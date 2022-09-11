package com.nasa.mobile.ui.explore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.nasa.gallery.mobile.databinding.FragmentExploreBinding

class ExploreFragment : Fragment() {

    companion object {
        fun newInstance() = ExploreFragment()
    }

    private val viewModel: ExploreViewModel by viewModels<ExploreViewModel>()
    private lateinit var binding: FragmentExploreBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.list.apply {
            adapter = ExploreAdapter()
        }


    }



}