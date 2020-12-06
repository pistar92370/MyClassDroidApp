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
    
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Student student);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    List<Long> insertAll(List<Student> students);

    @Update
    void update(Student student);

    @Delete
    void delete(Student student);

    @Query("DELETE FROM student")
    void deleteAll();

    @Query("SELECT * FROM student ORDER BY lastname ASC")
    LiveData<List<Student>> getStudents();

    @Query("SELECT * FROM student WHERE classroomId = :id ORDER BY lastname ASC")
    LiveData<List<Student>> getStudentsByClassroomId(long id);

}
