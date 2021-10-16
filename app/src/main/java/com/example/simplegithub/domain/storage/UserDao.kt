package com.example.simplegithub.domain.Storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNewUser(userEntity: UserEntity):Long
    @Query("SELECT * FROM userentity")
    suspend fun getAllUsers():List<UserEntity?>
    @Query("SELECT * FROM userentity WHERE login == :login")
    suspend fun findByLogin(login:String):UserEntity?
}