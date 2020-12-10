package com.ju.myclass.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.ju.myclass.dao.converters.DateConverter;

import java.util.Date;

@Entity(tableName = "student")
public class Student {

    @Embedded public final static String SEX_MALE = "MALE";
    @Embedded public final static String SEX_FEMALE = "FEMALE";

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private long id;

    // FOREIGN IDs
    public long classroomId;

    // INFO
    private String firstName;
    private String lastname;
    @TypeConverters(DateConverter.class)
    private Date birthDate;
    private String sex;
    private String description;
    private String contactName1;
    private String contactName2;
    private String contactPhoneNumber1;
    private String contactPhoneNumber2;

    // Constructors
    public Student() {
    }

    public Student(long classroomId,
                   String firstName,
                   String lastname,
                   String sex) {
        this.classroomId = classroomId;
        this.firstName = firstName;
        this.lastname = lastname;
        this.sex = sex;
        this.birthDate = birthDate;
        this.contactPhoneNumber1 = contactPhoneNumber1;
        this.contactPhoneNumber2 = contactPhoneNumber2;
    }

    public Student(long classroomId,
                   String firstName,
                   String lastname,
                   String sex,
                   Date birthDate,
                   String contactPhoneNumber1,
                   String contactPhoneNumber2) {
        this.classroomId = classroomId;
        this.firstName = firstName;
        this.lastname = lastname;
        this.sex = sex;
        this.birthDate = birthDate;
        this.contactPhoneNumber1 = contactPhoneNumber1;
        this.contactPhoneNumber2 = contactPhoneNumber2;
    }

    // Methods


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

    public String getContactName1() {
        return contactName1;
    }

    public void setContactName1(String contactName1) {
        this.contactName1 = contactName1;
    }

    public String getContactName2() {
        return contactName2;
    }

    public void setContactName2(String contactName2) {
        this.contactName2 = contactName2;
    }

    public String getContactPhoneNumber1() {
        return contactPhoneNumber1;
    }

    public void setContactPhoneNumber1(String contactPhoneNumber1) {
        this.contactPhoneNumber1 = contactPhoneNumber1;
    }

    public String getContactPhoneNumber2() {
        return contactPhoneNumber2;
    }

    public void setContactPhoneNumber2(String contactPhoneNumber2) {
        this.contactPhoneNumber2 = contactPhoneNumber2;
    }
}
