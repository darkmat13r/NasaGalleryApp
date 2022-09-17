package com.nasa.gallery.mobile.presentation.ui.detail
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.nasa.gallery.mobile.R
import com.nasa.gallery.mobile.data.model.SpaceImage
import com.nasa.gallery.mobile.databinding.FragmentSpaceImageDetailBinding
import com.nasa.gallery.mobile.presentation.ui.explore.ExploreAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class SpaceImageDetailFragment : Fragment() {

    companion object {
        fun newInstance() = SpaceImageDetailFragment()
    }

    private lateinit var binding: FragmentSpaceImageDetailBinding
    private  val viewModel: SpaceImageDetailViewModel by viewModels()
    private var spaceImage : String? = null
    private lateinit var mDetailAdapter : DetailAdapter
    private val snapHelper = PagerSnapHelper()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.image_transition)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSpaceImageDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
        lifecycleScope.launchWhenStarted {
           delay(1000)
            viewModel.state.collect{
                binding.state = it
            }
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mDetailAdapter = DetailAdapter()
        binding.adapter = mDetailAdapter
        binding.list.apply {
            adapter = mDetailAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            snapHelper.attachToRecyclerView(this)
        }
    }




}
@BindingAdapter("spaceImages", "detailsAdapter", "snapTo")
fun bindSpaceImagesDetail(
    recyclerView: RecyclerView,
    spaceImages: List<SpaceImage>?,
    detailsAdapter: DetailAdapter?,
    snapToIndex : Int
) {
    recyclerView.apply {
        detailsAdapter?.setItems(spaceImages ?: emptyList())
        //SetItems run a async task to calculate diff of data. Added delay to scrollToPosition /
        postDelayed({ scrollToPosition(snapToIndex) }, 200)
    }
}
