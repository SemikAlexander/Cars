package alexander.test.cars.ui.home

import alexander.test.cars.R
import alexander.test.cars.databinding.FragmentHomeBinding
import alexander.test.cars.ui.adapters.CarAdapter
import alexander.test.cars.ui.extentions.*
import alexander.test.cars.ui.main.MainActivity
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment : ViewBindingFragment<FragmentHomeBinding>() {
    private val viewModel: HomeViewModel by viewModels()

    private val carsAdapter by lazy(LazyThreadSafetyMode.PUBLICATION) {
        CarAdapter(
            onClickItem = {

            },
            onPhotoClick = {
                (requireActivity() as MainActivity).setPhotoGallery(it.carPhotos)
            },
            onDeleteClickItem = {
                viewModel.deleteCar(it)
            }
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            setupRecyclerView()

            viewModel.totalNumOfCars.onEach {
                tvTotalCars.text = requireContext().getString(R.string.total_cars, it)
                carsAdapter.refresh()
            }.launchIn(lifecycleScope)

            ivAddCar.onClickDebounce {
                findNavController().navigate(R.id.action_homeFragment_to_addCarFragment)
            }

            ivAddCar.setOnLongClickListener {
                viewModel.addTestCar()
                true
            }
        }
    }

    private fun setupRecyclerView() {
        binding.apply {
            runCatching {
                viewModel.carsList.onEach {
                    carsAdapter.submitData(it)
                }.launchIn(lifecycleScope)
            }

            rvCars.run {
                adapter = carsAdapter
            }

            carsAdapter.addLoadStateListener {
                when (it.refresh) {
                    LoadState.Loading -> progressBar.visible()
                    else -> progressBar.gone()
                }
            }
        }
    }

    override fun makeBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)
}