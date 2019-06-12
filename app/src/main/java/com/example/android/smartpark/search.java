package com.example.android.smartpark;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class search extends AppCompatActivity {
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;
    private TextView tv8;
    private ImageButton im1;
    private ImageButton im2;
    private ImageButton im3;
    private ImageButton im4;
    private ImageButton im5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent i=getIntent();
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


        tv1=(TextView)findViewById(R.id.place1);
        tv2=(TextView)findViewById(R.id.place2);
        tv3=(TextView)findViewById(R.id.place3);
        tv4=(TextView)findViewById(R.id.place4);
        tv5=(TextView)findViewById(R.id.place5);
        tv6=(TextView)findViewById(R.id.place6);
        tv7=(TextView)findViewById(R.id.place7);
        tv8=(TextView)findViewById(R.id.place8);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(getApplicationContext(), book.class));
                Intent i = new Intent(search.this, book.class);
                i.putExtra("key","place1");
                startActivity(i);
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(getApplicationContext(), book.class));
                Intent i = new Intent(search.this, book.class);
                i.putExtra("key","place2");
                startActivity(i);
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(getApplicationContext(), book.class));
                Intent i = new Intent(search.this, book.class);
                i.putExtra("key","place3");
                startActivity(i);
            }
        });
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(getApplicationContext(), book.class));
                Intent i = new Intent(search.this, book.class);
                i.putExtra("key","place4");
                startActivity(i);
            }
        });
        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(getApplicationContext(), book.class));
                Intent i = new Intent(search.this, book.class);
                i.putExtra("key","place5");
                startActivity(i);
            }
        });
        tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(getApplicationContext(), book.class));
                Intent i = new Intent(search.this, book.class);
                i.putExtra("key","place6");
                startActivity(i);
            }
        });
        tv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(getApplicationContext(), book.class));
                Intent i = new Intent(search.this, book.class);
                i.putExtra("key","place7");
                startActivity(i);
            }
        });
        tv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(getApplicationContext(), book.class));
                Intent i = new Intent(search.this, book.class);
                i.putExtra("key","place8");
                startActivity(i);
            }
        });

    }
}
