package com.mbd.qr_scanner_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.Result;

import java.util.HashMap;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanCodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private DatabaseReference mDatabase;
    public   static HashMap<String, String> user = new HashMap<String, String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        ScannerView = new ZXingScannerView( this );
        setContentView(ScannerView);
    }
    ZXingScannerView ScannerView;
    @Override
    public void handleResult(Result result) {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        try {

            mDatabase.child("users").child(result.getText()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                    MainActivity.resultTextView.setText(result.getText());
                    onBackPressed();
                }
                else {

                    user = (HashMap<String, String>) task.getResult().getValue();

                    startActivity(new Intent(getApplicationContext(),Profile.class));
                    Log.d("firebase", String.valueOf(user));

                }
            }
        });
        }catch (Exception e){
            Log.e("firebase  error ", "Error getting data",e);
            MainActivity.resultTextView.setText(result.getText());
            onBackPressed();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        ScannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ScannerView.setResultHandler(this);
        ScannerView.startCamera();
    }
}