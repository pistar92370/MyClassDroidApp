package com.ju.myclass.view.model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ju.myclass.entities.Event;
import com.ju.myclass.repositories.EventRepository;

import java.util.List;

public class EventViewModel extends AndroidViewModel {

    private EventRepository mEventRepository;

    private final LiveData<List<Event>> mAllEvent;

    public EventViewModel(Application application) {
        super(application);
        mEventRepository = new EventRepository(application);
        mAllEvent = mEventRepository.getAllEvents();
    }

    public LiveData<List<Event>> getAllEvents() { return mAllEvent; }

    public void insert(Event event) { mEventRepository.insert(event); }
}