package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Main_page extends AppCompatActivity {
ListView recyclerView;
recycle recycleAdapter;
customAdapter cd;

    private  long backPressedTime;
    ArrayAdapter adapter;
    ArrayAdapter ma;
    ArrayAdapter t;
ArrayList<String> list;
    ArrayList<String> time;
    ArrayList<String> mail;
TextInputLayout message;
TextView signout;
FloatingActionButton send;
DatabaseReference databaseReference;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        auth = FirebaseAuth.getInstance();
signout  =findViewById(R.id.textView);
        send=findViewById(R.id.fab);


        message = findViewById(R.id.message);
        FirebaseUser user = auth.getCurrentUser();
        String uid=  user.getUid();
        Date currentTime = Calendar.getInstance().getTime();
        recyclerView  =findViewById(R.id.recylce);
        String email=  user.getEmail();
        list = new ArrayList<>();
        mail = new ArrayList<>();
        time = new ArrayList<>();
         adapter =  new ArrayAdapter<>(this,R.layout.dummy,R.id.message,list);

        ma =  new ArrayAdapter<>(this,R.layout.dummy,R.id.email,mail);
        t =  new ArrayAdapter<>(this,R.layout.dummy,R.id.time,time);
//
        LinearLayoutManager lim  = new LinearLayoutManager(this,RecyclerView.VERTICAL,true);

        recyclerView.setAdapter(adapter);



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = message.getEditText().getText().toString();
                String Date = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
if(!msg.equals("")){
                databaseReference  = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("message").push().setValue(new message(email,msg,Date)).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        message.getEditText().setText("");
//
                        adapter.notifyDataSetChanged();

                    }
                });
            }else{
    Toast.makeText(Main_page.this, "Fill the message box ra Anonymous", Toast.LENGTH_SHORT).show();
}
            }


        });
signout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(Main_page.this,MainActivity.class));
    }
});

    }

    @Override
    protected void onStart() {
        super.onStart();
   recevie();
    }

    private  void recevie(){
        databaseReference  = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("message").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren()){

                    message m= snapshot1.getValue(message.class);
//                    mail.add(m.getEmail());
//                    time.add(m.getTime());
                   list.add(m.getEmail()+"\n"+m.getMessage()+"  "+m.getTime());
                   adapter.notifyDataSetChanged();
//                   ma.notifyDataSetChanged();
//                   t.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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