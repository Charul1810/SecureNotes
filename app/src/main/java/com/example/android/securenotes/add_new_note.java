package com.example.android.securenotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.example.android.securenotes.R.id.fab1;

public class add_new_note extends AppCompatActivity {

    EditText note_id, title, note;
    ListView listView;
    DatabaseHandler db;
    EditText time;
    String c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);

        note_id = (EditText) findViewById(R.id.note_id);
        title = (EditText) findViewById(R.id.title);
        note = (EditText) findViewById(R.id.note);
        time = (EditText) findViewById(R.id.cre_time);
        listView = (ListView) findViewById(R.id.list_item);
        db = new DatabaseHandler(this);

        //  final String titles = title.getText().toString();

        note.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                //if ( (title.getText().toString().trim().length() < 25)&&(title.getText().toString().trim().isEmpty())) {

                if(title.getText().toString().trim().isEmpty()) {
                    title.setText(s);
                }
                //}
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }
        });


        FloatingActionButton fab2 = (FloatingActionButton) findViewById(fab1);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (title.getText().toString().trim().length() > 0 && note.getText().toString().trim().length() > 0) {
                    add();
//                    db.addNote(new note(title.getText().toString(), note.getText().toString(), formattedDate));

                    //Toast.makeText(getApplicationContext(), formattedDate, Toast.LENGTH_LONG).show();
                    clear();

//                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Please add note!! ", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    public void add() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String formattedDate = df.format(c.getTime());

        db.addNote(new note(title.getText().toString(), note.getText().toString(), formattedDate));
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }


    public void clear() {
        note_id.setText("");
        title.setText("");
        note.setText("");
    }

    @Override
    public void onBackPressed() {
        if (title.getText().toString().trim().length() > 0 && note.getText().toString().trim().length() > 0) {

            add();
            Toast.makeText(getApplicationContext(), "Note saved", Toast.LENGTH_SHORT).show();
        } else {

            startActivity(new Intent(getApplicationContext(), Main2Activity.class));
            //     Toast.makeText(getApplicationContext(), "Back pressed", Toast.LENGTH_SHORT).show();


        }
        super.onBackPressed();

    }

}
