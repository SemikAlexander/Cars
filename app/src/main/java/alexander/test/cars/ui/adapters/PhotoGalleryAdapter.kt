package alexander.test.cars.ui.adapters

import alexander.test.cars.R
import alexander.test.cars.databinding.ItemCarPhotoBinding
import alexander.test.cars.ui.extentions.dp
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load

class PhotoGalleryAdapter(
    private var items: List<String>
) : RecyclerView.Adapter<PhotoGalleryAdapter.GalleryHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryHolder {
        return GalleryHolder(
            ItemCarPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: GalleryHolder, position: Int) =
        holder.bind(items[position])

    inner class GalleryHolder(private val view: ItemCarPhotoBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(item: String) {
            view.run {
                ivCarPhoto.shapeAppearanceModel =
                    ivCarPhoto.shapeAppearanceModel
                        .toBuilder()
                        .setAllCornerSizes(16f.dp.toFloat())
                        .build()

                ivCarPhoto.load(item) {
                    crossfade(true)
                    placeholder(R.drawable.ic_loading)
                    error(R.drawable.ic_no_image)
                }
            }
        }
    }
}