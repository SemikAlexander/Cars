package alexander.test.cars.ui.main

import alexander.test.cars.databinding.ActivityMainBinding
import alexander.test.cars.ui.adapters.PhotoGalleryAdapter
import alexander.test.cars.ui.extentions.gone
import alexander.test.cars.ui.extentions.visible
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun setPhotoGallery(photos: List<String>) {
        binding.run {
            ivClose.setOnClickListener {
                flPhotoGallery.gone()
            }

            flPhotoGallery.visible()

            vpPhotos.adapter = PhotoGalleryAdapter(photos)
        }
    }
}