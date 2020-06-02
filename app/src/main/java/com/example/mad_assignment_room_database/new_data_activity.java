package com.example.mad_assignment_room_database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mad_assignment_room_database.Entity.ExpenseEntity;
import com.example.mad_assignment_room_database.Entity.ProfitEntity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class new_data_activity extends AppCompatActivity
{
    private Calendar currentDate;
    private Date date;
    private LinearLayout linearLayout;
    private String previousDate;
    private static final String SHARED_PREFS="shared preferences";
    private static final String IS_PROF_ENTERED="profit entered?";
    private static final String PREVIOUS_DATE="previous date";
    private SharedPreferences sharedPreferences;
    private EditText profitet,nameet,expenseet;
    private ExpenseDatabase database;
    private SharedPreferences.Editor editor;
    private boolean isprofitEntered=false;

    private static int profit=0;

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
        setContentView(R.layout.activity_new_data_activity);

        currentDate=Calendar.getInstance();

        sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        editor=sharedPreferences.edit();
        linearLayout=findViewById(R.id.splash_newdate);
        date=currentDate.getTime();
       // int day=sharedPreferences.getInt(DAY,0),month=sharedPreferences.getInt(MONTH,0),year=sharedPreferences.getInt(YEAR,0);

        profitet=findViewById(R.id.todays_profit);
        nameet=findViewById(R.id.expense_name);
        expenseet=findViewById(R.id.expense_amount);
        database=ExpenseDatabase.getInstance(this);
        handler.postDelayed(runnable,500);
        final Date date4=new Date();
        date4.setYear(2021);
        isprofitEntered=sharedPreferences.getBoolean(IS_PROF_ENTERED,false);
        if(sharedPreferences.getString(PREVIOUS_DATE,"")=="")
        {
            editor.putString(PREVIOUS_DATE,dateToString(date4));
            editor.commit();
        }
        else
        {

                previousDate=sharedPreferences.getString(PREVIOUS_DATE,"");

        }
        if(!dateToString(date).equals(previousDate))
        {
            isprofitEntered=false;
            editor.putBoolean(IS_PROF_ENTERED,isprofitEntered);
            editor.commit();
        }

        if(isprofitEntered==false)
        {
            profitet.setVisibility(View.VISIBLE);
        }
        if(isprofitEntered==true)
        {
            profitet.setVisibility(View.INVISIBLE);
        }


    }

    public void addExpense(View view)
    {
        if(isprofitEntered==false)
        {
            if(profitet.getText().length()==0)
            {
                Toast.makeText(this, "please enter the pprofit", Toast.LENGTH_SHORT).show();
                return;
            }
            else
            {
                profit=Integer.parseInt(profitet.getText().toString());
                if(profit==0)
                {
                    profitet.setError("youtr profit is zero please enter a profit");
                    return;
                }
                database.getProfitDao().addProfit(new ProfitEntity(profit,profit,dateToString(date)));
                isprofitEntered=true;
                profitet.setVisibility(View.INVISIBLE);
                editor.putBoolean(IS_PROF_ENTERED,true);
                editor.putString(PREVIOUS_DATE,dateToString(date));
                editor.commit();
            }
            if(profit==0)
            {
                profitet.setError("youtr profit is zero please enter a profit");
                return;
            }
            else
            {
                String expName=nameet.getText().toString().trim();
                int expwnse=Integer.parseInt(expenseet.getText().toString().trim());
                if(profit-expwnse<=0)
                {
                    profitet.setError("expense is greater than profit enter profit");
                    expenseet.setError("expense is greater than profit enter profit");
                    return;
                }
                profit-=expwnse;

                editor.commit();
                database.getProfitDao().updateRemainingAmount(dateToString(date),profit);
                ExpenseEntity expenseEntity=new ExpenseEntity(profit,expwnse,expName,dateToString(date));
                database.getexpenseDao().insertExpense(expenseEntity);
                Toast.makeText(this, "expense added succcessfully", Toast.LENGTH_SHORT).show();
                profitet.setText("");
                expenseet.setText("");
                nameet.setText("");
            }
        }
        else
        {
            profit=database.getProfitDao().getremainingAmount(dateToString(date));

            if(profit==0)
            {
                Toast.makeText(this, "you have all of your profit", Toast.LENGTH_SHORT).show();
            }



            String expName=nameet.getText().toString().trim();
            int expwnse=Integer.parseInt(expenseet.getText().toString().trim());
            if(profit-expwnse<=0)
            {
                profitet.setError("expense is greater than profit enter profit");
                expenseet.setError("expense is greater than profit enter profit");
                return;
            }
            profit-=expwnse;
            database.getProfitDao().updateRemainingAmount(dateToString(date),profit);

            ExpenseEntity expenseEntity=new ExpenseEntity(profit,expwnse,expName,dateToString(date));
            database.getexpenseDao().insertExpense(expenseEntity);
            Toast.makeText(this, "expense added succcessfully", Toast.LENGTH_SHORT).show();
            profitet.setText("");
            expenseet.setText("");
            nameet.setText("");



        }
    }

    private String dateToString(Date date)
    {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(date);
        return strDate;
    }

    private Date convertStringToDate(String sDate1) throws ParseException
    {
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        return date1;
    }
}
