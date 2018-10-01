package com.test.firebaseml;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnRecognizeText, btnDetectFace, btnScanBarCode, btnLabelImage, btnRecognizeLandmark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRecognizeText = findViewById(R.id.btnRecognizeText);
        btnDetectFace = findViewById(R.id.btnDetectFace);
        btnScanBarCode = findViewById(R.id.btnScanBarCode);
        btnLabelImage = findViewById(R.id.btnLabelImage);
        btnRecognizeLandmark = findViewById(R.id.btnRecognizeLandmark);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRecognizeText:
                 startActivity(new Intent(this, RecognizeTextActivity.class));
                break;
            case R.id.btnDetectFace:
                startActivity(new Intent(this, DetectFaceActivity.class));
                break;
            case R.id.btnScanBarCode:
                startActivity(new Intent(this, ScanBarCodeActivity.class));
                break;
            case R.id.btnLabelImage:
                startActivity(new Intent(this, LabelImageActivity.class));
                break;
            case R.id.btnRecognizeLandmark:
                startActivity(new Intent(this, RecognizeLanmarksActivity.class));
                break;
        }
    }
}
