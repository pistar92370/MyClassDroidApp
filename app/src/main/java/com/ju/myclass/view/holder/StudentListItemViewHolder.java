package com.ju.myclass.view.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ju.myclass.R;

public class StudentListItemViewHolder extends RecyclerView.ViewHolder {
    private final TextView studentItemView;

    private StudentListItemViewHolder(View itemView) {
        super(itemView);
        studentItemView = itemView.findViewById(R.id.student_label_textView);
    }

    public void bind(String text) {
        studentItemView.setText(text);
    }

    public static StudentListItemViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_student_item, parent, false);
        return new StudentListItemViewHolder(view);
    }
}
