package com.example.elolam.safeug;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

        GridView grid;
        String[] web = {
                "Report Incident",
                "Emegency Call",
                "Emergency Sms",
                "Statistics"
        } ;
        int[] imageId = {
                R.drawable.handcuffs,
                R.drawable.report,
                R.drawable.sms,
                R.drawable.statistics,
        };

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            CustomMenuAdapter adapter = new CustomMenuAdapter(MainActivity.this, web, imageId);
            grid=(GridView)findViewById(R.id.grid);
            assert grid != null;
            grid.setAdapter(adapter);
            grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                   // Toast.makeText(MainActivity.this, "You Clicked at " + web[+position], Toast.LENGTH_SHORT).show();
                    if (position == 0) {
                        Intent intent = new Intent(MainActivity.this, ReportActivity.class);
                        startActivity(intent);
                    } else if (position == 1) {
                        Intent intent = new Intent(MainActivity.this, CallActivity.class);
                        startActivity(intent);
                    } else if (position == 2) {
                        Intent intent = new Intent(MainActivity.this, MessageActivity.class);
                        startActivity(intent);
                    } else if (position == 3) {
                        Intent intent = new Intent(MainActivity.this, StatisticsActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
}
