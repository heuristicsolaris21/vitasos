package com.sanjith.vitasos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private Button btsignup,btlogin;
    private Context context;
    DatabaseReference databasereference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://vitaapp-7628c-default-rtdb.firebaseio.com/");
    private EditText phonetxt,passwordtxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btsignup=findViewById(R.id.button3);
        btlogin=findViewById(R.id.button2);
        phonetxt=findViewById(R.id.editTextPhone3);
        passwordtxt=findViewById(R.id.editTextTextPassword);
        context=this;
        btsignup.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Intent signup=new Intent(context,usersignin.class);
                startActivity(signup);

            }
        });
        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number1=phonetxt.getText().toString().trim();
                String password1=passwordtxt.getText().toString().trim();
                if(number1.isEmpty()){
                    phonetxt.setError("Please enter the number.");
                    phonetxt.requestFocus();
                    return;
                }

                else if(password1.isEmpty()){
                    passwordtxt.setError("Please enter the password.");
                    passwordtxt.requestFocus();
                    return;
                }
                else if(password1.length()<8){
                    passwordtxt.setError("length of the pass word must be greater than 8");
                    passwordtxt.requestFocus();
                    return;
                }
                else{
                    databasereference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(number1)){
                                final String getpassword= snapshot.child(number1).child("password").getValue(String.class);
                                if(getpassword.equals(password1)){

                                    Toast.makeText(MainActivity.this, "Successfully logged in......", Toast.LENGTH_SHORT).show();
                                    Intent a  =new Intent(MainActivity.this,usersosscreen.class);
                                    a.putExtra(usersosscreen.number,number1);
                                    startActivity(a);
                                }
                                else{
                                    Toast.makeText(MainActivity.this, "Wrong password......", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Wrong password......", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });//data base checking...
                }
            }
        });


    }
}