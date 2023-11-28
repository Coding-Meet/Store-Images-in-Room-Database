package com.coding.meet.storeimagesinroomdatabase

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
/**
 * @author Coding Meet
 * Created 28-11-2023 at 04:12 pm
 */
class ImageAdapter : ListAdapter<ImageModel, ImageAdapter.ViewHolder>(DiffCallback()){


    class DiffCallback : DiffUtil.ItemCallback<ImageModel>(){
        override fun areItemsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
           return oldItem == newItem
        }

    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val imageV = itemView.findViewById<ImageView>(R.id.imageV)

        fun bindData(imageModel: ImageModel){
            val bitmap = BitmapFactory.decodeByteArray(
                imageModel.imageData,0,imageModel.imageData.size
            )
            imageV.setImageBitmap(bitmap)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_image_layout,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageModel = getItem(position)
        holder.bindData(imageModel)
    }

}