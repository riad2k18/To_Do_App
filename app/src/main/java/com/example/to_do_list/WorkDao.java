package com.example.to_do_list;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WorkDao {

    @Insert
    void insert(Work work);     //for insert single data

    @Update
    void update(Work work);      //for update single data

    @Delete
    void delete(Work work);      //for delete single data

    @Query("DELETE FROM work_table")
    void deleteallworks();       //for delete all data

    @Query("SELECT * FROM work_table ORDER BY id DESC")
    LiveData<List<Work>>getallworks();  //for showing data

}
