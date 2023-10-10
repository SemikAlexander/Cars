package alexander.test.cars.ui.add_car

import alexander.test.cars.databinding.FragmentAddCarBinding
import alexander.test.cars.ui.extentions.ViewBindingFragment
import alexander.test.cars.ui.extentions.setMask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCarFragment : ViewBindingFragment<FragmentAddCarBinding>() {
    private val viewModel: AddCarViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        binding.apply {
            etPrice.doOnTextChanged { text, _, _, _ ->
                if (!text.isNullOrEmpty()) {
                    if (etPrice.text.toString() != "") {
                        setMask(etPrice)
                    }
                }
            }

            etMillage.doOnTextChanged { text, _, _, _ ->
                if (!text.isNullOrEmpty()) {
                    if (etMillage.text.toString() != "") {
                        setMask(etMillage)
                    }
                }
            }
        }
    }

    override fun makeBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAddCarBinding.inflate(inflater, container, false)
}