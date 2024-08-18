package com.example.contactmanager;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {

    //Repository
    private MyRepository myRepository;

    //Live Data
    private LiveData<List<Contacts>> allContacts;

    public ContactViewModel(@NonNull Application application) {
        super(application);
        this.myRepository=new MyRepository(application);
    }
    public LiveData<List<Contacts>> getAllContacts(){
        allContacts= myRepository.getAllContact();
        return allContacts;
    }

    public void addNewContact(Contacts contacts){
        myRepository.addContact(contacts);
    }

    public void deleteContact(Contacts contacts){
        myRepository.deleteContact(contacts);
    }

}
