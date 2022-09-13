package com.nasa.gallery.mobile.presentation.ui.explore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.nasa.gallery.mobile.data.model.SpaceImage
import com.nasa.gallery.mobile.databinding.FragmentExploreBinding
import com.nasa.gallery.mobile.result.succeeded
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment : Fragment() {

    companion object {
        fun newInstance() = ExploreFragment()
    }

    private val viewModel: ExploreViewModel by viewModels<ExploreViewModel>()
    private lateinit var binding: FragmentExploreBinding
    private lateinit var mExploreAdapter: ExploreAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mExploreAdapter = ExploreAdapter()

    }

    @BindingAdapter("spaceImages")
    fun bindSpaceImages( recyclerView: RecyclerView, spaceImages : List<SpaceImage>){
        recyclerView.apply {
            adapter =  mExploreAdapter
            mExploreAdapter.setItems(spaceImages)

        }
    }
}

