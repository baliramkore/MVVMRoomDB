package com.example.contactmanager;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactmanager.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Data Sources
    //Pushing changes to github repository
    private ContactDatabase contactDatabase;
    private ArrayList<Contacts> contactsArrayList= new ArrayList();

    // Adapter
    MyAdapter myAdapter;

    //Creating Binding Object from ActivityMainBinding
    private ActivityMainBinding mainBinding;
    private MainActivityClickHandlers handlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        handlers=new MainActivityClickHandlers(this);
        mainBinding.setClickHandler(handlers);

        //RecyclerView
        RecyclerView recyclerView=mainBinding.recyclerview;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //Adapter


        //Database
        contactDatabase=ContactDatabase.getInstance(this);

        //ViewModel
        ContactViewModel myVievModel=new ViewModelProvider(this).get(ContactViewModel.class);


        // Testing Data Providing by adding some objects

        Contacts contacts=new Contacts("Baliram","baliramkore@gmail.com");
        Contacts contacts1=new Contacts("Chayya","baliramkore@gmail.com");
        Contacts contacts2=new Contacts("Renuka","baliramkore@gmail.com");


        //dealing with viewmodel to add data inside database
        myVievModel.addNewContact(contacts);
        myVievModel.addNewContact(contacts1);
        myVievModel.addNewContact(contacts2);


        //Loading data from Room database
        myVievModel.getAllContacts().observe(this, new Observer<List<Contacts>>() {
            @Override
            public void onChanged(List<Contacts> contacts) {

                for(Contacts cont:contacts){
                    Log.d("DB_result",cont.getName() + cont.getEmail());
                    contactsArrayList.add(cont);
                }

                myAdapter.notifyDataSetChanged();

            }
        });

        myAdapter= new MyAdapter(contactsArrayList);
        recyclerView.setAdapter(myAdapter);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

              Contacts  c=contactsArrayList.get(viewHolder.getAdapterPosition());
                myVievModel.deleteContact(c);
            }
        }).attachToRecyclerView(recyclerView);

    }
}