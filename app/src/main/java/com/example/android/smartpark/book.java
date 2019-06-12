package com.example.android.smartpark;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.util.Calendar;

public class book extends AppCompatActivity {
    private EditText hour;
    private Button book;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    //private FirebaseAuth firebaseAuth;
    private DatabaseReference ddatabaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        Intent i=getIntent();
        final String place = i.getStringExtra("key");
        firebaseAuth= FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        final String Uid=user.getUid();
        DatabaseReference edatabaseReference;
        edatabaseReference= FirebaseDatabase.getInstance().getReference("Wallet").child(Uid);
        final String v[]=new String[2];
        edatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String prof=dataSnapshot.getValue(String.class);
                if(prof==null){
                    // Toast.makeText(personal.this,"Please update your profile",Toast.LENGTH_LONG).show();
                }
                else {
                    v[0]=prof;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(book.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        hour=(EditText)findViewById(R.id.hour);
        book=(Button)findViewById(R.id.book);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amt=hour.getText().toString().trim();
                int result = Integer.parseInt(amt);
                int tot=result*15;

                int x;
                if(v[0]==null)x=0;
                else
                    x=Integer.parseInt(v[0]);

                if(x<tot)
                    Toast.makeText(getApplicationContext(),"SORRY INSUFFICIENT BALANCE",Toast.LENGTH_LONG).show();
                else {
                     x=x-tot;
                     String xx=x+"";
                    ddatabaseReference = FirebaseDatabase.getInstance().getReference("Wallet").child(Uid);
                    ddatabaseReference.setValue(xx);
                    Calendar now = Calendar.getInstance();
                    int year = now.get(Calendar.YEAR);
                    int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
                    int day = now.get(Calendar.DAY_OF_MONTH);
                    String insert = day + "/" + month + "        " + place + "     " + result + "    " + tot;
                    databaseReference = FirebaseDatabase.getInstance().getReference("Booking").child(Uid);
                    databaseReference.push().setValue(insert);
                    Toast.makeText(book.this, "submitted successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
