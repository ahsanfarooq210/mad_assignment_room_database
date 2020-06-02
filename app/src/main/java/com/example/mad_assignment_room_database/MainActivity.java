package com.example.mad_assignment_room_database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private RelativeLayout rellay1,rally2;

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {

            rellay1.setVisibility(View.VISIBLE);
            rally2.setVisibility(View.VISIBLE);


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rellay1 = findViewById(R.id.rellay1);
        rally2=findViewById(R.id.bottom_rally2);

        handler.postDelayed(runnable, 2000);

    }

    public void newData(View view)
    {
        startActivity(new Intent(MainActivity.this,new_data_activity.class));
    }

    public void todayReport(View view)
    {
        startActivity(new Intent(MainActivity.this,todays_report_activity.class));
    }

    public void monthlyReport(View view)
    {
        startActivity(new Intent(MainActivity.this,monthly_report_activity.class));
    }
}
