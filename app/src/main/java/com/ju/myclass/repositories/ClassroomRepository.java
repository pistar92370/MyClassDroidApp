package com.ju.myclass.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.ju.myclass.dao.ClassroomDao;
import com.ju.myclass.dao.MyClassRoomDatabase;
import com.ju.myclass.dao.WordRoomDatabase;
import com.ju.myclass.entities.Classroom;
import com.ju.myclass.entities.Word;

import java.util.List;

public class ClassroomRepository {

    private ClassroomDao mClassroomDao;
    private LiveData<List<Classroom>> mAllClassroom;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public ClassroomRepository(Application application) {
        MyClassRoomDatabase db = MyClassRoomDatabase.getDatabase(application);
        mClassroomDao = db.classroomDao();
        mAllClassroom = mClassroomDao.getClassrooms();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Classroom>> getAllClassrooms() {
        return mAllClassroom;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Classroom classroom) {
        MyClassRoomDatabase.databaseWriteExecutor.execute(() -> {
            mClassroomDao.insert(classroom);
        });
    }
}
