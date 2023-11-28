package com.coding.meet.storeimagesinroomdatabase

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * @author Coding Meet
 * Created 28-11-2023 at 04:05 pm
 */

@Database(
    entities = [ImageModel::class],
    version = 1,
    exportSchema = false
)
abstract class ImageDatabase : RoomDatabase() {

    abstract val imageDao: ImageDao
}