package alexander.test.cars.data.db

import alexander.test.cars.data.db.data.Car
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Car::class], version = 4)
abstract class AppDatabase : RoomDatabase() {
    abstract fun carsDao(): CarsDao
}