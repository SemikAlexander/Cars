package alexander.test.cars.data.db

import alexander.test.cars.data.db.data.Car
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CarsDao {
    @Query("SELECT * FROM car_data ORDER BY carID ASC LIMIT 20 OFFSET :offset")
    fun getPagedList(offset: Int): List<Car>

    @Query("SELECT COUNT(*) FROM car_data")
    fun getCountOfCars(): Int

    @Query("SELECT * FROM car_data WHERE car_price >= :from AND car_price <= :to")
    fun getCarsByPrice(from: Int, to: Int): List<Car>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCar(car: Car)

    @Delete
    fun deleteCar(car: Car)
}
