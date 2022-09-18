package com.nasa.gallery.mobile.presentation.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nasa.gallery.mobile.data.model.SpaceImage
import com.nasa.gallery.mobile.domain.explore.GetSpaceImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpaceImageDetailViewModel
@Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getSpaceImagesUseCase: GetSpaceImagesUseCase
) : ViewModel() {

    private val initialImageUrl = savedStateHandle.get<String>(SpaceImage::class.java.simpleName)
    private val _state = MutableStateFlow<DetailViewState>(
        DetailViewState.Loading
    )

    val state : StateFlow<DetailViewState> = _state

    init {
        viewModelScope.launch {
            _state.value = initialImageUrl?.let {
                DetailViewState.InitialImage(
                    it
                )
            }?:run{
                DetailViewState.Error("Image is not selected")
            }
        }
    }

    fun loadSpaceImages(){
        viewModelScope.launch {
            getSpaceImagesUseCase().collect {
                when(it){
                    is com.nasa.gallery.mobile.result.Result.Loading ->{
                        _state.value = DetailViewState.Loading
                    }
                    is com.nasa.gallery.mobile.result.Result.Error ->{
                        _state.value = DetailViewState.Error(it.exception.localizedMessage ?: "Unexpected error occurred")
                    }
                    is com.nasa.gallery.mobile.result.Result.Success ->{
                        val index : Int = it.data.indexOfFirst { it.url == initialImageUrl}.let{ index ->
                            if(index < 0) 0 else index
                        }
                        _state.value = DetailViewState.Content(it.data, index)
                    }
                }
            }
        }
    }

    sealed class DetailViewState {
        data class Content(val data: List<SpaceImage>, val currentIndex: Int) : DetailViewState()
        data class InitialImage(val url: String) : DetailViewState()
        data class Error(val message: String) : DetailViewState()
        object Loading : DetailViewState()

    }
}