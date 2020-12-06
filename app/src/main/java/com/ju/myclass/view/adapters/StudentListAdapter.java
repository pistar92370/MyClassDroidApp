package com.ju.myclass.view.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.ju.myclass.entities.Student;
import com.ju.myclass.view.holder.StudentListItemViewHolder;

public class StudentListAdapter extends ListAdapter<Student, StudentListItemViewHolder> {

    public StudentListAdapter(@NonNull DiffUtil.ItemCallback<Student> diffCallback) {
        super(diffCallback);
    }

    @Override
    public StudentListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return StudentListItemViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(StudentListItemViewHolder holder, int position) {
        Student current = getItem(position);
        holder.bind(current.getFirstName() + " " + current.getLastname());
    }

    public static class StudentDiff extends DiffUtil.ItemCallback<Student> {
        @Override
        public boolean areItemsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
            return oldItem.getId() == newItem.getId();
        }
        @Override
        public boolean areContentsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
            return oldItem.getFirstName().concat(oldItem.getLastname())
                    .equals(newItem.getFirstName().concat(newItem.getLastname()));
        }
    }
}
