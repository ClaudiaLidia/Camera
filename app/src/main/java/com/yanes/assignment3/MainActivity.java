package com.yanes.assignment3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {
    private static final int RESULT_LOAD_IMAGE= 1;
    private static final int CAMERA_REQUEST = 1888;
    private Button bUploadImage;
    private Button btnAdd;
    private Button b;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bUploadImage = (Button) findViewById(R.id.bUploadImage);
        bUploadImage.setOnClickListener(this);
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);
        b = (Button) findViewById(R.id.btn_delete);
        b.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {


            case R.id.bUploadImage:
                dialog= new AlertDialog.Builder(this).create();
                dialog.setTitle("Upload image");
                dialog.setMessage("From where do you want to upload your image?");
                dialog.setButton("Gallery", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                    }
                });
                dialog.setButton2("Camera", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_REQUEST);
                    }
                });
                dialog.show();
                break;
            case R.id.btn_add:

                break;
            case R.id.btn_delete:
                break;


        }
    }
}
