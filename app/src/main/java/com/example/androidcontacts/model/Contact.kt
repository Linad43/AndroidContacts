package com.example.androidcontacts.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_table")
data class Contact(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "numPhone") val numPhone: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    companion object {
        const val NAME_TABLE = "contact_table"
        const val KEY_NAME = "name"
        const val KEY_NUM_PHONE = "numPhone"
    }
}