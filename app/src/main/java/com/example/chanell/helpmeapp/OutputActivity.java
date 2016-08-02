package com.example.chanell.helpmeapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.wolfram.alpha.WAEngine;
import com.wolfram.alpha.WAException;
import com.wolfram.alpha.WAPlainText;
import com.wolfram.alpha.WAPod;
import com.wolfram.alpha.WAQuery;
import com.wolfram.alpha.WAQueryResult;
import com.wolfram.alpha.WASubpod;
import com.nostra13.*;

/**
 * Created by YolandaBreakfast on 2016/07/26.
 */
public class OutputActivity extends MainActivity{
    ImageLoader imageLoader = ImageLoader.getInstance();
    Bitmap bitmap = imageLoader.loadImageSync("http://www4c.wolframalpha.com/Calculate/MSP/MSP14901i2ed45e31ab3912000042h81a3bc99add2e?MSPStoreType=image/gif&amp;s=61");



    private static final String TAG = OutputActivity.class.getSimpleName();
    TextView output;


    // Load image, decode it to Bitmap and return Bitmap to callback
    // Load image, decode it to Bitmap and return Bitmap synchronously
    Bitmap bmp = imageLoader.loadImageSync("http://www4c.wolframalpha.com/Calculate/MSP/MSP14901i2ed45e31ab3912000042h81a3bc99add2e?MSPStoreType=image/gif&amp;s=61");


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        output = (TextView) findViewById(R.id.Output);

        String query = getIntent().getStringExtra("Query");
         makeRequest(query);


    }
    public void makeRequest(String input) {
        new AsyncTask<String, Void, WAQueryResult>() {
            @Override
            protected WAQueryResult doInBackground(String... params) {
                String input = params[0];

                WAEngine engine = new WAEngine();

                // These properties will be set in all the WAQuery objects created from this WAEngine.
                engine.setAppID("X3VUE5-3664XL6KKA");
                engine.addFormat("plaintext");
                engine.addFormat("image");

                // Create the query.
                WAQuery query = engine.createQuery();

                // Set properties of the query.
                query.setInput(input);

                try {
                    // For educational purposes, print out the URL we are about to send:
                    Log.d(TAG, "Query URL: " + engine.toURL(query));

                    // This sends the URL to the Wolfram|Alpha server, gets the XML result
                    // and parses it into an object hierarchy held by the WAQueryResult object.
                    WAQueryResult queryResult = engine.performQuery(query);

                    return queryResult;
                } catch (WAException e) {
                    Log.e(TAG, e.getMessage(), e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(WAQueryResult queryResult) {

                super.onPostExecute(queryResult);
                if (queryResult != null) {
                    if (queryResult.isError()) {
                        Log.d(TAG, "Query error");
                        Log.d(TAG, "  error code: " + queryResult.getErrorCode());
                        Log.d(TAG, "  error message: " + queryResult.getErrorMessage());
                    } else if (!queryResult.isSuccess()) {
                        Log.d(TAG, "Query was not understood; no results available.");
                    } else {
                        // Got a result.
                        Log.d(TAG, "Successful query. Pods follow:\n");
                        for (WAPod pod : queryResult.getPods()) {
                            if (!pod.isError()) {
                                Log.d(TAG, pod.getTitle());
                                Log.d(TAG, "------------");
                                for (WASubpod subpod : pod.getSubpods()) {
                                    for (Object element : subpod.getContents()) {
                                        //TIP: Load image here
                                        String imageUri = "http://www4c.wolframalpha.com/Calculate/MSP/MSP14901i2ed45e31ab3912000042h81a3bc99add2e?MSPStoreType=image/gif&amp;s=61";
                                        imageLoader.displayImage(bitmap,findViewById(R.id.trying));

                                        if (element instanceof WAPlainText) {

                                            Log.d(TAG, ((WAPlainText) element).getText());
                                            output.append(((WAPlainText) element).getText() + "\n");

                                        }
                                    }
                                }
                            }
                        }
                        // We ignored many other types of Wolfram|Alpha output, such as warnings, assumptions, etc.
                        // These can be obtained by methods of WAQueryResult or objects deeper in the hierarchy.
                    }
                } else {
                    Log.d(TAG, "Query error");
                }
            }
        }.execute(input);
    }
}
