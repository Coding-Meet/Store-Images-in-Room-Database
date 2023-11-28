package com.coding.meet.storeimagesinroomdatabase

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageDatabase = Room.databaseBuilder(
            applicationContext,
            ImageDatabase::class.java,
            "images_db"
        ).build()

        val imageRV = findViewById<RecyclerView>(R.id.imageRV)
        val imageAdapter = ImageAdapter()
        imageRV.adapter = imageAdapter

        // get All Images
        imageDatabase.imageDao.getAllImages().observe(this){
            imageAdapter.submitList(it)
        }



        /// Single Image Upload in room database
        val singleImagePickerBtn = findViewById<Button>(R.id.singleImageBtn)
        val singlePhotoPickerLauncher =
            registerForActivityResult(ActivityResultContracts.PickVisualMedia()){ uri ->

                // single image upload function
                uri?.let { newImageUpload(imageDatabase, it) }
            }
        singleImagePickerBtn.setOnClickListener {
            singlePhotoPickerLauncher.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        }

        /// Multiple Image Upload in room database
        val multiplePhotoPickLauncher =
            registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia()){uris ->
                for (image in uris){
                    // single image upload function
                    newImageUpload(imageDatabase, image)
                }
            }
        val multipleImagePickBtn = findViewById<Button>(R.id.multipleImageBtn)
        multipleImagePickBtn.setOnClickListener {
            multiplePhotoPickLauncher.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        }

    }

    private fun newImageUpload(imageDatabase: ImageDatabase, imageUri: Uri) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val newImage = contentResolver.openInputStream(imageUri)?.readBytes()?.let {
                    ImageModel(
                        UUID.randomUUID().toString(),
                        it
                    )
                }
                newImage?.let {
                    imageDatabase.imageDao.insertImage(it)
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}