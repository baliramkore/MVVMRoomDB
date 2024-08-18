package com.example.contactmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactmanager.databinding.ContactListItemBinding;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ContactViewHolder>{
    private ArrayList<Contacts> contactsArrayList;
    public MyAdapter(ArrayList<Contacts> contactsArrayList) {
        this.contactsArrayList = contactsArrayList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ContactListItemBinding itemBinding=
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.contact_list_item,parent,false
                        );
        return new ContactViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {

        //called by recyclerview when need to display or0
        // update item at specific position

        Contacts currentContact=contactsArrayList.get(position);
        holder.itemBinding.setContact(currentContact);

    }

    @Override
    public int getItemCount() {
        //total recycler item count
        if (contactsArrayList!=null){
            return contactsArrayList.size();
        }else {
            return 0;
        }
    }

    public void setContacts(ArrayList<Contacts> contactsArrayList) {
        this.contactsArrayList = contactsArrayList;
        //refresh recyclerview and reflect the  changes into recycler dataset
        notifyDataSetChanged();
    }
    class ContactViewHolder extends RecyclerView.ViewHolder{

        private ContactListItemBinding itemBinding;

        public ContactViewHolder(ContactListItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }
}
