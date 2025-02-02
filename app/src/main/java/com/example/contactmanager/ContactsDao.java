package com.example.contactmanager;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactsDao {
   @Insert
   void insert(Contacts contacts);

   @Delete
    void  delete(Contacts contacts);

   @Query("Select * from contacts_table")
   LiveData <List<Contacts>> getAllContacts();

}
