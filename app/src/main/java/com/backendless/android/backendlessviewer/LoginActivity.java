package com.backendless.android.backendlessviewer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
    }

    private void initViews() {
        Button scanButton = findViewById(R.id.scan_btn);
        scanButton.setOnClickListener(this::scanQrCode);
    }

    private void scanQrCode(View v) {
        new IntentIntegrator(this)
            .setBeepEnabled(false)
            .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            .setPrompt("Please scan the QR code")
            .initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                login(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void login(String contents) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
