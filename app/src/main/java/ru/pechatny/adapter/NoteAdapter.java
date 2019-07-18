package ru.pechatny.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import ru.pechatny.CreateNoteActivity;
import ru.pechatny.MainActivity;
import ru.pechatny.R;
import ru.pechatny.db.entities.Note;

public class NoteAdapter extends ListAdapter<Note, NoteViewHolder> {
    private Context context;

    public NoteAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    public static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {

        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.equals(newItem);
        }
    };

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View noteViewHolder = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);

        return new NoteViewHolder(noteViewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = getItem(position);
        holder.priority.setText(String.valueOf(note.getPriority()));
        holder.title.setText(note.getTitle());
        holder.description.setText(note.getDescription());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CreateNoteActivity.class);
            intent.putExtra(CreateNoteActivity.EXTRA_TITLE, note.getTitle());
            intent.putExtra(CreateNoteActivity.EXTRA_ID, note.getId());
            intent.putExtra(CreateNoteActivity.EXTRA_DESCRIPTION, note.getDescription());
            intent.putExtra(CreateNoteActivity.EXTRA_PRIORITY, note.getPriority());

            ((Activity) context).startActivityForResult(intent, MainActivity.EDIT_NOTE_REQUEST);
        });
    }

    public Note getNoteAt(int position) {
        return getItem(position);
    }
}
