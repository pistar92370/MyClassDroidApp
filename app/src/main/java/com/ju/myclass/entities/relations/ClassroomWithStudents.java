package com.ju.myclass.entities.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.ju.myclass.entities.Classroom;
import com.ju.myclass.entities.Student;

import java.util.List;

public class ClassroomWithStudents {
    @Embedded
    public Classroom classroom;
    @Relation(
            parentColumn = "id",
            entityColumn = "classroomId"
    )
    public List<Student> students;
}
