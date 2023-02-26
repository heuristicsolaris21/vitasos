package com.sanjith.vitasos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class sos2 extends AppCompatActivity {
    public static String phone;
    public static String latitude;
    public static String longitude;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREFS="mypref";
    private static final String LATI="Lati";
    private static final String LONGI="longi";
    private static final String PH="phn";
    Button yes,no;
    TextView as;
    DatabaseReference databasereference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://vitaapp-7628c-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos2);
        as = (TextView)findViewById(R.id.textView6);
        yes=(Button) findViewById(R.id.button);
        no=(Button)findViewById(R.id.button5);
        Intent s=getIntent();
        //String phonetxt =s.getStringExtra(phone);
        //String lati=s.getStringExtra(latitude);
        //String longi=s.getStringExtra(longitude);


        sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String phonetxt=sharedPreferences.getString(PH,null);
        String lati=sharedPreferences.getString(LATI,null);
        String longi=sharedPreferences.getString(LONGI,null);


        Toast.makeText(sos2.this,"asd"+lati+" "+longi+" "+phonetxt,Toast.LENGTH_SHORT).show();
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databasereference.child("data").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(phonetxt)) {
                            Toast.makeText(sos2.this, "The Phone number is already registered", Toast.LENGTH_SHORT).show();
                        } else {

                            databasereference.child("data").child(phonetxt).setValue(new helper2(phonetxt,lati,longi));
                            Toast.makeText(sos2.this, "data sent.", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                //databasereference.child("data").child(phone).setValue(new helper2(phonetxt,lati,longi));
                //Toast.makeText(sos2.this, "uploaded in database... " , Toast.LENGTH_SHORT).show();
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