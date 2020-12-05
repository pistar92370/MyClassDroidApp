package com.ju.myclass.entities;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.ju.myclass.dao.converters.StudentConverter;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "classroom")
public class Classroom {

    @PrimaryKey
    @NonNull
    private long id;

    @ColumnInfo(name = "name")
    private String name;

//    @Ignore // Wont be persisted
//    private Bitmap picture;

    @TypeConverters(StudentConverter.class)
    private List<Student> students = new ArrayList<>();

    // Constructor
    public Classroom(@NonNull String name) {this.name = name;}

    // Methods
    public void addStudent(@NonNull Student student) {
        getStudents().add(student);
    }

    // Getters/setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

}
