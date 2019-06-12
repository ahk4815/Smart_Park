package com.example.android.smartpark;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class confirmcar extends AppCompatActivity {
    private EditText rfid;
    private Button confirm;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmcar);
        rfid=(EditText)findViewById(R.id.rfid);
        confirm=(Button)findViewById(R.id.confirm);
        firebaseAuth= FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        final String Uid=user.getUid();
        radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rfid.getText().toString().trim() != "") {
                    car newcar = new car(rfid.getText().toString().trim());
                    int selectedId = radioSexGroup.getCheckedRadioButtonId();

                    // find the radiobutton by returned id
                    radioSexButton = (RadioButton) findViewById(selectedId);
                    String x=radioSexButton.getText().toString().trim();
                    databaseReference = FirebaseDatabase.getInstance().getReference("Cars").child(Uid);
                    databaseReference.push().setValue(rfid.getText().toString().trim()+"      "+x);

                    Toast.makeText(confirmcar.this, "submitted successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
