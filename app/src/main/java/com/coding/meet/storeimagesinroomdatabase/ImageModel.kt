package com.coding.meet.storeimagesinroomdatabase

import androidx.room.*

/**
 * @author Coding Meet
 * Created 28-11-2023 at 04:03 pm
 */

@Entity(tableName = "images")
data class ImageModel(
    @PrimaryKey(autoGenerate = false)
    val id:String,
    @ColumnInfo(name = "imageData", typeAffinity = ColumnInfo.BLOB)
    val imageData : ByteArray
)
