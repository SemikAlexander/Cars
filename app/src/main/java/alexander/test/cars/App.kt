package alexander.test.cars

import alexander.test.cars.data.db.AppDatabase
import android.app.Application
import androidx.room.Room
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    companion object {
        lateinit var carsDataBase:  AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        carsDataBase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "cars-db"
        ).build()
    }
}