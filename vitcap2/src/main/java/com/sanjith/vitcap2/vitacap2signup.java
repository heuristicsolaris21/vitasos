package com.sanjith.vitcap2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class vitacap2signup extends AppCompatActivity{
    private EditText editname,editnumber,editpassword,editvehicle,editli;
    private Button signup;
    DatabaseReference databasereference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://vitaapp-7628c-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitacap2signup);
        editname=(EditText)findViewById(R.id.editTextTextPersonName);
        editnumber=(EditText)findViewById(R.id.editTextPhone2);
        editpassword=(EditText)findViewById(R.id.editTextTextPassword);
        editvehicle=(EditText)findViewById(R.id.editTextTextPersonName2);
        editli=(EditText)findViewById(R.id.editTextTextPersonName3);
        signup=(Button)findViewById(R.id.button3);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signupfun();
            }
        });
    }
    void signupfun(){
        String name=editname.getText().toString().trim();
        String number=editnumber.getText().toString().trim();
        String password=editpassword.getText().toString().trim();
        String vehicle=editvehicle.getText().toString().trim();
        String li=editli.getText().toString().trim();
        if(name.isEmpty()){
            editname.setError("Please enter the name.");
            editname.requestFocus();
            return;
        }
        else if(number.isEmpty()){
            editnumber.setError("Please enter the number.");
            editnumber.requestFocus();
            return;
        }
        else if(password.isEmpty()){
            editpassword.setError("Please enter the password.");
            editpassword.requestFocus();
            return;
        }
        else if(password.length()<8){
            editpassword.setError("length of the pass word must be greater than 8");
            editpassword.requestFocus();
            return;
        }
        else if(vehicle.isEmpty()){
            editvehicle.setError("Please enter Yes or No");
            editvehicle.requestFocus();
            return;
        }
        else if(li.isEmpty()){
            editli.setError("Please enter Yes or No");
            editli.requestFocus();
            return;
        }

        else{

            databasereference.child("Drivers").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChild(number)) {
                        Toast.makeText(vitacap2signup.this, "The Phone number is already registered", Toast.LENGTH_SHORT).show();
                    } else {
                        // sending data to database.
                        databasereference.child("Drivers").child(number).setValue(new helper1(name,number,vehicle,password,li));
                        //databasereference.child("Users").child(number).child("NAME").setValue(name);
                        //databasereference.child("Users").child(number).child("PHONE NO.").setValue(number);
                        //databasereference.child("Users").child(number).child("PASSWORD").setValue(password);
                        //databasereference.child("Users").child(number).child("Email ID").setValue(email);
                        // showing progress.....(success msg)
                        Toast.makeText(vitacap2signup.this, "User is registered sucessfully", Toast.LENGTH_SHORT).show();

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }
    }



}