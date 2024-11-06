package com.example.androidcontacts.service

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidcontacts.model.Contact

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun getContactDao(): ContactDao

    companion object {
        const val VERSION = 1
        const val NAME_DATABASE = "contact_database"
        const val EXPORT_SCHEMA = false
        private var INSTANCE: ContactDatabase? = null
        fun getDatabase(
            fragment: Fragment
//            context: Context,
        ): ContactDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    fragment.requireContext().applicationContext,
//                    context.applicationContext,
                    ContactDatabase::class.java,
                    "contact_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}