package com.ju.myclass.view.model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ju.myclass.entities.Classroom;
import com.ju.myclass.entities.Word;
import com.ju.myclass.repositories.ClassroomRepository;

import java.util.List;

public class ClassroomViewModel extends AndroidViewModel {

    private ClassroomRepository mClassroomRepository;

    private final LiveData<List<Classroom>> mAllClassroom;

    public ClassroomViewModel(Application application) {
        super(application);
        mClassroomRepository = new ClassroomRepository(application);
        mAllClassroom = mClassroomRepository.getAllClassrooms();
    }

    public LiveData<List<Classroom>> getAllClassrooms() { return mAllClassroom; }

    public void insert(Classroom classroom) { mClassroomRepository.insert(classroom); }
}