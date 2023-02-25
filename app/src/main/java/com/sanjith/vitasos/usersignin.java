package com.sanjith.vitasos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class usersignin extends AppCompatActivity {
    DatabaseReference databasereference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://vitaapp-7628c-default-rtdb.firebaseio.com/");
    private EditText editname,editnumber,editage,editpassword,editdia,editbp,editdrugs,editemer;
    private Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersignin);
        editname=(EditText)findViewById(R.id.editTextTextPersonName);
        editnumber=(EditText)findViewById(R.id.editTextPhone);
        editage=(EditText)findViewById(R.id.editTextNumber);
        editpassword=(EditText)findViewById(R.id.editTextTextPassword2);
        editdia=(EditText)findViewById(R.id.editTextTextPersonName2);
        editbp=(EditText)findViewById(R.id.editTextTextPersonName3);
        editdrugs=(EditText)findViewById(R.id.editTextTextPersonName4);
        editemer=(EditText)findViewById(R.id.editTextPhone4);
        signup=(Button)findViewById(R.id.button4);
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
        String age=editage.getText().toString().trim();
        String password=editpassword.getText().toString().trim();
        String dia=editdia.getText().toString().trim();
        String bp=editbp.getText().toString().trim();
        String drugs=editdrugs.getText().toString().trim();
        String emer=editemer.getText().toString().trim();
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
        else if(age.isEmpty()){
            editage.setError("Please enter the age");
            editage.requestFocus();
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
        else if(dia.isEmpty()){
            editdia.setError("Please enter Yes or No");
            editdia.requestFocus();
            return;
        }
        else if(bp.isEmpty()){
            editbp.setError("Please enter Yes or No");
            editbp.requestFocus();
            return;
        }
        else if(drugs.isEmpty()){
            editdrugs.setError("Please enter Yes or No");
            editdrugs.requestFocus();
            return;
        }
        else if(emer.isEmpty()){
            editemer.setError("Please enter Yes or No");
            editemer.requestFocus();
            return;
        }
        else{

            databasereference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChild(number)) {
                        Toast.makeText(usersignin.this, "The Phone number is already registered", Toast.LENGTH_SHORT).show();
                    } else {
                        // sending data to database.
                        databasereference.child("Users").child(number).setValue(new helper1(name,number,age,password,dia,bp,drugs,emer));
                        //databasereference.child("Users").child(number).child("NAME").setValue(name);
                        //databasereference.child("Users").child(number).child("PHONE NO.").setValue(number);
                        //databasereference.child("Users").child(number).child("PASSWORD").setValue(password);
                        //databasereference.child("Users").child(number).child("Email ID").setValue(email);
                        // showing progress.....(success msg)
                        Toast.makeText(usersignin.this, "User is registered sucessfully", Toast.LENGTH_SHORT).show();

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }
    }

}