package com.ju.myclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ju.myclass.entities.Student;
import com.ju.myclass.view.adapters.StudentListAdapter;
import com.ju.myclass.view.model.StudentViewModel;

public class ClassroomActivity extends AppCompatActivity {

    private StudentViewModel mStudentViewModel;
    private long classroomId;
    public static final int NEW_CLASSROOM_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get classroom ID from parent activity
        classroomId = getIntent().getLongExtra("CLASS_ID", -1);
        System.out.println("STARTING CLASSROOM_ACTIVITY - CLASS_ID=" + classroomId);
        setContentView(R.layout.activity_classroom);
        RecyclerView recyclerView = findViewById(R.id.recyclerviewStudent);
        final StudentListAdapter adapter = new StudentListAdapter(new StudentListAdapter.StudentDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mStudentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);

        mStudentViewModel.getStudentsByClassId(classroomId).observe(this, students -> {
            // Update the cached copy of the classrooms in the adapter.
            adapter.submitList(students);
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener( view -> {
            Intent intent = new Intent(ClassroomActivity.this, NewStudentActivity.class);
            intent.putExtra("CLASS_ID", classroomId);
            startActivityForResult(intent, NEW_CLASSROOM_ACTIVITY_REQUEST_CODE);
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_CLASSROOM_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String firstName = data.getStringExtra(NewStudentActivity.EXTRA_REPLY_FIRST_NAME);
            String lastName = data.getStringExtra(NewStudentActivity.EXTRA_REPLY_LAST_NAME);
            String sex = "MALE";
            long classroomId = data.getLongExtra(NewStudentActivity.EXTRA_REPLY_CLASSROOM_ID, -1);
            Student student = new Student(firstName, lastName, sex, classroomId);
            mStudentViewModel.insert(student);
            Toast.makeText(
                    getApplicationContext(),
                    R.string.successfully_saved,
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}