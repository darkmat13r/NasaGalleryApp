package com.nasa.gallery.mobile.presentation.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.nasa.gallery.mobile.R
import com.nasa.gallery.mobile.data.model.SpaceImage
import com.nasa.gallery.mobile.databinding.FragmentExploreBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment : Fragment() {

    companion object {
        private val TAG = ExploreFragment::class.java.simpleName
        fun newInstance() = ExploreFragment()
    }

    private val viewModel: ExploreViewModel by viewModels<ExploreViewModel>()
    private lateinit var binding: FragmentExploreBinding
    private lateinit var mExploreAdapter: ExploreAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExploreBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mExploreAdapter = ExploreAdapter().apply {
            onSelect = { spaceImage, imageView ->
                findNavController().navigate(R.id.nav_space_detail_fragment, bundleOf( SpaceImage::class.java.simpleName to spaceImage.url), null, FragmentNavigatorExtras(
                    imageView to getString(R.string.transition_space_image)
                ))
            }
        }
        binding.adapter = mExploreAdapter
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                binding.state = it
            }
        }
    }
}

@BindingAdapter("spaceImages", "exploreAdapter")
fun bindSpaceImages(
    recyclerView: RecyclerView,
    spaceImages: List<SpaceImage>?,
    exploreAdapter: ExploreAdapter?
) {
    recyclerView.apply {
        adapter = exploreAdapter
        exploreAdapter?.setItems(spaceImages ?: emptyList())
    }
}

