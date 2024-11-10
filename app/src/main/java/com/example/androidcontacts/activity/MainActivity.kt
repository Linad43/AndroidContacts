package com.example.androidcontacts.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcontacts.R
import com.example.androidcontacts.database.model.Contact
import com.example.androidcontacts.database.service.ContactAdapter
import com.example.androidcontacts.database.service.ContactViewModel
import com.example.androidcontacts.fragments.NewMainFragment
import com.example.androidviewpager.service.CommonFun
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ContactViewModel
    private lateinit var recyclerRL: RecyclerView
    private lateinit var toolbar: Toolbar
    private lateinit var nameET: EditText
    private lateinit var numPhoneET: EditText
    private lateinit var saveBTN: Button
    private lateinit var deleteBTN: Button
    private lateinit var adapter: ContactAdapter

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
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.containerFragment, NewMainFragment())
            .commit()

//        init()

//        saveBTN.setOnClickListener {
//            if (CommonFun.allETIsNotEmpty(listET)) {
//                val contact =
//                    Contact(
//                        nameET.text.toString(),
//                        numPhoneET.text.toString(),
//                        getDatatime()
//                    )
//                viewModel.insertContact(contact)
//                adapter.notifyDataSetChanged()
////                update()
//                listET.forEach {
//                    it.text.clear()
//                }
//            }
//        }
//
//        deleteBTN.setOnClickListener {
//            GlobalScope.async {
//                viewModel.deleteAllContact()
//            }
//        }
//
//        toolbar.setOnMenuItemClickListener {
//            when (it.itemId) {
//                R.id.exit -> finish()
//            }
//            true
//        }
    }

//    private fun init() {
//        toolbar = findViewById(R.id.toolbar)
//        toolbar.inflateMenu(R.menu.main_menu)
//
//        nameET = findViewById(R.id.nameET)
//        numPhoneET = findViewById(R.id.numPhoneET)
//        saveBTN = findViewById(R.id.saveBTN)
//        deleteBTN = findViewById(R.id.deleteBTN)
//        recyclerRL = findViewById(R.id.recyclerRV)
//
//        recyclerRL.layoutManager = LinearLayoutManager(this)
//        adapter = ContactAdapter(this, this)
//        recyclerRL.adapter = adapter
//        viewModel = ViewModelProvider(
//            this,
//            ViewModelProvider
//                .AndroidViewModelFactory
//                .getInstance(application)
//        )[ContactViewModel::class.java]
//        update()
//        listET.add(nameET)
//        listET.add(numPhoneET)
//    }
//
//    private fun getDatatime(): String {
//        val timeFormat = SimpleDateFormat("(yyyy.MM.dd) EEE, HH:mm")
//        return timeFormat.format(Date().time)
//    }
//
//    override fun onItemClicked(contact: Contact) {
//        viewModel.deleteContact(contact)
//        Toast.makeText(
//            this,
//            "Контакт ${contact.name} удален.",
//            Toast.LENGTH_SHORT
//        )
//            .show()
//    }
//
//    private fun update() {
//        viewModel.contacts.observe(this) {
//            it?.let {
//                adapter.updateList(it)
//            }
//        }
//    }
}