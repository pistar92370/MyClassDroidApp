package com.ju.myclass.view.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ju.myclass.R;

public class ClassroomViewHolder extends RecyclerView.ViewHolder {
    private final TextView classroomItemView;

    private ClassroomViewHolder(View itemView) {
        super(itemView);
        classroomItemView = itemView.findViewById(R.id.classroom_name_textView);
    }

    public void bind(String text) {
        classroomItemView.setText(text);
    }

    public static ClassroomViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_classroom_item, parent, false);
        return new ClassroomViewHolder(view);
    }
}
