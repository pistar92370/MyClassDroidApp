package com.ju.myclass.view.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.ju.myclass.ClassroomActivity;
import com.ju.myclass.R;
import com.ju.myclass.entities.Classroom;
import com.ju.myclass.view.holder.ClassroomItemViewHolder;

public class ClassroomListAdapter extends ListAdapter<Classroom, ClassroomItemViewHolder> {

    public ClassroomListAdapter(@NonNull DiffUtil.ItemCallback<Classroom> diffCallback) {
        super(diffCallback);
    }

    @Override
    public ClassroomItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ClassroomItemViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ClassroomItemViewHolder holder, int position) {
        Classroom current = getItem(position);
        holder.bind(current.getName());
        //holder.bind(String.valueOf(current.getId()));
        holder.setClassroom(current);
    }

    public static class ClassroomDiff extends DiffUtil.ItemCallback<Classroom> {
        @Override
        public boolean areItemsTheSame(@NonNull Classroom oldItem, @NonNull Classroom newItem) {
            return oldItem.getId() == newItem.getId();
        }
        @Override
        public boolean areContentsTheSame(@NonNull Classroom oldItem, @NonNull Classroom newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    }
}
