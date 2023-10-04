package alexander.test.cars.ui.home

import alexander.test.cars.data.contollers.CarsImpl
import alexander.test.cars.data.db.data.Car
import alexander.test.cars.data.generic.GenericDataSource
import alexander.test.cars.ui.extentions.bitDepth
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    init {
        getCountOfCars()
    }

    private val carsService = CarsImpl()

    val carsList: Flow<PagingData<Car>> =
        Pager(PagingConfig(pageSize = 20)) {
            GenericDataSource(::getCarsList)
        }.flow.stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
            .cachedIn(viewModelScope)

    private suspend fun getCarsList(
        page: Int
    ): List<Car> {
        return try {
            val payments = carsService.getCars(page)
            payments
        } catch (e: Exception) {
            Log.e("ERROR", e.message.toString())
            listOf()
        }
    }

    private val _totalNumOfCars = MutableStateFlow("")
    val totalNumOfCars: StateFlow<String> = _totalNumOfCars

    private fun getCountOfCars() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                carsService.getCountOfCars()
            }.onFailure {
                _totalNumOfCars.emit("0")
            }.onSuccess { count ->
                val countOfCars = bitDepth(count)
                _totalNumOfCars.emit(countOfCars)
            }
        }
    }

    fun deleteCar(car: Car) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                carsService.deleteCar(car)
            }.onFailure {

            }.onSuccess {

            }
        }
    }

    private val _car = MutableStateFlow(false)
    val car: StateFlow<Boolean> = _car

    fun addTestCar() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                carsService.addTestCar()
            }.onFailure {
                _car.emit(false)
            }.onSuccess {
                _car.emit(true)
            }
        }
    }
}