package com.example.elolam.safeug;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {
    //set duration of the splash screen
    long delay = 5000;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove the title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Get the view for splash screen
        setContentView(R.layout.activity_splash_screen);
        //create the timer
        Timer time = new Timer();
        //task to do when a timer ends
        TimerTask showsplash = new TimerTask() {
            @Override
            public void run() {
                //close the splasth screen activity
                finish();
                //start MainActivity.class
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
            }
        };
        time.schedule(showsplash,delay);
    }
}
