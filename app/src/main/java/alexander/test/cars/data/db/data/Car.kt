package alexander.test.cars.data.db.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "car_data")
data class Car(
    @PrimaryKey(autoGenerate = true) val carID: Long = 0,
    @ColumnInfo(name = "car_name") val name: String,
    @ColumnInfo(name = "car_price") val price: Int,
    @ColumnInfo(name = "car_year") val year: Int,
    @ColumnInfo(name = "car_mileage") val mileage: Int,
    @ColumnInfo(name = "car_number") val carNumber: String
) {
    companion object {
        val carsList = listOf(
            "Acura", "Aston Martin", "Audi", "Austin", "AvtoVAZ", "BMW", "Bugatti", "Buick", "Canoo",
            "Chery", "Chevrolet", "Chrysler", "Citroën", "Dacia", "Daimler", "Dodge", "Ferrari", "Fiat",
            "Toyota", "Ford", "Geo", "GMC", "Honda", "Hyundai", "Infiniti", "Iran Khodro", "Isuzu",
            "Kia", "Lamborghini", "Lexus", "Lincoln", "Lada", "Maserati", "Mazda", "Mercury",
            "Mitsubishi", "Moskvitch", "Nissan", "Oldsmobile", "Opel", "Packard", "Plymouth",
            "Pontiac", "Porsche", "Renault", "Suzuki", "Saturn", "SEAT", "Škoda", "Subaru", "Toyota",
            "Vans", "Vauxhall", "Volkswagen", "Volvo", "Wolseley", "ZiL"
        )
    }
}
