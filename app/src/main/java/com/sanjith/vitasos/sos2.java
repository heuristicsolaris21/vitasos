package com.sanjith.vitasos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class sos2 extends AppCompatActivity {
    public static String number;
    public static String latitude;
    public static String longitude;
    DatabaseReference databasereference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://vitaapp-7628c-default-rtdb.firebaseio.com/");
    Intent s =getIntent();
    String phonetxt =s.getStringExtra(number);
    String latitude1=s.getStringExtra(latitude);
    String longitude1=s.getStringExtra(longitude);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos2);
    }
    public void funno(){
        Intent a  =new Intent(sos2.this,usersosscreen.class);
        startActivity(a);
    }
    public void funyes(){
        databasereference.child("data").child(number).setValue(new helper2(phonetxt,latitude1,longitude1));
        Toast.makeText(sos2.this, "uploaded in database... " , Toast.LENGTH_SHORT).show();
    }
}