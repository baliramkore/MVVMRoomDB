package com.example.contactmanager;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class MainActivityClickHandlers {
Context context;

    public MainActivityClickHandlers(Context context) {
        this.context = context;
    }
    public void OnFabClicked(View view){

        Intent intent=new Intent(view.getContext(),AddNewContactActivity.class);
        context.startActivity(intent);
    }
}
