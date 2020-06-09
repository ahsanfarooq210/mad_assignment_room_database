package com.example.mad_assignment_room_database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mad_assignment_room_database.Entity.ExpenseEntity;
import com.example.mad_assignment_room_database.Entity.ProfitEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class monthly_report_form_activity extends AppCompatActivity
{
    private Date from,to;
    private ExpenseDatabase database;
    private List<ExpenseEntity> list;
    private List<ExpenseEntity> filtered;
    private TextView amountLeftTv,expenseDetailstv,totalAmount;
    private String date;

    private List<ProfitEntity> profitList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_report_form_activity);

        Intent intent=getIntent();

        date=intent.getStringExtra("from_date");

            from=new Date(Long.parseLong(date));


        date=intent.getStringExtra("to_date");
       // from.setYear(year);

            to=new Date(Long.parseLong(date));



        database=ExpenseDatabase.getInstance(this);

        list=database.getexpenseDao().getAllExpense();
        filtered=new ArrayList<>();

        totalAmount=findViewById(R.id.form_amount_total_tv);
        amountLeftTv=findViewById(R.id.form_amount_left_tv);
        expenseDetailstv=findViewById(R.id.form_expense_details_tv);
        profitList=database.getProfitDao().getAllProfits();

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Date date = null;
        for (int i=0;i<list.size();i++)
        {
            try
            {
                date=convertStringToDate(list.get(i).getDate());
            } catch (ParseException e)
            {
                e.printStackTrace();
            }


            if(date.after(from)&&date.before(to)&&date!=null)
            {
                filtered.add(list.get(i));
            }

        }

        String str="";


        for(int i=0;i<filtered.size();i++)
        {
            str+=i+". "+filtered.get(i).getExpenseName()+" "+filtered.get(i).getExpense()+"\n";

        }




        expenseDetailstv.setText(str);
        int totalProfit = 0,remaining = 0;
        Date date5=null;
        for(int i=0;i<profitList.size();i++)
        {
            try
            {
                date5=convertStringToDate(profitList.get(i).getDate());
            } catch (ParseException e)
            {
                e.printStackTrace();
            }
            if(date5.after(from)&&date5.before(to)&&date5!=null)
            {
                totalProfit+=profitList.get(i).getProfit();
                remaining+=profitList.get(i).getRemainingAmount();
            }
        }
        totalAmount.setText("Total amount="+totalProfit);
        amountLeftTv.setText("Remaining amount="+remaining);

    }
    private Date convertStringToDate(String sDate1) throws ParseException
    {
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        return date1;
    }
}
