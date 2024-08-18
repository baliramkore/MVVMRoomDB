package com.example.contactmanager;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class AddNewClickHandler {

    Contacts contacts;
    Context context;
    ContactViewModel viewModel;

    public AddNewClickHandler(Contacts contacts,
                              Context context,
                              ContactViewModel viewModel
                              ) {
        this.contacts = contacts;
        this.context = context;
        this.viewModel=viewModel;
    }

    public void onSubmitClickBtnClick(View view){

        if (contacts.getName()==null || contacts.getEmail()==null){
            Toast.makeText(view.getContext(),"Fields can not be emty",Toast.LENGTH_LONG).show();
        }else {
            Intent intent=new Intent(context,MainActivity.class);
            /*intent.putExtra("Name",contacts.getName());
            intent.putExtra("Email",contacts.getEmail());*/

            Contacts contacts1=new Contacts(contacts.getName(),contacts.getEmail());
            viewModel.addNewContact(contacts1);

            context.startActivity(intent);

        }
    }
}
