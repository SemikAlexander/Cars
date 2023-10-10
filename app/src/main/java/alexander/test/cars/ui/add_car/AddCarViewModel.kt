package alexander.test.cars.ui.add_car

import alexander.test.cars.data.contollers.CarsImpl
import alexander.test.cars.data.db.data.Car
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCarViewModel @Inject constructor() : ViewModel() {
    private val carsService = CarsImpl()

    fun addCar(car: Car) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                carsService.addCar(car)
            }.onFailure {
                Log.e("ASO", it.message.toString())
            }.onSuccess {

            }
        }
    }
}