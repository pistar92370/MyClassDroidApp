package com.ju.myclass.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.ju.myclass.dao.converters.DateConverter;

import java.util.Date;
import java.util.List;

@Entity(tableName = "event")
public class Event {

    /*
        Event types
    */
    public static final String INCIDENT = "INCIDENT";
    public static final String LIKE = "LIKE";
    public static final String UNLIKE = "UNLIKE";

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private long id;

    // External IDs
    public long studentId;

    private String type;
    @TypeConverters(DateConverter.class)
    private Date creationDate;
    private String comment;
    private Integer value;      // value that impact student's mark (-2, -1, +1, ...)

    // Constructors


    public Event() {
    }

    public Event(String type, String comment, Integer value, long studentId) {
        this.type = type;
        this.comment = comment;
        this.value = value;
        this.studentId = studentId;
    }

    //Getters/Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
