package com.ju.myclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ju.myclass.view.adapters.ClassroomListAdapter;
import com.ju.myclass.entities.Classroom;
import com.ju.myclass.view.model.ClassroomViewModel;

public class MainActivity extends AppCompatActivity {

    private ClassroomViewModel mClassroomViewModel;

    public static final int NEW_CLASSROOM_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerviewClassroom);
        final ClassroomListAdapter adapter = new ClassroomListAdapter(new ClassroomListAdapter.ClassroomDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mClassroomViewModel = new ViewModelProvider(this).get(ClassroomViewModel.class);

        mClassroomViewModel.getAllClassrooms().observe(this, classrooms -> {
            // Update the cached copy of the classrooms in the adapter.
            adapter.submitList(classrooms);
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener( view -> {
            Intent intent = new Intent(MainActivity.this, NewClassroomActivity.class);
            startActivityForResult(intent, NEW_CLASSROOM_ACTIVITY_REQUEST_CODE);
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_CLASSROOM_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Classroom word = new Classroom(data.getStringExtra(NewClassroomActivity.EXTRA_REPLY_CLASSNAME));
            mClassroomViewModel.insert(word);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}