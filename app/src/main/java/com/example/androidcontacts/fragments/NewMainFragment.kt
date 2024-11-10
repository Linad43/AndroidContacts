package com.example.androidcontacts.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcontacts.R
import com.example.androidcontacts.database.model.Contact
import com.example.androidcontacts.database.service.ContactAdapter
import com.example.androidcontacts.database.service.ContactViewModel
import com.example.androidviewpager.service.CommonFun
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.text.SimpleDateFormat
import java.util.Date


class NewMainFragment : Fragment(), ContactAdapter.ContactClickListener {

    private lateinit var viewModel: ContactViewModel
    private lateinit var recyclerRL: RecyclerView
    private lateinit var toolbar: Toolbar
    private lateinit var nameET: EditText
    private lateinit var numPhoneET: EditText
    private lateinit var saveBTN: Button
    private lateinit var deleteBTN: Button
    private lateinit var adapter: ContactAdapter

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
                val contact =
                    Contact(
                        nameET.text.toString(),
                        numPhoneET.text.toString(),
                        getDatatime()
                    )
                viewModel.insertContact(contact)
                adapter.notifyDataSetChanged()
//                update()
                listET.forEach {
                    it.text.clear()
                }
            }
        }
        deleteBTN.setOnClickListener {
            GlobalScope.async {
                viewModel.deleteAllContact()
            }
        }
    }

    private fun getDatatime(): String {
        val timeFormat = SimpleDateFormat("(yyyy.MM.dd) EEE, HH:mm")
        return timeFormat.format(Date().time)
    }

    private fun init(view: View) {
        toolbar = view.findViewById(R.id.toolbar)
        toolbar.inflateMenu(R.menu.main_menu)

        nameET = view.findViewById(R.id.nameET)
        numPhoneET = view.findViewById(R.id.numPhoneET)
        saveBTN = view.findViewById(R.id.saveBTN)
        deleteBTN = view.findViewById(R.id.deleteBTN)
        recyclerRL = view.findViewById(R.id.recyclerRV)

        recyclerRL.layoutManager = LinearLayoutManager(this.requireContext())
        adapter = ContactAdapter(this.requireContext(), this)
        recyclerRL.adapter = adapter
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(
                    requireActivity()
                        .application
                )
        )[ContactViewModel::class.java]
        update()
        listET.add(nameET)
        listET.add(numPhoneET)
    }


    override fun onItemClicked(contact: Contact) {
        viewModel.deleteContact(contact)
        Toast.makeText(
            this.requireContext(),
            "Контакт ${contact.name} удален.",
            Toast.LENGTH_SHORT
        )
            .show()
    }

    private fun update() {
        viewModel.contacts.observe(viewLifecycleOwner) {
            it?.let {
                adapter.updateList(it)
            }
        }
    }

}
