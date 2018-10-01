package com.test.firebaseml;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import java.util.ArrayList;
import java.util.List;

public class RecognizeTextActivity extends AppCompatActivity {

    private static final String TAG = RecognizeTextActivity.class.getSimpleName();

    // Recognize Text in Images with ML Kit
    FirebaseVisionImage mFirebaseVisionImage;
    FirebaseVisionTextRecognizer mFirebaseVisionTextRecognizer;
    Bitmap mBitmap;

    GraphicOverlay mGraphicOverlay;
    ImageView mImageView;
    LinearLayout linearBottomSheet;
    RecyclerView mRecyclerView;
    LinearLayoutManager mManager;

    TextListAdapter mAdapter;
    List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recognize_text);

        mGraphicOverlay = findViewById(R.id.graphicOverlay);
        mImageView = findViewById(R.id.imageView);

        // get bitmap from drawable
        mBitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.bill);

        mList = new ArrayList<>();
        mFirebaseVisionImage = FirebaseVisionImage.fromBitmap(mBitmap);

        mFirebaseVisionTextRecognizer = FirebaseVision.getInstance().getOnDeviceTextRecognizer();

        mFirebaseVisionTextRecognizer.processImage(mFirebaseVisionImage)
                .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                    @Override
                    public void onSuccess(FirebaseVisionText firebaseVisionText) {
                        Log.i(TAG, "onSuccess: ");

                        String result = firebaseVisionText.getText();
                        Log.i(TAG, "onSuccess: " + result);

                        openBottomSheet();

                        processTextRecognitionResult(firebaseVisionText);


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i(TAG, "onFailure: ");
                    }
                });
    }

    private void processTextRecognitionResult(FirebaseVisionText texts) {
        List<FirebaseVisionText.TextBlock> blocks = texts.getTextBlocks();
        if (blocks.size() == 0) {
            Log.i(TAG, "processTextRecognitionResult: no blocks found");
            return;
        }

        // FirebaseVisionText ---> TextBlocks ---> Lines ---> Elements ---> Words.

        mGraphicOverlay.clear();
        for (int i = 0; i < blocks.size(); i++) {
            List<FirebaseVisionText.Line> lines = blocks.get(i).getLines();
            for (int j = 0; j < lines.size(); j++) {
                List<FirebaseVisionText.Element> elements = lines.get(j).getElements();
                for (int k = 0; k < elements.size(); k++) {
                    GraphicOverlay.Graphic textGraphic = new TextGraphic(mGraphicOverlay, elements.get(k));
                    mGraphicOverlay.add(textGraphic);
                    mList.add(elements.get(k).getText());
                    mAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    private void openBottomSheet() {
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(R.layout.bottom_sheet_text_item);

        linearBottomSheet = dialog.findViewById(R.id.linearBottomSheet);
        mRecyclerView = dialog.findViewById(R.id.mRecyclerView);

        mRecyclerView.setHasFixedSize(true);
        mManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mManager);

        mList = new ArrayList<String>();
        mAdapter = new TextListAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);

        dialog.show();
    }

}
