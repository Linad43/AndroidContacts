package com.example.androidcontacts.database.service

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.androidcontacts.database.model.Contact

@Dao
interface ContactDao {
    @Insert
    suspend fun insert(contact: Contact)

    @Delete
    suspend fun delete(contact: Contact)

    @Query("SELECT * FROM ${Contact.NAME_TABLE} ORDER BY id ASC")
    fun getAllContacts(): LiveData<List<Contact>>

    @Query("DELETE FROM ${Contact.NAME_TABLE}")
    fun deleteAll()
}