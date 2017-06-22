package com.example.elolam.safeug;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReportActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_PICK_FILE = 1;
    private int ACTIVITY_START_VIDEO_APP = 0;
    private TextView filePath;
    private ImageView Browse;
    private File selectedFile;
    private VideoView videoHolder;
    private ImageView video;
    private ImageView record;
    private ImageView capture;
    private ImageView imageHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        video = (ImageView)findViewById(R.id.video);
        videoHolder = (VideoView)findViewById(R.id.videoViewid);
        if (video != null) {
            video.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent videoIntent = new Intent();
                    videoIntent.setAction(MediaStore.ACTION_VIDEO_CAPTURE);
                    startActivityForResult(videoIntent,ACTIVITY_START_VIDEO_APP);

                }
            });
        }
        Browse = (ImageView) findViewById(R.id.browse);
        if (Browse!= null) {
            Browse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch(v.getId()) {
                        case R.id.browse:
                            Intent intent = new Intent(ReportActivity.this, FilePicker.class);
                            startActivityForResult(intent, REQUEST_PICK_FILE);
                            break;
                    }
                }
            });
        }
        capture = (ImageView)findViewById(R.id.capture);
        imageHolder = (ImageView)findViewById(R.id.containa);
        if (capture != null) {
            capture.setOnClickListener(new View.OnClickListener() {
                ImageView capture;
                @Override
                public void onClick(View v) {
                        //capture = (ImageView)findViewById(R.id.capture);
                        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
                            capture.setEnabled(false);
                        }
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        //since we a have to return result
                        startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);
                    }
            });
        }
        filePath = (TextView)findViewById(R.id.file_path);
        record = (ImageView)findViewById(R.id.record);
        if (record != null) {
            record.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ReportActivity.this, AudioActivity.class);
                    startActivity(intent);
                }
            });
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////
        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.categoryList);

        // Spinner click listener
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Murder");
        categories.add("Theft");
        categories.add("Domestic Violence");
        categories.add("Sexual Assault");
        categories.add("Terrorism  ");
        categories.add("Drug Abuse");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");
            imageHolder.setImageBitmap(bitmap);
        }
        if(requestCode == ACTIVITY_START_VIDEO_APP && resultCode == RESULT_OK){
            Uri videoUri = data.getData();
            videoHolder.setVideoURI(videoUri);
        }
    }

        protected void onActivityForResult(int requestCode, int resultCode, Intent data){

        if (requestCode == REQUEST_IMAGE_CAPTURE)
            if (resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap photo = (Bitmap) extras.get("data");
                imageHolder.setImageBitmap(photo);
            }

        if(resultCode == RESULT_OK) {

            switch(requestCode) {

                case REQUEST_PICK_FILE:

                    if(data.hasExtra(FilePicker.EXTRA_FILE_PATH)) {

                        selectedFile = new File
                                (data.getStringExtra(FilePicker.EXTRA_FILE_PATH));
                        filePath.setText(selectedFile.getPath());
                    }
                    break;
            }
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}