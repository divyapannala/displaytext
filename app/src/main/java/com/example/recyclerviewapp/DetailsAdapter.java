package com.example.recyclerviewapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.SyncStateContract;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder> {

    ArrayList<PersonDetails> personlist;
    Context mcontext;

    public DetailsAdapter(Context mcontext,ArrayList<PersonDetails> personlist) {

        this.mcontext=mcontext;
        this.personlist = personlist;

    }


    @NonNull
    @Override
    public DetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.detailslist_person,parent,false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull DetailsAdapter.ViewHolder holder, int position) {


        holder.useremail.setText(personlist.get(position).getPersonemail());
         holder.mobile.setText(personlist.get(position).getPersoncontact());
    }

    @Override
    public int getItemCount() {
        if(personlist!=null) {
            return personlist.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView useremail,mobile;
        private SharedPreferences prefs;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            useremail=itemView.findViewById(R.id.emailid);
            mobile =itemView.findViewById(R.id.phoneno);

        }
    }
}
