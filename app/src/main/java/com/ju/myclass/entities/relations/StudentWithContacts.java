package com.ju.myclass.entities.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.ju.myclass.entities.Classroom;
import com.ju.myclass.entities.Contact;
import com.ju.myclass.entities.Student;

import java.util.List;

public class StudentWithContacts {
    @Embedded
    public Student student;
    @Relation(
            parentColumn = "id",
            entityColumn = "studentId"
    )
    public List<Contact> contacts;
}
