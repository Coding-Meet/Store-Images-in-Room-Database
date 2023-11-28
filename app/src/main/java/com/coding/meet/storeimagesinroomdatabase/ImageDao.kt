package com.coding.meet.storeimagesinroomdatabase

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * @author Coding Meet
 * Created 28-11-2023 at 04:02 pm
 */

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(imageModel: ImageModel)

    @Query("SELECT * FROM images")
    fun getAllImages(): LiveData<List<ImageModel>>

}