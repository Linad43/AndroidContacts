package com.example.androidcontacts.database.service

import androidx.lifecycle.LiveData
import com.example.androidcontacts.database.model.Contact

class ContactRepos(
    private val contactDao: ContactDao,
) {
    val contacts: LiveData<List<Contact>> = contactDao.getAllContacts()
    suspend fun insert(contact: Contact){
        contactDao.insert(contact)
    }
    suspend fun delete(contact: Contact){
        contactDao.delete(contact)
    }

    fun deleteAll() {
        contactDao.deleteAll()
    }
}