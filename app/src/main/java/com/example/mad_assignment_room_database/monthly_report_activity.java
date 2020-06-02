package com.example.mad_assignment_room_database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;

import java.util.Calendar;

public class monthly_report_activity extends AppCompatActivity
{
//    private EditText toDay,toMonth,toYear,fromDay,fromMonth,fromYear;
    private boolean flag1=false,flag2=false;
    private LinearLayout linearLayout;

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private DatePickerDialog.OnDateSetListener mDateSetListener1;
    private Intent intent;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {

            //to do
            linearLayout.setVisibility(View.VISIBLE);


        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_report_activity);
        linearLayout=findViewById(R.id.splash_monthly);
//        toDay=findViewById(R.id.monthly_report_to_day);
//        toMonth=findViewById(R.id.monthly_report_to_month);
//        toYear=findViewById(R.id.monthly_report_to_year);
//        fromDay=findViewById(R.id.monthly_report_from_day);
//        fromMonth=findViewById(R.id.monthly_report_from_month);
//        fromYear=findViewById(R.id.monthly_report_from_year);
            handler.postDelayed(runnable,500);
        intent=new Intent(this,monthly_report_form_activity.class);
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                //month = month + 1;
                intent.putExtra("fromday",day);
                intent.putExtra("frommonth",month);
                intent.putExtra("fromyear",year);
            }
        };

        mDateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                //month = month + 1;
                intent.putExtra("today",day);
                intent.putExtra("tomonth",month);
                intent.putExtra("toyear",year);
            }
        };



    }

    public void showReport(View view)
    {
//        if(toDay.getText().toString().length()==0)
//        {
//            toDay.setError("fill this field");
//            return;
//        }
//        if(toMonth.getText().toString().length()==0)
//        {
//           toMonth .setError("fill this field");
//            return;
//        }
//        if(toYear.getText().toString().length()==0)
//        {
//            toYear.setError("fill this field");
//            return;
//        }
//        if(fromDay.getText().toString().length()==0)
//        {
//           fromDay .setError("fill this field");
//            return;
//        }
//        if(fromMonth.getText().toString().length()==0)
//        {
//           fromMonth .setError("fill this field");
//            return;
//        }
//        if(fromYear.getText().toString().length()==0)
//        {
//           fromYear .setError("fill this field");
//            return;
//        }





        if(flag1==true&&flag2==true)
        {
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "please enter both start and end date", Toast.LENGTH_SHORT).show();
        }




    }

    public void fromDate(View view)
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                monthly_report_activity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener,
                year,month,day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        flag1=true;
    }

    public void toDate(View view)
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                monthly_report_activity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener1,
                year,month,day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        flag2=true;
    }
}
