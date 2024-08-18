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
    public LiveData<List<Contacts>>  getAllContact(){
    return contactsDao.getAllContacts();
    }

}
