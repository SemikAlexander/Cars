package alexander.test.cars.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CarsDao {
    @Query("SELECT * FROM cars")
    fun getAll(): List<Cars>

    @Query("SELECT * FROM cars WHERE price >= :from AND price <= :to")
    fun getCarsByPrice(from: Int, to: Int): List<Cars>

    @Insert
    fun addCar(car: Cars)

    @Delete
    fun deleteCar(car: Cars)
}
