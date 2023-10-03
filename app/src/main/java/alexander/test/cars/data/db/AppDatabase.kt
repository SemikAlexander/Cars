package alexander.test.cars.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Cars::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun carsDao(): CarsDao
}