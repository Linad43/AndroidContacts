package com.example.androidcontacts.database.service

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.androidcontacts.database.model.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactViewModel(
    application: Application
):AndroidViewModel(application) {
    private val repos: ContactRepos
    val contacts:LiveData<List<Contact>>
    init {
        val dao = ContactDatabase.getDatabase(application).getContactDao()
        repos = ContactRepos(dao)
        contacts = repos.contacts
    }

    fun deleteContact(contact: Contact) = viewModelScope.launch(Dispatchers.IO) {
        repos.delete(contact)
    }
    fun insertContact(contact: Contact) = viewModelScope.launch(Dispatchers.IO) {
        repos.insert(contact)
    }

    fun deleteAllContact() {
        repos.deleteAll()
    }
}