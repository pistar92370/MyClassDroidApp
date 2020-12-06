package com.ju.myclass.view.model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ju.myclass.entities.Student;
import com.ju.myclass.repositories.StudentRepository;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {

    private StudentRepository mStudentRepository;

    private final LiveData<List<Student>> mAllStudent;

    public StudentViewModel(Application application) {
        super(application);
        mStudentRepository = new StudentRepository(application);
        mAllStudent = mStudentRepository.getAllStudents();
    }

    public LiveData<List<Student>> getAllStudents() {
        return mStudentRepository.getAllStudents();
    }

    public LiveData<List<Student>> getStudentsByClassId(long id) {
        return mStudentRepository.getStudentsByClassId(id); }

    public void insert(Student student) { mStudentRepository.insert(student); }
}