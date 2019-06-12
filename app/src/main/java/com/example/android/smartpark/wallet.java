package com.example.android.smartpark;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class wallet extends AppCompatActivity {
    private TextView bal;
    private EditText addm;
    private Button sub;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference ddatabaseReference;
    private ImageButton im1;
    private ImageButton im2;
    private ImageButton im3;
    private ImageButton im4;
    private ImageButton im5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        Intent i=getIntent();
        bal=(TextView)findViewById(R.id.balance);
        addm=(EditText)findViewById(R.id.addmoney);
        sub=(Button)findViewById(R.id.put);
        firebaseAuth= FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        final String Uid=user.getUid();
        im1=(ImageButton)findViewById(R.id.im1);
        im2=(ImageButton)findViewById(R.id.im2);
        im3=(ImageButton)findViewById(R.id.im3);
        im4=(ImageButton)findViewById(R.id.im4);
        im5=(ImageButton)findViewById(R.id.im5);
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), history.class));
            }
        });
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), search.class));
            }
        });
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), maps.class));
            }
        });
        im4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), wallet.class));
            }
        });
        im5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), personal.class));
            }
        });




        DatabaseReference databaseReference;
        databaseReference= FirebaseDatabase.getInstance().getReference("Wallet").child(Uid);
         final String v[]=new String[2];
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String prof=dataSnapshot.getValue(String.class);
                if(prof==null){
                   // Toast.makeText(personal.this,"Please update your profile",Toast.LENGTH_LONG).show();
                }
                else {
                    v[0]=prof;
                    bal.setText("Your Current Balance is :  "+prof );
                   /* name.setText(prof.getname());
                    age.setText(prof.getage());
                    gender.setText(prof.getGender());
                    email.setText(prof.getemail());
                    mobile.setText(prof.getmobile());
                   */ /*Toast.makeText(view_profile.this, prof.getfirstName(), Toast.LENGTH_LONG).show();
                    if (!prof.getimage().equals(" ")) {
                        Glide.with(view_profile.this).load(prof.getimage()).into(picture);
                        picture.setAlpha(1f);
                    } else {
                        picture.setAlpha(0.8f);
                    }
                    */
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(wallet.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
sub.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String x=addm.getText().toString().trim();
        int result = Integer.parseInt(x);
        int result2=0 ;
        if(v[0]!=null){
        result2= Integer.parseInt(v[0]);}
        result+=result2;
        String puttin=result+"";

        ddatabaseReference = FirebaseDatabase.getInstance().getReference("Wallet").child(Uid);
        ddatabaseReference.setValue(puttin);
        Toast.makeText(wallet.this, "added successfully", Toast.LENGTH_SHORT).show();

    }
});

    }
}
