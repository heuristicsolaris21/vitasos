package com.sanjith.vitasos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class sos2 extends AppCompatActivity {
    public static String phonetxt;
    public static String latitude;
    public static String longitude;
    Button yes,no;
    TextView as;
    //DatabaseReference databasereference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://vitaapp-7628c-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos2);
        as = (TextView)findViewById(R.id.textView6);
        yes=(Button) findViewById(R.id.button);
        no=(Button)findViewById(R.id.button5);
        Intent intent = getIntent();
        String phone = intent.getStringExtra("phonetxt");
        String latitude =intent.getStringExtra("latitude");
        String longitute = intent.getStringExtra("longitude");
        as.setText(phone);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //databasereference.child("data").child(phone).setValue(new helper2(phone,latitude,longitute));
                Toast.makeText(sos2.this, "uploaded in database... " , Toast.LENGTH_SHORT).show();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a  =new Intent(sos2.this,usersosscreen.class);
                startActivity(a);
            }
        });


    }

}