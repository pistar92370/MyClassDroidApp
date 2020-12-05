package com.ju.myclass.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.ju.myclass.dao.MyClassRoomDatabase;
import com.ju.myclass.dao.EventDao;
import com.ju.myclass.entities.Event;

import java.util.List;

public class EventRepository {

    private EventDao mEventDao;
    private LiveData<List<Event>> mAllEvent;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public EventRepository(Application application) {
        MyClassRoomDatabase db = MyClassRoomDatabase.getDatabase(application);
        mEventDao = db.eventDao();
        mAllEvent = mEventDao.getEvents();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Event>> getAllEvents() {
        return mAllEvent;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Event event) {
        MyClassRoomDatabase.databaseWriteExecutor.execute(() -> {
            mEventDao.insert(event);
        });
    }
}
