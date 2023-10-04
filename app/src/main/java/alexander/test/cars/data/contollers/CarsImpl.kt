package alexander.test.cars.data.contollers

import alexander.test.cars.App
import alexander.test.cars.data.db.data.Car
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CarsImpl {
    suspend fun getCars(page: Int): List<Car> {
        return withContext(Dispatchers.IO) {
            return@withContext App.carsDataBase.carsDao().getPagedList(page)
        }
    }

    suspend fun getCountOfCars(): Int {
        return withContext(Dispatchers.IO) {
            return@withContext App.carsDataBase.carsDao().getCountOfCars()
        }
    }

    suspend fun deleteCar(car: Car) {
        withContext(Dispatchers.IO) {
            App.carsDataBase.carsDao().deleteCar(car)
        }
    }

    suspend fun addTestCar() {
        withContext(Dispatchers.IO) {
            App.carsDataBase.carsDao().addCar(
                Car(
                    name = Car.carsList[(0..<Car.carsList.size).random()],
                    price = (1_000_000..100_000_000).random(),
                    year = (1990..2010).random(),
                    mileage = (100_000..500_000).random(),
                    carNumber = ""
                )
            )
        }
    }
}