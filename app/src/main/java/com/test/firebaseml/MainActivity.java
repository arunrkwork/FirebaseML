package com.test.firebaseml;

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

                break;
            case R.id.btnDetectFace:

                break;
            case R.id.btnScanBarCode:

                break;
            case R.id.btnLabelImage:

                break;
            case R.id.btnRecognizeLandmark:

                break;
        }
    }
}
