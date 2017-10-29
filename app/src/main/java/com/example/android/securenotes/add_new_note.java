package com.example.android.securenotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.example.android.securenotes.R.id.fab1;

public class add_new_note extends AppCompatActivity {

    EditText note_id,title,note;
    ListView listView;
    DatabaseHandler db;
    EditText  time;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);

       note_id = (EditText) findViewById(R.id.note_id);
        title = (EditText) findViewById(R.id.title);
        note = (EditText) findViewById(R.id.note);
       time =(EditText) findViewById(R.id.cre_time);

        listView = (ListView) findViewById(R.id.list_item);
        db = new DatabaseHandler(this);

       Calendar c = Calendar.getInstance();
       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       final String formattedDate = df.format(c.getTime());

       FloatingActionButton fab2 = (FloatingActionButton) findViewById(fab1);
       fab2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

                   db.addNote(new note(title.getText().toString(), note.getText().toString(), formattedDate));

                   //Toast.makeText(getApplicationContext(), formattedDate, Toast.LENGTH_LONG).show();
                   clear();

               startActivity(new Intent(getApplicationContext(),MainActivity.class));
               finish();

           }

       });



//       private void validateNoteForm() {
//           StringBuilder message = null;
//           if (Strings.isNullOrBlank(noteTitleText.getText().toString())) {
//               message = new StringBuilder().append(getString(R.string.title_required));
//           }
//           if (Strings.isNullOrBlank(noteContentText.getText().toString())) {
//               if (message == null) message = new StringBuilder().append(getString(R.string.content_required));
//               else message.append("\n").append(getString(R.string.content_required));
//           }
//           if (message != null) {
//               Toast.makeText(getApplicationContext(),
//                       message,
//                       Toast.LENGTH_LONG)
//                       .show();
//           }
//       }

    }

    public void clear(){
        note_id.setText("");
        title.setText("");
        note.setText("");
    }

    @Override
    public void onBackPressed() {
        note_id = (EditText) findViewById(R.id.note_id);
        title = (EditText) findViewById(R.id.title);
        note = (EditText) findViewById(R.id.note);
        time =(EditText) findViewById(R.id.cre_time);

        listView = (ListView) findViewById(R.id.list_item);
        db = new DatabaseHandler(this);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String formattedDate = df.format(c.getTime());

        FloatingActionButton fab2 = (FloatingActionButton) findViewById(fab1);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.addNote(new note(title.getText().toString(), note.getText().toString(), formattedDate));

                Toast.makeText(getApplicationContext(), formattedDate, Toast.LENGTH_LONG).show();
                clear();

                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }

        });

        super.onBackPressed();

    }
}
