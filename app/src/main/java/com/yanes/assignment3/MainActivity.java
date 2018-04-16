package com.yanes.assignment3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button bUploadImage;
    private Button btnAdd;
    private Button b;

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
                break;
            case R.id.btn_add:
                break;
            case R.id.btn_delete:
                break;


        }
    }
}
