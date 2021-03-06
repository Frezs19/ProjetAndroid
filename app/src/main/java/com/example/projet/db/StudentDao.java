package com.example.projet.db;



import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDao {

    @Query("SELECT * FROM Student")
    List<Student> getAll();

    @Insert
    void insert(Student student);

    @Insert
    long[] insertAll(Student... students);

    @Delete
    void delete(Student student);

    @Update
    void update(Student student);

}