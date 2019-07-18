package ru.pechatny.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.pechatny.R;

public class NoteViewHolder extends RecyclerView.ViewHolder {
    public TextView title, description, priority;

    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.note_title);
        description = itemView.findViewById(R.id.note_description);
        priority = itemView.findViewById(R.id.note_priority);
    }
}
