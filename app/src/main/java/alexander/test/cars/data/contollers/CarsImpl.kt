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

    suspend fun addCar(car: Car) {
        withContext(Dispatchers.IO) {
            App.carsDataBase.carsDao().addCar(car)
        }
    }

    suspend fun addTestCar() {
        val carPhotoLinksList = listOf(
            "https://i.pinimg.com/originals/ea/f2/cb/eaf2cb26b0acc8642f6cc3493edd8514.jpg",
            "https://hdpic.club/uploads/posts/2021-12/1639303121_20-hdpic-club-p-maklaren-p1-22.jpg",
            "https://sportishka.com/uploads/posts/2022-11/1667547623_48-sportishka-com-p-avtomobil-maklaren-instagram-49.jpg",
            "https://w-dog.ru/wallpapers/0/8/516824736327192/mclaren-r1-maklaren-chernyj-superkar.jpg",
            "https://i.pinimg.com/originals/bc/12/be/bc12bed18d6ab5ff46c79133b502de0f.jpg",
            "https://sportishka.com/uploads/posts/2021-12/1639047478_23-sportishka-com-p-maklaren-mashina-sport-krasivo-foto-24.jpg",
            "https://sportishka.com/uploads/posts/2022-11/thumbs/1667547711_49-sportishka-com-p-avtomobil-maklaren-instagram-50.jpg"
        )
        withContext(Dispatchers.IO) {
            App.carsDataBase.carsDao().addCar(
                Car(
                    name = Car.carsList[(0..<Car.carsList.size).random()],
                    price = (1_000_000..100_000_000).random(),
                    year = (1990..2010).random(),
                    mileage = (100_000..500_000).random(),
                    carNumber = "",
                    carPhotos = carPhotoLinksList
                )
            )
        }
    }
}