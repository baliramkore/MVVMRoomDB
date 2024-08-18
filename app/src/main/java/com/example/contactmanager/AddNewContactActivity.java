package com.example.contactmanager;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.contactmanager.databinding.ActivityAddNewContactBinding;
import com.example.contactmanager.databinding.ActivityMainBinding;

public class AddNewContactActivity extends AppCompatActivity {

    private ActivityAddNewContactBinding activityAddNewContactBinding;
    private AddNewClickHandler handler;
    private  Contacts contacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);
        contacts=new Contacts();
        activityAddNewContactBinding= DataBindingUtil.setContentView(this,R.layout.activity_add_new_contact);

        ContactViewModel viewModel=new ViewModelProvider(this).get(ContactViewModel.class);

        handler=new AddNewClickHandler(contacts,this,viewModel);
        activityAddNewContactBinding.setContacts(contacts);


        activityAddNewContactBinding.setClickHandler(handler);


    }
}