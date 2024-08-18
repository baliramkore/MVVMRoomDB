package com.example.contactmanager;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/*
=>View model is acts as mediator between Model and view
=>it is responsible to transform data from model to view
=>it is providing data stream by observing changes to the view
=>ViewModel usually exposes this information using LiveData Or Android Data Binding
*/
public class ContactViewModel extends AndroidViewModel {

    //Android Viewmodel is subclass of viewmodel and similar to them
    //they are designed to store and manage ui related data and responsible to provide data for UI
    //allow data to be survive during configuration changes.
    // as it provide scope until activity or fragment lifecycle completely destroyed


   // if we need to use context inside viewmodel,then we should use androidviewmodel(AVM),
   // because it provides application context, hence we are extending AndroidViewModel


    //Repository accesing methods having logic for insertion ,deletion
    private MyRepository myRepository;

    //Live Data for updating changes inside database
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
