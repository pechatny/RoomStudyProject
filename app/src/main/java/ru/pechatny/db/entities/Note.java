package ru.pechatny.db.entities;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String title;
    private String description;
    private Integer priority;

    public Note(String title, String description, Integer priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        Note note = (Note) obj;
        assert note != null;
        return this.getTitle().equals(note.getTitle()) &&
                this.getDescription().equals(note.getDescription()) &&
                this.getPriority().equals(note.getPriority());
    }
}
