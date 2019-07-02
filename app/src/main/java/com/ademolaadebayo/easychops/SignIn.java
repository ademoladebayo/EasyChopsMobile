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

public class SignIn extends AppCompatActivity {
    TextView  creatacct,appName , phonelabel , passlabel;  Animation  zoom;
    EditText phone , pass;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        appName = findViewById(R.id.appName);
        phone = findViewById(R.id.phone);
        pass = findViewById(R.id.pass);
        phonelabel = findViewById(R.id.phonelabel);
        passlabel = findViewById(R.id.passlabel);
        creatacct = findViewById(R.id.createAcct);
        login = findViewById(R.id.login);

        //Firebase Conn
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference tableName = database.getReference("user");

        zoom = AnimationUtils.loadAnimation(this,R.anim.zoom);
        appName.setAnimation(zoom);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"Fonts/Andhyta.ttf");
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

        creatacct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this,SignUp.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             final  ProgressDialog progressDialog = new ProgressDialog(SignIn.this);
                progressDialog.setMessage("Logging In...");
                progressDialog.show();

                tableName.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (!phone.getText().toString().equals("") || !pass.getText().toString().equals("")) {
                            UserModel user = dataSnapshot.child(phone.getText().toString()).getValue(UserModel.class);
                            if (dataSnapshot.child(phone.getText().toString()).exists()) {
                                if (user.getPassword().equals(pass.getText().toString())) {

                                    Toast.makeText(SignIn.this, "Success", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(SignIn.this, "failed", Toast.LENGTH_SHORT).show();

                                }

                            } else {
                                Toast.makeText(SignIn.this, "User not found!", Toast.LENGTH_SHORT).show();

                            }
                            phone.setText("");
                            pass.setText("");
                        }else{

                            Toast.makeText(SigIn.this,)
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


             finish();
            }
        });


    }
}
