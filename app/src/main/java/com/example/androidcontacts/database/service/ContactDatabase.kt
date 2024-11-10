package com.example.androidcontacts.database.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidcontacts.database.model.Contact
import com.example.androidcontacts.database.service.ContactDatabase.Companion.EXPORT_SCHEMA
import com.example.androidcontacts.database.service.ContactDatabase.Companion.VERSION

@Database(entities = [Contact::class], version = VERSION, exportSchema = EXPORT_SCHEMA)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun getContactDao(): ContactDao

    companion object {
        const val VERSION = 1
        const val NAME_DATABASE = "contact_database"
        const val EXPORT_SCHEMA = false
        private var INSTANCE: ContactDatabase? = null
        fun getDatabase(
//            fragment: Fragment,
            context: Context,
        ): ContactDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
//                    fragment.requireContext().applicationContext,
                    context.applicationContext,
                    ContactDatabase::class.java,
                    NAME_DATABASE
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}