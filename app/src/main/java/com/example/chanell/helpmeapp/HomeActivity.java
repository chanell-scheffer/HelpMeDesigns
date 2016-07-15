package com.example.chanell.helpmeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

public class HomeActivity extends AppCompatActivity {


    Button getStartedButton;

    public void zoom(View view) {
        TextView textView = (TextView) findViewById(R.id.WelcomeTo);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.jump);
        textView.startAnimation(animation);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getStartedButton = (Button) findViewById(R.id.get_started_button);
        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, IntroductionActivity.class);
                startActivity(intent);
            }
        });

        TextView textView = (TextView) findViewById(R.id.WelcomeTo);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.jump);
        animation.setRepeatCount(Animation.INFINITE);
        textView.startAnimation(animation);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
