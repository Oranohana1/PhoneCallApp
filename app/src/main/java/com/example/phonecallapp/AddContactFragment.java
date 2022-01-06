package com.example.phonecallapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class AddContactFragment extends Fragment {
EditText name, number, gender;
Button send;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_add_contact, container, false);



        name = v.findViewById(R.id.editTextContactName);
        number = v.findViewById(R.id.editNumberAdd);
        gender = v.findViewById(R.id.editTextGender);
        send = v.findViewById(R.id.btnSendMessage);

                String nameToAdd, phoneNumber, genderToAdd;
                nameToAdd = name.getText().toString();
                phoneNumber = number.getText().toString();
                genderToAdd = gender.getText().toString();

                Person person = new Person(nameToAdd, phoneNumber, genderToAdd);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefContacts = database.getReference("Contacts");

//==================================================================================================
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                String nameToAdd, phoneNumber, genderToAdd;
                nameToAdd = name.getText().toString();
                phoneNumber = number.getText().toString();
                genderToAdd = gender.getText().toString();

                Person person = new Person(nameToAdd, phoneNumber, genderToAdd);
                myRefContacts.push().setValue(person);


            }
        });

//==================================================================================================



        return v;
    }


}