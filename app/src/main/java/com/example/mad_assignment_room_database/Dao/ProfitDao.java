package com.example.mad_assignment_room_database.Dao;

import androidx.room.Dao;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mad_assignment_room_database.Entity.ProfitEntity;

import java.util.List;

@Dao
public interface ProfitDao
{
    @Insert
    void addProfit(ProfitEntity profitEntity);


    @Query("update ProfitEntity set remainingAmount=:remainingamount where date=:date ")
    void updateRemainingAmount(String date,int remainingamount);

    @Query("select remainingAmount from ProfitEntity where date=:date")
    int getremainingAmount(String date);

    @Query("select profit  from ProfitEntity where date=:date")
    int getTotalProfit(String date);

    @Query("select * from ProfitEntity")
    List<ProfitEntity> getAllProfits();
}
