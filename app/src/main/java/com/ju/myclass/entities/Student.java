package com.ju.myclass.entities;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.ju.myclass.dao.converters.ContactConverter;
import com.ju.myclass.dao.converters.DateConverter;
import com.ju.myclass.dao.converters.EventConverter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(tableName = "student")
public class Student {

    public static String SEX_MALE = "MALE";
    public static String SEX_FEMALE = "FEMALE";

    @PrimaryKey
    @NonNull
    private long id;
    // INFO
    private String firstName;
    private String lastname;
    @TypeConverters(DateConverter.class)
    private Date birthDate;
    private String sex;
    private String description;
    // CLASSROOM
    public long classroomId;
    // CONTACTS
    @TypeConverters(ContactConverter.class)
    private List<Contact> contacts = new ArrayList<>();
    // EVENTS
    @TypeConverters(EventConverter.class)
    private List<Event> events = new ArrayList<>();

    // Constructors
    public Student() {
    }

    public Student(String firstName, String lastname, String sex) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.sex = sex;
    }

    // Methods
    public void addContact(Contact contact) {
        getContacts().add(contact);
    }

    public void addEvent(Event event) {
        getEvents().add(event);
    }

    // Getters / Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(long classroomId) {
        this.classroomId = classroomId;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
