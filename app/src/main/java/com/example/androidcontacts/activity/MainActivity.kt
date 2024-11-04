package com.example.androidcontacts.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidcontacts.R
import com.example.androidcontacts.fragments.ListContactsFragment
import com.example.androidcontacts.model.Contact
import com.example.androidcontacts.service.ContactDatabase
import com.example.androidviewpager.service.CommonFun
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class MainActivity : AppCompatActivity() {

    private var db: ContactDatabase? = null
    private lateinit var toolbar: Toolbar
    private lateinit var nameET: EditText
    private lateinit var numPhoneET: EditText
    private lateinit var saveBTN: Button
    private lateinit var listTextTV: TextView
    private val listET = arrayListOf<EditText>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.containerFragment, ListContactsFragment())
//            .commit()
        init()
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.exit -> finish()
            }
            true
        }

    }
    private fun init() {
        toolbar = findViewById(R.id.toolbar)
        nameET = findViewById(R.id.nameET)
        numPhoneET = findViewById(R.id.numPhoneET)
        saveBTN = findViewById(R.id.saveBTN)
        listTextTV = findViewById(R.id.listTextTV)
        toolbar.inflateMenu(R.menu.main_menu)
        listET.add(nameET)
        listET.add(numPhoneET)
        db = ContactDatabase.getDatabase(this)
        readDatabase(db!!)
    }

    override fun onResume() {
        super.onResume()
        saveBTN.setOnClickListener {
            if (CommonFun.allETIsNotEmpty(listET)) {
                val contact = Contact(
                    nameET.text.toString(),
                    numPhoneET.text.toString()
                )
                addContact(db!!, contact)
                readDatabase(db!!)
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun addContact(db: ContactDatabase, contact: Contact) =
        GlobalScope.async {
            db.getContactDao().insert(contact)
        }

    @OptIn(DelicateCoroutinesApi::class)
    private fun readDatabase(db: ContactDatabase) =
        GlobalScope.async {
            listTextTV.text = ""
            val list = db.getContactDao().getAllContacts()
            list.forEach {
                listTextTV.append("${it.name} = ${it.numPhone}\n")
            }
        }
}