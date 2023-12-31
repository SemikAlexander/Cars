package alexander.test.cars.ui.adapters

import alexander.test.cars.R
import alexander.test.cars.data.db.data.Car
import alexander.test.cars.databinding.ItemCarBinding
import alexander.test.cars.ui.extentions.bitDepth
import alexander.test.cars.ui.extentions.dp
import alexander.test.cars.ui.extentions.onClickDebounce
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.shape.CornerFamily

class CarAdapter(
    val onClickItem: (Car) -> Unit = { },
    val onPhotoClick: (Car) -> Unit = { },
    val onDeleteClickItem: (Car) -> Unit = { }
) : PagingDataAdapter<Car, CarAdapter.Holder>(CarsDiffUtil) {

    override fun onBindViewHolder(holder: Holder, position: Int) {
        getItem(position)?.let(holder::bind)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return Holder(ItemCarBinding.inflate(layoutInflater, parent, false))
    }

    inner class Holder(private val binding: ItemCarBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Car) = binding.apply {
            item.run {
                tvPrice.text = itemView.context.getString(R.string.car_price, bitDepth(price))
                tvCarName.text = name
                tvMileage.text = itemView.context.getString(R.string.car_millage, bitDepth(mileage))
                tvYear.text = itemView.context.getString(R.string.car_year, year.toString())

                val photo = if (carPhotos.isEmpty()) {
                    R.drawable.ic_car
                } else {
                    carPhotos[0]
                }

                ivCarPhoto.shapeAppearanceModel =
                    ivCarPhoto.shapeAppearanceModel
                        .toBuilder()
                        .setTopLeftCorner(CornerFamily.ROUNDED, 16F.dp.toFloat())
                        .setBottomLeftCorner(CornerFamily.ROUNDED, 16F.dp.toFloat())
                        .build()

                ivCarPhoto.load(photo) {
                    crossfade(true)
                    placeholder(R.drawable.ic_loading)
                    error(R.drawable.ic_no_image)
                }

                ivCarPhoto.onClickDebounce {
                    onPhotoClick(this)
                }

                itemView.onClickDebounce {
                    onClickItem(this)
                }

                swipeBtnDelete.onClickDebounce {
                    onDeleteClickItem(this)
                }
            }
        }
    }
}

object CarsDiffUtil : DiffUtil.ItemCallback<Car>() {
    override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean {
        return oldItem.carID == newItem.carID
    }

    override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean {
        return oldItem == newItem
    }
}