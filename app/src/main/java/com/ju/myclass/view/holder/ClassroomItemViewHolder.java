package com.ju.myclass.view.holder;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ju.myclass.ClassroomActivity;
import com.ju.myclass.R;
import com.ju.myclass.entities.Classroom;

public class ClassroomItemViewHolder extends RecyclerView.ViewHolder {
    private final TextView classroomItemView;
    private Classroom classroom;

    private ClassroomItemViewHolder(View itemView) {
        super(itemView);
        classroomItemView = itemView.findViewById(R.id.classroom_name_textView);
        classroomItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start classroom activity
                System.out.println("CLICK HOLDER");
                Intent intent = new Intent(v.getContext(), ClassroomActivity.class);
                intent.putExtra("CLASS_ID", getClassroom().getId());
                v.getContext().startActivity(intent);
            }
        });
    }

    public void bind(String text) {
        classroomItemView.setText(text);
    }

    public static ClassroomItemViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_classroom_item, parent, false);
        return new ClassroomItemViewHolder(view);
    }

//    @Override
//    public void onClick(View v) {
//        // Start classroom activity
//        System.out.println("CLICK HOLDER");
//        Intent intent = new Intent(v.getContext(), ClassroomActivity.class);
//        intent.putExtra("CLASS_ID", getClassroom().getId());
//        v.getContext().startActivity(intent);
//    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public TextView getClassroomItemView() {
        return classroomItemView;
    }
}
