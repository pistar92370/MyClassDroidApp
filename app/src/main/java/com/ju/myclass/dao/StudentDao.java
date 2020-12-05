package com.ju.myclass.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.ju.myclass.entities.Student;

import java.util.List;

@Dao
public interface StudentDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Student student);

    @Update
    void update(Student student);

    @Delete
    void delete(Student student);

    @Query("DELETE FROM student")
    void deleteAll();

    @Query("SELECT * FROM student ORDER BY lastname ASC")
    LiveData<List<Student>> getStudents();
}
