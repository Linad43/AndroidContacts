package com.example.androidcontacts.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.androidcontacts.R
import com.example.androidcontacts.model.Contact
import com.example.androidcontacts.service.ContactDatabase
import com.example.androidviewpager.service.CommonFun
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.joinAll


class ListContactsFragment : Fragment() {

    private var db: ContactDatabase? = null
    private lateinit var toolbar: Toolbar
    private lateinit var nameET: EditText
    private lateinit var numPhoneET: EditText
    private lateinit var saveBTN: Button
    private lateinit var listTextTV: TextView
    private val listET = arrayListOf<EditText>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_list_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.exit -> activity?.finish()
            }
            true
        }

    }

    override fun onResume() {
        super.onResume()
        saveBTN.setOnClickListener {
            if (CommonFun.allETIsNotEmpty(listET)) {
                val contact = Contact(
//                    0,
                    nameET.text.toString(),
                    numPhoneET.text.toString()
                )
                addContact(db!!, contact).start()
                readDatabase(db!!).start()
            }
        }
    }

    private fun init(view: View) {
        toolbar = view.findViewById(R.id.toolbar)
        nameET = view.findViewById(R.id.nameET)
        numPhoneET = view.findViewById(R.id.numPhoneET)
        saveBTN = view.findViewById(R.id.saveBTN)
        listTextTV = view.findViewById(R.id.listTextTV)
        toolbar.inflateMenu(R.menu.main_menu)
        listET.add(nameET)
        listET.add(numPhoneET)
        db = ContactDatabase.getDatabase(this)
        readDatabase(db!!)
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
            val listForTV = db.getContactDao().getAllContacts()
            listForTV.forEach {
                listTextTV.append("${it.name} = ${it.numPhone}\n")
            }
        }
}