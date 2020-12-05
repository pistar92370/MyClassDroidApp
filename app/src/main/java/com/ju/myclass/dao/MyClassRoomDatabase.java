package com.ju.myclass.dao;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.ju.myclass.entities.Classroom;
import com.ju.myclass.entities.Contact;
import com.ju.myclass.entities.Event;
import com.ju.myclass.entities.Student;
import com.ju.myclass.entities.Word;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * we set exportSchema to false here to avoid a build warning.
 * In a real app, you should consider setting a directory for Room to use to export the schema
 * so you can check the current schema into your version control system.
 * TO CHECK
 */
@Database(entities = {Classroom.class, Student.class, Contact.class, Event.class}, version = 1, exportSchema = false)
public abstract class MyClassRoomDatabase extends RoomDatabase {

    public abstract ClassroomDao classroomDao();
    public abstract StudentDao studentDao();
    public abstract EventDao eventDao();

    private static volatile MyClassRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
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

                Classroom classroom = new Classroom("5eme2");
                Student student1 = new Student("Roger", "Moore", Student.SEX_MALE);
                Contact contact1 = new Contact("Daron", "0678475983");
                Event event1 = new Event(Event.UNLIKE, "Bavardages", -1);

                student1.addContact(contact1);
                student1.addEvent(event1);
                classroom.addStudent(student1);

                classroomDao.insert(classroom);

                //////////////////////
                Classroom classroom2 = new Classroom("5eme3");
                Student student2 = new Student("Sarah", "Vittoz", Student.SEX_FEMALE);
                Contact contact2 = new Contact("Daron", "0699999988");
                Event event2 = new Event(Event.UNLIKE, "Saradote", -1);

                student2.addContact(contact2);
                student2.addEvent(event2);
                classroom.addStudent(student2);

                classroomDao.insert(classroom2);

                System.out.println(classroomDao.getClassrooms());
                System.out.println("Finishing populating DB");
            });
        }
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            System.out.println("DATABASE OPENING");
//            // If you want to keep data through app restarts,
//            // comment out the following block
//            databaseWriteExecutor.execute(() -> {
//                // Populate the database in the background.
//                // If you want to start with more words, just add them.
//                ClassroomDao classroomDao = INSTANCE.classroomDao();
//                StudentDao studentDao = INSTANCE.studentDao();
//                EventDao eventDao = INSTANCE.eventDao();
//                classroomDao.deleteAll();
//                studentDao.deleteAll();
//                eventDao.deleteAll();
//
//                Classroom classroom = new Classroom("5eme2");
//                Student student1 = new Student("Roger", "Moore", Student.SEX_MALE);
//                Contact contact1 = new Contact("Daron", "0678475983");
//                Event event1 = new Event(Event.UNLIKE, "Bavardages", -1);
//
//                student1.addContact(contact1);
//                student1.addEvent(event1);
//                classroom.addStudent(student1);
//
//                classroomDao.insert(classroom);
//
//                System.out.println(classroomDao.getClassrooms());
//                System.out.println("Finishing populating DB");
//            });
        }
    };
}
