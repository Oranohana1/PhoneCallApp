package com.example.phonecallapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class CallFragment extends Fragment {





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_add_contact, container, false);

        EditText number = v.findViewById(R.id.editNumberAdd);
        Button callBtn = v.findViewById(R.id.btnSendMessage);

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNum = "tel:"+number.getText().toString();
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.setPhoneNumber(phoneNum);



            }

        });





        return v;
    }


}