package com.nasa.mobile.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.core.os.bundleOf
import androidx.lifecycle.SavedStateHandle
import com.google.common.truth.Truth.assertThat
import com.nasa.gallery.mobile.data.model.SpaceImage
import com.nasa.gallery.mobile.data.repository.NasaSpaceImageRepository
import com.nasa.gallery.mobile.domain.explore.GetSpaceImagesUseCase
import com.nasa.gallery.mobile.presentation.ui.detail.SpaceImageDetailFragment
import com.nasa.gallery.mobile.presentation.ui.detail.SpaceImageDetailViewModel
import com.nasa.gallery.mobile.presentation.ui.explore.ExploreViewModel
import com.nasa.mobile.data.MainCoroutineRule
import com.nasa.mobile.data.TestData
import com.nasa.mobile.ui.explore.TestDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DetailViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private val testDataSource = TestDataSource()


    @Test
    fun testDataIsLoaded_ObservablesUpdated() = runTest {
        val viewModel = createViewModel(saveStateHandle())
        val state =
            viewModel.state.first { it is SpaceImageDetailViewModel.DetailViewState.Content } as SpaceImageDetailViewModel.DetailViewState.Content
        assertThat(state.data).isNotEmpty()
    }

    @Test
    fun testInitialImageState_ObservablesUpdated() = runTest {
        val viewModel = createViewModel(saveStateHandle())
        val state =
            viewModel.state.first()
        assertThat(state).isInstanceOf(SpaceImageDetailViewModel.DetailViewState.InitialImage::class.java)
    }

    @Test
    fun testInitialImageState_NoInitialUrlPassed() = runTest {
        val viewModel = createViewModel(emptySavedStateHandle())
        val state =
            viewModel.state.first()
        assertThat(state).isInstanceOf(SpaceImageDetailViewModel.DetailViewState.Error::class.java)
    }

    fun saveStateHandle() =  SavedStateHandle().apply {
        set(SpaceImage::class.java.simpleName, TestData.spaceImages.first().url)
    }
    fun emptySavedStateHandle() =  SavedStateHandle().apply {
    }
    fun createViewModel(savedStateHandle: SavedStateHandle) = SpaceImageDetailViewModel(
       savedStateHandle,
        GetSpaceImagesUseCase(
            NasaSpaceImageRepository(testDataSource),
            coroutineRule.testDispatcher
        )
    )
}