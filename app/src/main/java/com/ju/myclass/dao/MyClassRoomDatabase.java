package com.ju.myclass.dao;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.ju.myclass.entities.Classroom;
import com.ju.myclass.entities.Event;
import com.ju.myclass.entities.Student;
import com.ju.myclass.entities.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * we set exportSchema to false here to avoid a build warning.
 * In a real app, you should consider setting a directory for Room to use to export the schema
 * so you can check the current schema into your version control system.
 * TO CHECK
 */
@Database(entities = {Classroom.class, Student.class, Event.class}, version = 1, exportSchema = false)
public abstract class MyClassRoomDatabase extends RoomDatabase {

    public abstract ClassroomDao classroomDao();
    public abstract StudentDao studentDao();
    public abstract EventDao eventDao();

    private static volatile MyClassRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 1;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static MyClassRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyClassRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyClassRoomDatabase.class, "classroomdb")
                            .addCallback(roomDatabaseCallbackOnStart).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomDatabaseCallbackOnStart = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            System.out.println("DATABASE CREATED");
        }
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            System.out.println("DATABASE OPENING");
            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                ClassroomDao classroomDao = INSTANCE.classroomDao();
                StudentDao studentDao = INSTANCE.studentDao();
                EventDao eventDao = INSTANCE.eventDao();

                classroomDao.deleteAll();
                studentDao.deleteAll();
                eventDao.deleteAll();

                List<Classroom> classrooms = new ArrayList<>();
                List<Student> students = new ArrayList<>();
                List<Event> events = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    Classroom classroom = new Classroom("5eme" + i);
                    classrooms.add(classroom);
                    for (int j = 0 ; j < 25; j++) {
                        students.add(new Student(i, "Roger" + j, "Moore - class " + i, Student.SEX_MALE));
                        for (int k = 0 ; k < 10; k++) {
                            events.add(new Event(Event.UNLIKE, "Bavardages student " + j + k, -1, j));
                        }
                    }
                }

                System.out.println(classroomDao.insertAll(classrooms));
                System.out.println(studentDao.insertAll(students));
                System.out.println(eventDao.insertAll(events));


                //System.out.println(classroomDao.getClassrooms().getValue().size());
                System.out.println("Finishing populating DB");
            });
        }
    };
}
