package com.ju.myclass.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.ju.myclass.entities.Classroom;
import com.ju.myclass.entities.Student;
import com.ju.myclass.entities.Word;
import com.ju.myclass.entities.relations.ClassroomWithStudents;

import java.util.List;

@Dao
public interface ClassroomDao {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Classroom classroom);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    List<Long> insertAll(List<Classroom> classrooms);

    @Update
    void update(Classroom classroom);

    @Delete
    void delete(Classroom classroom);

    @Query("DELETE FROM classroom")
    void deleteAll();

    @Transaction
    @Query("SELECT * FROM classroom ORDER BY  name ASC")
    LiveData<List<Classroom>> getClassrooms();

    @Transaction
    @Query("SELECT * FROM classroom ORDER BY  name ASC")
    LiveData<List<ClassroomWithStudents>> getClassroomsWithStudents();
}
