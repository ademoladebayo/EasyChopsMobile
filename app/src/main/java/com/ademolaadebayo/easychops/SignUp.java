package com.ademolaadebayo.easychops;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {
    TextView oops, appName, phonelabel, passlabel, namelabel, cpasslabel, emaillabel;
    Animation zoom;
    EditText phone, pass, name, email, cpass;
    Button signup;
    Toast toast;
    boolean success;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        appName = findViewById(R.id.appName);
        phone = findViewById(R.id.phone);
        pass = findViewById(R.id.pass);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        cpass = findViewById(R.id.cpass);
        oops = findViewById(R.id.fpass);
        signup = findViewById(R.id.signup);

        //Firebase Conn
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference tableName = database.getReference("user");

        phonelabel = findViewById(R.id.phonelabel);
        passlabel = findViewById(R.id.passlabel);
        namelabel = findViewById(R.id.namelabel);
        cpasslabel = findViewById(R.id.cpasslabel);
        emaillabel = findViewById(R.id.emaillabel);


        zoom = AnimationUtils.loadAnimation(this, R.anim.zoom);
        appName.setAnimation(zoom);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "Fonts/Andhyta.ttf");
        appName.setTypeface(typeface);

        phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    phonelabel.setTextColor(getResources().getColor(R.color.pinklike));
                } else {
                    //focus has stopped perform your desired action
                    phonelabel.setTextColor(getResources().getColor(R.color.appName));
                }
            }
        });

        pass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    passlabel.setTextColor(getResources().getColor(R.color.pinklike));
                } else {
                    //focus has stopped perform your desired action
                    passlabel.setTextColor(getResources().getColor(R.color.appName));
                }
            }
        });
        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    namelabel.setTextColor(getResources().getColor(R.color.pinklike));
                } else {
                    //focus has stopped perform your desired action
                    namelabel.setTextColor(getResources().getColor(R.color.appName));
                }
            }
        });

        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    emaillabel.setTextColor(getResources().getColor(R.color.pinklike));
                } else {
                    //focus has stopped perform your desired action
                    emaillabel.setTextColor(getResources().getColor(R.color.appName));
                }
            }
        });

        cpass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    cpasslabel.setTextColor(getResources().getColor(R.color.pinklike));
                } else {
                    //focus has stopped perform your desired action
                    cpasslabel.setTextColor(getResources().getColor(R.color.appName));
                }
            }
        });

        oops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, SignIn.class);
                startActivity(intent);
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(SignUp.this);
                progressDialog.setMessage("Logging In...");
               // progressDialog.show();

                tableName.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(!success){
                        if(phone.getText().toString().equals("") || email.getText().toString().equals("") || pass.getText().toString().equals("")  || cpass.getText().toString().equals("") || name.getText().toString().equals("")) {
                            Toast.makeText(SignUp.this, "Fields Cannot Be Empty", Toast.LENGTH_SHORT).show();
                        }else{

                            if (dataSnapshot.child(phone.getText().toString()).exists()) {
                                phone.setText("");
                                Toast.makeText(SignUp.this, "Phone Number Has Been Used!", Toast.LENGTH_SHORT).show();

                            } else {
                                if (pass.getText().toString().equals(cpass.getText().toString())) {
                                    UserModel saveUser = new UserModel(name.getText().toString(), email.getText().toString(), pass.getText().toString());
                                    tableName.child(phone.getText().toString()).setValue(saveUser);
                                    Toast.makeText(SignUp.this, "Your Account Has Been Created Successfully!!!", Toast.LENGTH_SHORT).show();
                                    success = true;
                                    phone.setText("");
                                    email.setText("");
                                    name.setText("");
                                    pass.setText("");
                                    cpass.setText("");
                                    finish();

                                } else {
                                    Toast.makeText(SignUp.this, "The Password Does not match!!!", Toast.LENGTH_SHORT).show();
                                    pass.setText("");
                                    cpass.setText("");
                                }

                            }


                        }}
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }


        });
    }
}