package alexander.test.cars.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cars(
    @PrimaryKey val carID: Int,
    val name: String,
    val price: Int,
    val year: Int,
    val mileage: Int,
    val carNumber: String
)
