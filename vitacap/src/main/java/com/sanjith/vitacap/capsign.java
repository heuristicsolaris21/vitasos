package com.sanjith.vitacap;

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

public class capsign extends AppCompatActivity {

    DatabaseReference databasereference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://vitaapp-7628c-default-rtdb.firebaseio.com/");
    private EditText editname, editnumber, editvehicle, editlicense, editdname, editpassword;
    private Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capsign);
        editnumber=(EditText) findViewById(R.id.editTextPhone2);
        editpassword=(EditText) findViewById(R.id.editTextTextPassword);
        editdname=(EditText) findViewById(R.id.editTextTextPersonName);
        editvehicle=(EditText) findViewById(R.id.editTextTextPersonName2);
        editlicense=(EditText) findViewById(R.id.editTextTextPersonName3);
        signup=(Button) findViewById(R.id.button3);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupfun();

            }
        });
    }

    void signupfun() {
        String name = editname.getText().toString().trim();
        String number = editnumber.getText().toString().trim();
        String vehicle = editvehicle.getText().toString().trim();
        String password = editpassword.getText().toString().trim();
        String license = editlicense.getText().toString().trim();
        if (name.isEmpty()) {
            editname.setError("Please enter the name.");
            editname.requestFocus();
            return;
        } else if (number.isEmpty()) {
            editnumber.setError("Please enter the number.");
            editnumber.requestFocus();
            return;
        } else if (vehicle.isEmpty()) {
            editvehicle.setError("Please enter the age");
            editvehicle.requestFocus();
            return;
        } else if (password.isEmpty()) {
            editpassword.setError("Please enter the password.");
            editpassword.requestFocus();
            return;
        } else if (password.length() < 8) {
            editpassword.setError("length of the pass word must be greater than 8");
            editpassword.requestFocus();
            return;
        }
        else {

            databasereference.child("Drivers").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChild(number)) {
                        Toast.makeText(capsign.this, "The Phone number is already registered", Toast.LENGTH_SHORT).show();
                    } else {
                        // sending data to database.
                        databasereference.child("Users").child(number).setValue(new helper1(name, number, vehicle, password, license));
                        Toast.makeText(capsign.this, "User is registered sucessfully", Toast.LENGTH_SHORT).show();
                    }

                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }


}