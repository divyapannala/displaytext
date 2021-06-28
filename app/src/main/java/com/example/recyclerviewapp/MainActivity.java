package com.example.recyclerviewapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerview;
    DetailsAdapter adaptery;
    SharedPreferences sp;
    Context context;
ArrayList<PersonDetails> detailsList;
    EditText pemail,pcontact;
    Button displaybtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pemail=findViewById(R.id.personemail);
        pcontact=findViewById(R.id.personcontact);
        recyclerview=(RecyclerView) findViewById(R.id.recyclerview);
        displaybtn=findViewById(R.id.display);

        sp=getSharedPreferences("persondetails",MODE_PRIVATE);
        SharedPreferences.Editor e=sp.edit();
        String usersList= sp.getString("usersList", null);
        if (usersList != null) {
            detailsList = new Gson().fromJson(usersList, new TypeToken<ArrayList<PersonDetails>>() {
            }.getType());
        } else {
            detailsList=new ArrayList<>();
        }


        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
recyclerview.setLayoutManager(layoutManager);
adaptery=new DetailsAdapter(context,detailsList);
recyclerview.setAdapter(adaptery);

        displaybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eid = pemail.getText().toString().trim();
                String econtact = pcontact.getText().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (eid.isEmpty() && econtact.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter The details", Toast.LENGTH_SHORT).show();
                }
                    else {
                    if (eid.matches(emailPattern) && econtact.length()==10) {
                        Toast.makeText(getApplicationContext(), "Enter valid email and contact", Toast.LENGTH_SHORT).show();
                        try {

                            boolean userExist = false;
                            for (PersonDetails pern : detailsList) {
                                if (pern.getPersonemail().equalsIgnoreCase(eid) || pern.getPersoncontact().equalsIgnoreCase(econtact)) {
                                    //user exist
                                    userExist = true;
                                }
                            }

                            if (userExist) {
                                Toast.makeText(MainActivity.this, "Already user exits", Toast.LENGTH_SHORT).show();
                            } else {
                                detailsList.add(new PersonDetails(eid, econtact));
                                adaptery.notifyItemInserted(detailsList.size() - 1);

                                e.putString("usersList", new Gson().toJson(detailsList));
                                e.commit();//---saves the values---
                                Toast.makeText(MainActivity.this, "User Details Registered", Toast.LENGTH_SHORT).show();
                                pemail.setText("");
                                pcontact.setText("");
                            }

                        } catch (NumberFormatException exception) {
                            Toast.makeText(MainActivity.this, "Cannot Display", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                    }


                }}


        });
    }


}