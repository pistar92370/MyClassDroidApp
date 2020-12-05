package com.ju.myclass.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.ju.myclass.dao.StudentDao;
import com.ju.myclass.dao.MyClassRoomDatabase;
import com.ju.myclass.entities.Student;

import java.util.List;

public class StudentRepository {

    private StudentDao mStudentDao;
    private LiveData<List<Student>> mAllStudent;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public StudentRepository(Application application) {
        MyClassRoomDatabase db = MyClassRoomDatabase.getDatabase(application);
        mStudentDao = db.studentDao();
        mAllStudent = mStudentDao.getStudents();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Student>> getAllStudents() {
        return mAllStudent;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Student student) {
        MyClassRoomDatabase.databaseWriteExecutor.execute(() -> {
            mStudentDao.insert(student);
        });
    }
}
