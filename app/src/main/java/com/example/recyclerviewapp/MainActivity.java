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

        displaybtn=findViewById(R.id.display);
        recyclerview=(RecyclerView) findViewById(R.id.recyclerview);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerview.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerview.setLayoutManager(layoutManager);
        adaptery=new DetailsAdapter(context,detailsList);
        recyclerview.setAdapter(adaptery);



        displaybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eid=pemail.getText().toString();
                String econtact=pcontact.getText().toString();

                if(eid.isEmpty() && econtact.isEmpty()){
                    Toast.makeText(MainActivity.this,"Enter The details",Toast.LENGTH_SHORT).show();

                }
                else{
                    sp=getSharedPreferences("details",MODE_PRIVATE);
                    SharedPreferences.Editor e=sp.edit();
                    String usersList= sp.getString("usersList", "");

                   try {
                        if (usersList != null) {
                            detailsList = new Gson().fromJson(usersList, new TypeToken<ArrayList<PersonDetails>>(){
                            }.getType());
                        } else {
                            detailsList=new ArrayList<>();
                       }

                        for (PersonDetails person : detailsList) {
                            String str = new Gson().toJson(detailsList);
                            detailsList.add(new PersonDetails(eid, econtact));
                            adaptery.notifyItemInserted(detailsList.size() - 1);
                            if (person .getPersonemail().equals(eid) || person .getPersoncontact().equals(econtact) ) {
                                Toast.makeText(MainActivity.this,"User Already Exists",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                detailsList= new Gson().fromJson(usersList, new TypeToken<ArrayList<PersonDetails>>() {}.getType());
                                e.putString("usersList",  new Gson().toJson(detailsList));
                                e.commit();
                                Toast.makeText(MainActivity.this, "User Details Registered",Toast.LENGTH_SHORT).show();
                                pemail.setText("");
                                pcontact.setText("");
                            }
                        }


                   }catch (NumberFormatException exception){
                       Toast.makeText(MainActivity.this,"Cannot Display",Toast.LENGTH_SHORT).show();
                   }
                }
            }
        });
    }


}