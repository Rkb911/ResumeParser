package com.example.resumeparser;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {

    private static final String urlFetch = "https://resume-parserapp.herokuapp.com/api/resume-list/";

    RecyclerView recyclerView;
    FloatingActionButton fabUpload;

    //This is for asking Storage Permission
    private static final int MY_RESULT_CODE_FILECHOOSER = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        fabUpload = findViewById(R.id.uploadButton);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fabUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UploadFile();

            }
        });

        ProcessData();

    }


    // Storage Permission checker to be implemented later

    /*private void askPermissionAndUploadFile()  {
        // With Android Level >= 23, you have to ask the user
        // for permission to access External Storage.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) { // Level 23

            // Check if we have Call permission
            int permission = ActivityCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE);

            if (permission != PackageManager.PERMISSION_GRANTED) {
                // If don't have permission so prompt the user.
                this.requestPermissions(
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_REQUEST_CODE_PERMISSION
                );
                return;
            }
        }
        this.UploadFile();
    }*/


    //This will Provide Info of the selected File
    ActivityResultLauncher<Intent> intentActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Toast.makeText(MainActivity.this, result.toString(), Toast.LENGTH_LONG).show();
                }
            }
    );


    //File Picker       Work In Progress.........
    private void UploadFile() {


        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("*/*");
        String [] mimeTypes = {"application/msword","application/vnd.openxmlformats-officedocument.wordprocessingml.document","text/plain","application/pdf"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
        intentActivityResultLauncher.launch(intent);
        //startActivityForResult(Intent.createChooser(intent,"xD"), MY_RESULT_CODE_FILECHOOSER);
    }


    //Access data from API
    public void ProcessData() {


        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());


        StringRequest request=new StringRequest(urlFetch, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                GsonBuilder builder=new GsonBuilder();
                Gson gson=builder.create();
                Models data[] = gson.fromJson(response, Models[].class);

                MyAdapter adapter = new MyAdapter(data);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        }
        );

        queue.add(request);


    }
}