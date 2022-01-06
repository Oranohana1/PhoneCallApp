package com.example.phonecallapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SmsFragment extends Fragment {


    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_sms, container, false);

        EditText MessageWrite = v.findViewById(R.id.editNumberAdd);
        Button sendBtn = v.findViewById(R.id.btnSendMessage);

        String smsNumber;



        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          //     String number = "tel: "
               SmsManager smsManager = SmsManager.getDefault();
               MainActivity activity = (MainActivity) getActivity();
               String smsNum = activity.number;
                System.out.println(smsNum);
                smsManager.sendTextMessage(smsNum, null, MessageWrite.getText().toString(), null,null );
                Toast.makeText(activity, "Message sent", Toast.LENGTH_SHORT).show();

            }
        });



        return v;
    }




}