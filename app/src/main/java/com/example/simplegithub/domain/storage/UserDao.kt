package com.example.simplegithub.domain.Storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addNewUser(userEntity: UserEntity): Single<Long>

    @Query("SELECT * FROM userentity")
    fun getAllUsers(): Flowable<List<UserEntity?>>

    @Query("SELECT * FROM userentity WHERE login == :login")
    fun findByLogin(login: String): Maybe<UserEntity>
}