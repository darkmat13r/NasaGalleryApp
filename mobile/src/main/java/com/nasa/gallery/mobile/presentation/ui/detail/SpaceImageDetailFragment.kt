package com.nasa.gallery.mobile.presentation.ui.detail
import android.graphics.drawable.Drawable
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
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.nasa.gallery.mobile.R
import com.nasa.gallery.mobile.data.model.SpaceImage
import com.nasa.gallery.mobile.databinding.FragmentSpaceImageDetailBinding
import com.nasa.gallery.mobile.presentation.ui.explore.ExploreAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
        binding.initialImage.transitionName = viewModel.initialImage
        postponeEnterTransition()
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect{
                binding.state = it
                if(it is SpaceImageDetailViewModel.DetailViewState.InitialImage){
                    loadImage(it.url)

                }else{
                    startPostponedEnterTransition()
                }
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

    private fun loadImage(url : String) {
        Glide.with(this)
            .load(url)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {

                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {

                    return false
                }
            })
            .into(binding.initialImage)

        //It's hack to perform the transition as the listeners for glide are not working.
        //This need to called after the image is loaded
        CoroutineScope(Dispatchers.Main).launch {
            delay(500)
            viewModel.loadSpaceImages()
            startPostponedEnterTransition()
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
