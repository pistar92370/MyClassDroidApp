package com.ju.myclass.view.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.ju.myclass.entities.Classroom;
import com.ju.myclass.view.holder.ClassroomViewHolder;
import com.ju.myclass.view.holder.ClassroomViewHolder;

public class ClassroomListAdapter extends ListAdapter<Classroom, ClassroomViewHolder> {

    public ClassroomListAdapter(@NonNull DiffUtil.ItemCallback<Classroom> diffCallback) {
        super(diffCallback);
    }

    @Override
    public ClassroomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ClassroomViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ClassroomViewHolder holder, int position) {
        Classroom current = getItem(position);
        holder.bind(current.getName());
    }

    public static class ClassroomDiff extends DiffUtil.ItemCallback<Classroom> {
        @Override
        public boolean areItemsTheSame(@NonNull Classroom oldItem, @NonNull Classroom newItem) {
            return oldItem == newItem;
        }
        @Override
        public boolean areContentsTheSame(@NonNull Classroom oldItem, @NonNull Classroom newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    }
}
