package com.example.chanell.helpmeapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.icu.util.Output;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.wolfram.alpha.WAEngine;
import com.wolfram.alpha.WAException;
import com.wolfram.alpha.WAPlainText;
import com.wolfram.alpha.WAPod;
import com.wolfram.alpha.WAQuery;
import com.wolfram.alpha.WAQueryResult;
import com.wolfram.alpha.WASubpod;
import com.wolfram.alpha.test.Main;

import java.io.File;

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int CAMERA_REQUEST = 1888;
    ImageButton camera;
    TextView output;
    private File imageFile;


//    OutputActivity output = new OutputActivity();


    private GoogleApiClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output =(TextView) findViewById(R.id.Output);

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

       public void Window() {

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    }
    public void click(View view) {
        switch (view.getId()) {
            case R.id.algebra:
                Intent algebraIntent = new Intent(MainActivity.this,OutputActivity.class);
                algebraIntent.putExtra("Query","pi");
                startActivity(algebraIntent);
//                makeRequest("pi");
                break;

            case R.id.applied_math:
                Intent appliedIntent = new Intent(MainActivity.this,OutputActivity.class);
                appliedIntent.putExtra("Query","log(10)");
                startActivity(appliedIntent);
                break;

            case R.id.calculus:
                Intent calculusIntent = new Intent(MainActivity.this,OutputActivity.class);
                calculusIntent.putExtra("Query", "Calculus Math");
                startActivity(calculusIntent);
                break;

            case R.id.definition:
                Intent definitionIntent = new Intent(MainActivity.this,OutputActivity.class);
                definitionIntent.putExtra("Query", "Definition Math");
                startActivity(definitionIntent);
                break;

            case R.id.discrete_math:
                Intent discreteMathIntent = new Intent(MainActivity.this,OutputActivity.class);
                discreteMathIntent.putExtra("Query", "Discrete Math");
                startActivity(discreteMathIntent);
                break;

            case R.id.elementary_math:
                Intent elementaryIntent = new Intent(MainActivity.this,OutputActivity.class);
                elementaryIntent.putExtra("Query", "Elementary Math");
                startActivity(elementaryIntent);
                break;

            case R.id.ellipses:
                Intent ellipsesIntent = new Intent(MainActivity.this,OutputActivity.class);
                ellipsesIntent.putExtra("Query","Ellipses Math");
                startActivity(ellipsesIntent);
                break;

            case R.id.functions:
                Intent functionsIntent = new Intent(MainActivity.this,OutputActivity.class);
                functionsIntent.putExtra("Query","Functions Math");
                startActivity(functionsIntent);
                break;

            case R.id.geometry:
                Intent geometryIntent = new Intent(MainActivity.this,OutputActivity.class);
                geometryIntent.putExtra("Query", "Geometry Math");
                startActivity(geometryIntent);
                break;

            case R.id.logic:
                Intent logicIntent = new Intent(MainActivity.this,OutputActivity.class);
                logicIntent.putExtra("Query", "Logic Math");
                startActivity(logicIntent);
                break;

            case R.id.matrices:
                Intent matricesIntent = new Intent(MainActivity.this,OutputActivity.class);
                matricesIntent.putExtra("Query", "Matrices Math");
                startActivity(matricesIntent);
                break;

            case R.id.numbers:
                Intent numbersIntent = new Intent(MainActivity.this,OutputActivity.class);
                numbersIntent.putExtra("Query","Numbers Math");
                startActivity(numbersIntent);
                break;

            case R.id.number_theory:
                Intent numberTheoryIntent = new Intent(MainActivity.this,OutputActivity.class);
                numberTheoryIntent.putExtra("Query", "Number Theory Math");
                startActivity(numberTheoryIntent);
                break;

            case R.id.plotting:
                Intent plottingIntent = new Intent(MainActivity.this,OutputActivity.class);
                plottingIntent.putExtra("Query", "Plotting Math");
                startActivity(plottingIntent);
                break;

            case R.id.trigonometry :
                Intent trigIntent = new Intent(MainActivity.this,Output.class);
                trigIntent.putExtra("Query", "Trigonometry Math");
                startActivity(trigIntent);
                break;

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