package com.example.androidcontacts.service

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.androidcontacts.model.Contact

@Dao
interface ContactDao {
    @Insert
    suspend fun insert(contact: Contact)

    @Delete
    suspend fun delete(contact: Contact)

    @Query("SELECT * FROM contact_table ORDER BY id ASC")
    fun getAllContacts(): ArrayList<Contact>

    @Query("DELETE FROM contact_table")
    fun deleteAll()
}