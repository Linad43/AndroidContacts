package com.example.androidcontacts.database.service

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcontacts.R
import com.example.androidcontacts.database.model.Contact

class ContactAdapter(
    private val context: Context,
    private val listener: ContactClickListener
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    private val contacts = ArrayList<Contact>()

    inner class ContactViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        private val nameTV = view.findViewById<TextView>(R.id.nameTV)
        private val numPhoneTV = view.findViewById<TextView>(R.id.numPhoneTV)
        val deleteIconIV = view.findViewById<ImageView>(R.id.deleteIconIV)!!
        private val dateCreateTV = view.findViewById<TextView>(R.id.dateCreateTV)

        fun bind(contact: Contact) {
            nameTV.text = contact.name
            numPhoneTV.text = contact.numPhone
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val viewHolder =
            ContactViewHolder(
                LayoutInflater
                    .from(context)
                    .inflate(
                        R.layout.list_item,
                        parent,
                        false
                    )
            )
        viewHolder.deleteIconIV.setOnClickListener{
            listener.onItemClicked(contacts[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val currentContact = contacts[position]
        holder.bind(currentContact)

    }
    interface ContactClickListener{
        fun onItemClicked(contact: Contact)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Contact>){
        contacts.clear()
        contacts.addAll(newList)
        notifyDataSetChanged()
    }
}