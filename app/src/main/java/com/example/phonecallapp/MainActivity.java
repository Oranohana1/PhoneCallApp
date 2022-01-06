package com.example.phonecallapp;

import static android.view.View.GONE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.app.MediaRouteButton;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    public String phoneNumber;
    public String number;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    boolean flag = true;
    Button calBtn, searchBtn;
    EditText numberToSearch;
    TextView name, numberContact, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_SMS,
                        Manifest.permission.SEND_SMS}
                , PackageManager.PERMISSION_GRANTED);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


     //   calBtn = findViewById(R.id.buttonCall);


        phoneNumber = "tel: 0526788464";


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Contacts");

        calBtn =  findViewById(R.id.btnCall);
        searchBtn = findViewById(R.id.btnSearch);
        numberToSearch = findViewById(R.id.editSearchNumber);
        name = findViewById(R.id.TxtNameContact);
        numberContact = findViewById(R.id.txtNumberContact);
        gender = findViewById(R.id.txtGenderContact);


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numberSearch = numberToSearch.getText().toString();

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            number = dataSnapshot.child("number").getValue(String.class);

                            String name1 = dataSnapshot.child("name").getValue(String.class);
                            String gender1 = dataSnapshot.child("gender").getValue(String.class);

                            System.out.println("name: -> "+name1);

                            System.out.println("number -> "+number);

                            if (number .equals(numberSearch)) {
                                System.out.println("numbertosearch "+numberSearch);

                                Bundle bundle = new Bundle();
                                bundle.putString("number", String.valueOf(number));
                                Fragment fragment = new SmsFragment();
                                fragment.setArguments(bundle);
                                getSupportFragmentManager().beginTransaction().replace(R.id.settingsFragment, fragment).commit();

                                name.setText("Name: "+name1);
                                numberContact.setText("Phone number: "+number);
                                gender.setText("Gender: "+gender1);
                                phoneNumber = "tel: "+number;
                                break;
                            }
                            else{
                              name.setText("not Exist !");
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        calBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallPhone();
            }
        });








    }

    private void CallPhone() {
        String number = phoneNumber.toString();
        //number = number.toString();
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(number));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item){
        View visible = findViewById(R.id.settingsFragment);
        fragmentManager = getSupportFragmentManager();
        switch (item.getItemId()) {
            case R.id.addContact:


                fragmentManager.beginTransaction().replace(R.id.settingsFragment, AddContactFragment.class, null).
                        setReorderingAllowed(true).
                        addToBackStack(null).
                        commit();

                if (flag) {
                    visible.setVisibility(View.VISIBLE);
                    //numberToSearch.setVisibility(View.GONE);
                    name.setVisibility(GONE);
                    numberContact.setVisibility(GONE);
                    gender.setVisibility(GONE);
                    searchBtn.setVisibility(GONE);
                    flag = false;
                } else {
                    visible.setVisibility(View.INVISIBLE);
                    name.setVisibility(View.VISIBLE);
                    numberContact.setVisibility(View.VISIBLE);
                    gender.setVisibility(View.VISIBLE);
                    searchBtn.setVisibility(View.VISIBLE);
                    flag = true;
                }
                break;

            case R.id.call:

                fragmentManager.beginTransaction().replace(R.id.settingsFragment, SmsFragment.class, null).
                        setReorderingAllowed(true).
                        addToBackStack(null).
                        commit();
                if (flag) {
                    visible.setVisibility(View.VISIBLE);
                    //numberToSearch.setVisibility(View.GONE);
                    name.setVisibility(GONE);
                    numberContact.setVisibility(GONE);
                    gender.setVisibility(GONE);
                    searchBtn.setVisibility(GONE);
                    flag = false;
                } else {
                    visible.setVisibility(View.INVISIBLE);
                    name.setVisibility(View.VISIBLE);
                    numberContact.setVisibility(View.VISIBLE);
                    gender.setVisibility(View.VISIBLE);
                    searchBtn.setVisibility(View.VISIBLE);
                    flag = true;
                }
                break;


        }
        return super.onOptionsItemSelected(item);
    }

}