package com.example.android.smartpark;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class personal extends AppCompatActivity {
  // private EditText gender;
    private EditText age;
    private EditText email;
    private EditText mobile;
    private EditText name;
    private FirebaseAuth firebaseAuth;
    private userprofile prof;
    private Button submit;
    private Button car;
    private ImageButton im1;
    private ImageButton im2;
    private ImageButton im3;
    private ImageButton im4;
    private ImageButton im5;
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    private Button btnDisplay;

    private DatabaseReference mDatabaseRef;
    ListView listview;
    ArrayList<String> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        Intent i=getIntent();
    //    gender=(EditText)findViewById(R.id.gender);
        age=(EditText)findViewById(R.id.age);
        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        mobile=(EditText)findViewById(R.id.mobile);
        submit=(Button)findViewById(R.id.submit);
        car=(Button)findViewById(R.id.car);
        radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);
        /*btnDisplay = (Button) findViewById(R.id.btnDisplay);

        btnDisplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = radioSexGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioSexButton = (RadioButton) findViewById(selectedId);

                Toast.makeText(personal.this,
                        radioSexButton.getText(), Toast.LENGTH_SHORT).show();

            }

        });

    }*/
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


        listview=(ListView)findViewById(R.id.listview);
        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,list);
        listview.setAdapter(adapter);


        firebaseAuth= FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
       final String Uid=user.getUid();
        DatabaseReference databaseReference;
        databaseReference= FirebaseDatabase.getInstance().getReference("Users_Profile").child(Uid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                prof=dataSnapshot.getValue(userprofile.class);
                if(prof==null){
                    Toast.makeText(personal.this,"Please update your profile",Toast.LENGTH_LONG).show();
                }
                else {
                    name.setText(prof.getname());
                    age.setText(prof.getage());
      //              gender.setText(prof.getGender());
                    if(prof.getGender().equalsIgnoreCase("MALE"))
                    {
                        radioSexGroup.check(R.id.radioMale);
                    }
                    else
                    {
                        radioSexGroup.check(R.id.radioFemale);
                    }
                    email.setText(prof.getemail());
                    mobile.setText(prof.getmobile());
                    /*Toast.makeText(view_profile.this, prof.getfirstName(), Toast.LENGTH_LONG).show();
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
                Toast.makeText(personal.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        DatabaseReference cdatabaseReference;
        cdatabaseReference= FirebaseDatabase.getInstance().getReference("Cars").child(Uid);
        cdatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                list.add(dataSnapshot.getValue(String.class));
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                list.remove(dataSnapshot.getValue(String.class));
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userprofile person=new userprofile(Uid,name.getText().toString().trim(),email.getText().toString().trim());
                int selectedId = radioSexGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioSexButton = (RadioButton) findViewById(selectedId);

                person.setprofile(name.getText().toString().trim(),email.getText().toString().trim(),age.getText().toString().trim(),mobile.getText().toString().trim(),radioSexButton.getText().toString().trim());
                        //setprofile(String name,String email,String age,String mobile,String gender)
                mDatabaseRef = FirebaseDatabase.getInstance().getReference("Users_Profile").child(Uid);
                mDatabaseRef.setValue(person);
                Toast.makeText(personal.this, "submitted successfully", Toast.LENGTH_SHORT).show();
            }
        });

     car.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             startActivity(new Intent(getApplicationContext(), confirmcar.class));
         }
     });
    }
}
