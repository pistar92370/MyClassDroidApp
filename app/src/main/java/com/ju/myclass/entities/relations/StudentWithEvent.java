package com.ju.myclass.entities.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.ju.myclass.entities.Event;
import com.ju.myclass.entities.Student;

import java.util.List;

public class StudentWithEvent {
    @Embedded public Student student;
    @Relation(
            parentColumn = "id",
            entityColumn = "studentId"
    )
    public List<Event> events;
}
