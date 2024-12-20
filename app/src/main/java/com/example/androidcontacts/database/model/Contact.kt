package com.example.androidcontacts.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.androidcontacts.database.model.Contact.Companion.NAME_TABLE

@Entity(tableName = NAME_TABLE)
data class Contact(
    @ColumnInfo(name = KEY_NAME) val name: String,
    @ColumnInfo(name = KEY_NUM_PHONE) val numPhone: String,
    @ColumnInfo(name = KEY_DATA_CREATE) val dataCreate: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    companion object {
        const val NAME_TABLE = "contact_table"
        const val KEY_NAME = "name"
        const val KEY_NUM_PHONE = "numPhone"
        const val KEY_DATA_CREATE = "dataCreate"
    }
}