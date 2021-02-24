package com.mbd.qr_scanner_app;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class Profile extends AppCompatActivity {

    public static TextView name;
    public static TextView email;
    public static TextView cin;
    public static TextView phone;
    public static TextView date;
    public  static ImageView avatar;
    Button close;
    HashMap<String, String> data = new HashMap<String, String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);
        getSupportActionBar().hide();
        name = (TextView)findViewById(R.id.name);
        email = (TextView)findViewById(R.id.email);
        phone = (TextView)findViewById(R.id.phone);
        cin = (TextView)findViewById(R.id.cin);
        date = (TextView)findViewById(R.id.date);
        avatar = (ImageView)findViewById(R.id.avatar);
        close = (Button)findViewById(R.id.close);

        close.setOnClickListener((new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        } ));

        data = ScanCodeActivity.user;
        Profile.name.setText(data.get("nom") + " "+ data.get("prenom"));
        Profile.email.setText(data.get("email"));
        Profile.cin.setText(data.get("cin"));
        Profile.phone.setText(data.get("phone"));
        Profile.date.setText(data.get("Date"));

        if(data.get("Gender").equals("female") ){
                avatar.setImageResource(R.drawable.female);
    }else if(data.get("Gender").equals("male")){
                avatar.setImageResource(R.drawable.male);
        }else{
                avatar.setImageResource(R.drawable.unknown);
        }

    }

}
