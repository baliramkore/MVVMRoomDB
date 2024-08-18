package com.example.contactmanager;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyRepository {

    private final ContactsDao contactsDao;
    ExecutorService executorService;
    Handler handler;


    //Need application context for accessing database we are getting it from constructor
    public MyRepository(Application application) {

        ContactDatabase contactDatabase=ContactDatabase.getInstance(application);
        this.contactsDao=contactDatabase.getConactDao();

        // make UI thread released providers another threads to execution in another thread
        // background database operations
         executorService= Executors.newSingleThreadExecutor();

        // used for UI updating
         handler=new Handler(Looper.getMainLooper());
    }

    public void addContact(Contacts contact){



        // executing tasks on saperate thread
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                contactsDao.insert(contact);
            }
        });



    }

    public void deleteContact(Contacts contact){
        // executing tasks on saperate thread
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                contactsDao.delete(contact);
            }
        });

    }

    // We are going to use Live data for updating inside UI hence no need to use executor or handler
    // making corresponding changes inside DAO Class which returns the Live Data
    //
    public LiveData<List<Contacts>>  getAllContact(){
    return contactsDao.getAllContacts();
    }

}
