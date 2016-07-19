package com.example.chanell.helpmeapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;

public class MainActivity extends Activity {

    private static final int CAMERA_REQUEST = 1888;
    ImageButton camera;
    private File imageFile;


//     public void onClick(View v){
//         i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//         startActivityForResult(i,camera);
//     }

    private GoogleApiClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        camera = (ImageButton) this.findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }

//            public void picture(View view) {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                imageFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"image.jpg");
//                Uri value = Uri.fromFile(imageFile);
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, value);
//                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
//                startActivityForResult(intent, 0);
//            }
//

        });

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    if (imageFile.exists()) {
                        Toast.makeText(this, "Saved " + imageFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "Error saving ", Toast.LENGTH_LONG).show();
                    }

                    break;
                case Activity.RESULT_CANCELED:
                    break;

                default:
                    break;


            }

       if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
           Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            camera.setImageBitmap(imageBitmap);
       }
        }
    }


    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.chanell.helpmeapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);


        Camera camera = new Camera();

    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.chanell.helpmeapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}