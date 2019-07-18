package ru.pechatny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class CreateNoteActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "ru.pechatny.EXTRA_ID";
    public static final String EXTRA_TITLE = "ru.pechatny.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "ru.pechatny.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "ru.pechatny.EXTRA_PRIORITY";

    EditText editTitle;
    EditText editDescription;
    NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        editTitle = findViewById(R.id.edit_title);
        editDescription = findViewById(R.id.edit_description);
        numberPicker = findViewById(R.id.pick_priority);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(10);


        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            editTitle.setText(intent.getStringExtra(CreateNoteActivity.EXTRA_TITLE));
            editDescription.setText(intent.getStringExtra(CreateNoteActivity.EXTRA_DESCRIPTION));
            numberPicker.setValue(intent.getIntExtra(CreateNoteActivity.EXTRA_PRIORITY, 1));
            setTitle("Редактировать");
        } else {
            setTitle("Создать");
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveNote() {
        String title = editTitle.getText().toString();
        String description = editDescription.getText().toString();
        int priority = numberPicker.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(this, "Please input title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if(id > -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }
}
