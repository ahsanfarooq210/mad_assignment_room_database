package com.example.mad_assignment_room_database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mad_assignment_room_database.Entity.ExpenseEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class todays_report_activity extends AppCompatActivity
{
    private TextView dateTv,amountLeftTv,expenseDetailsTv,totalProfitTv;
    private Date date;
    private SharedPreferences sharedPreferences;
    private ExpenseDatabase database;
    private SharedPreferences.Editor editor;
    private static final String REMAINPROFIT="remaining profiy";
    private static final String SHARED_PREFS="todays date";
    private Calendar currentDate;
    private List<ExpenseEntity> list;
    private int profit,remainig;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todays_report_activity);

        totalProfitTv=findViewById(R.id.amount_total_tv);
        dateTv=findViewById(R.id.date_tv);
        amountLeftTv=findViewById(R.id.amount_left_tv);
        expenseDetailsTv=findViewById(R.id.expense_details_tv);

        currentDate= Calendar.getInstance();

        sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        editor=sharedPreferences.edit();
        database=ExpenseDatabase.getInstance(this);


        date=currentDate.getTime();
        list=new ArrayList<>();

        list=database.getexpenseDao().todaysReport(dateToString(date));
        dateTv.setText(dateToString(date));

        profit=database.getProfitDao().getTotalProfit(dateToString(date));
        remainig=database.getProfitDao().getremainingAmount(dateToString(date));

        amountLeftTv.setText("Remaining Amount="+remainig);
        totalProfitTv.setText("Total profit:="+profit);


    }

    @Override
    protected void onStart()
    {
        super.onStart();
        String str="";
        for (int i=0;i<list.size();i++)
        {
            str+=i+1+"."+list.get(i).getExpenseName()+"   "+list.get(i).getExpense()+"\n";
        }

        expenseDetailsTv.setText(str);
    }
    private String dateToString(Date date)
    {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(date);
        return strDate;
    }
}
