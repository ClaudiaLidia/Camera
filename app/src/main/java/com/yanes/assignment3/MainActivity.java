package com.yanes.assignment3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements View.OnClickListener {
    private static final int RESULT_LOAD_IMAGE= 1;
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private com.yanes.assignment3.MyView my;
    private Button bUploadImage;
   private com.yanes.assignment3.MyView mv;
    private Button btnAdd;
    private Button b;
    ImageView imageToUpload;
    public static String type;
    public static int color;
    public static int thicknesses;
    private AlertDialog dialog;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setupView();
        imageToUpload= (ImageView) findViewById(R.id.image_view);
        my= (com.yanes.assignment3.MyView) findViewById(R.id.myW);
        mv = (com.yanes.assignment3.MyView) findViewById(R.id.myW);
        bUploadImage = (Button) findViewById(R.id.bUploadImage);
        bUploadImage.setOnClickListener(this);
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);
        b = (Button) findViewById(R.id.btn_delete);
        b.setOnClickListener(this);
    }
    public void setupView(){
        btnSave.setOnClickListener(this);
    }
    public void init(){
        imageView= (ImageView) findViewById(R.id.image_view);
        my =(com.yanes.assignment3.MyView) findViewById(R.id.myW);
        btnSave= (Button)findViewById(R.id.btn_save);
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
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setTitle("Pick The Thicknesses of Your Widget");

// add a list
                String[] widget2 = {"1dp", "3dp", "5dp", "10dp","20dp"};
                builder2.setItems(widget2, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which1) {

                        switch (which1) {

                            case 0:thicknesses= 1;   break;
                            case 1:thicknesses= 3;   break;
                            case 2:thicknesses = 5; break;
                            case 3:thicknesses = 10; break;
                            case 4:thicknesses = 20; break;

                        }
                    }
                });
                AlertDialog dialog2 = builder2.create();

                dialog2.show();

                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle("Pick The Color of Your Widget");

// add a list
                String[] widget1 = {"Red", "Blue", "Green", "Yellow","Black"};
                builder1.setItems(widget1, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which1) {

                        switch (which1) {

                            case 0:color= Color.RED;   break;
                            case 1:color= Color.BLUE;   break;
                            case 2:color=Color.GREEN; break;
                            case 3:color=Color.YELLOW; break;
                            case 4:color=Color.BLACK;  break;

                        }
                    }
                });
                AlertDialog dialog1 = builder1.create();

                dialog1.show();

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Pick Your Widget");

// add a list
                String[] widget = {"Line", "Rectangle", "Point", "Check"};
                builder.setItems(widget, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {

                            case 0:type="Line";    break;
                            case 1:type="Rectangle";   break;
                            case 3:  type="Point";     break;
                            case 2:type="Check";      break;
                        }

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
                mv.addB2();
                break;

            case R.id.btn_delete:
                mv.delete();
                break;


        }
    }
}
