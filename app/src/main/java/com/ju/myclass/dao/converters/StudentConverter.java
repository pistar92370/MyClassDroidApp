package com.ju.myclass.dao.converters;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ju.myclass.entities.Student;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class StudentConverter {

    private static Gson gson = new Gson();
    private static Type listType = new TypeToken<List<Student>>() {}.getType();

    @TypeConverter
    public static List<Student> storedStringToStudents(String data) {
        if (data == null) {
            return Collections.emptyList();
        }
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String myObjectsToStoredString(List<Student> myObjects) {
        Gson gson = new Gson();
        return gson.toJson(myObjects);
    }
}
