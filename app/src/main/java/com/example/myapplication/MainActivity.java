package com.example.myapplication;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText pass, email,name;
    Button login,signup;
    FirebaseAuth auth,mauth;
private  long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mauth = FirebaseAuth.getInstance();
        if (mauth.getCurrentUser() != null) {
            Intent intent = new Intent(MainActivity.this, Main_page.class);
            startActivity(intent);
        } else {
            setContentView(R.layout.activity_main);
            email = findViewById(R.id.Email);
            pass = findViewById(R.id.pass);
            login = findViewById(R.id.Login);
            signup = findViewById(R.id.signup);
            auth = FirebaseAuth.getInstance();


            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String e = email.getText().toString();
                    String p = pass.getText().toString();
                    if(!e.equals("")){
                    auth.signInWithEmailAndPassword(e, p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Welcome Back", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, Main_page.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(MainActivity.this, "Sorry, Check Your Email and password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            else{
                        Toast.makeText(MainActivity.this, "Enter Necessary Details", Toast.LENGTH_SHORT).show();
                }
                }
            });


            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String e = email.getText().toString();
                    String p = pass.getText().toString();
                    if(!e.equals("")){
//                    String e = email.getText().toString();
//                    String p = pass.getText().toString();
                    auth.createUserWithEmailAndPassword(e, p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, Main_page.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(MainActivity.this, "Error in creating an account", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });}
                     else{
                            Toast.makeText(MainActivity.this, "Enter Necessary Details", Toast.LENGTH_SHORT).show();
                        }
                }
            });}

        }


@Override
public void onBackPressed() {
    if(backPressedTime+2000> System.currentTimeMillis()){
        super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
        return;
    }else{
        Toast.makeText(getBaseContext(),"Press Back again to Exit",Toast.LENGTH_SHORT).show();
    }
    backPressedTime=System.currentTimeMillis();
}
}


