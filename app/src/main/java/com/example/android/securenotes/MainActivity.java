package com.example.android.securenotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static com.example.android.securenotes.R.id.fab;

public class MainActivity extends AppCompatActivity {
    EditText note_id, title, note;
    ListView listView;
    List<note> mylist;
    DatabaseHandler db;
    AppAdapter adapter;
    private CoordinatorLayout cdl;
    private boolean isOpen = false;
    TextView view_id;
    TextView view_title;
    TextView view_note;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        note_id = (EditText) findViewById(R.id.note_id);
        title = (EditText) findViewById(R.id.title);
        note = (EditText) findViewById(R.id.note);
        listView = (ListView) findViewById(R.id.list_item);
        db = new DatabaseHandler(this);
        cdl = (CoordinatorLayout) findViewById(R.id.actioncontainer);
        view_id = (TextView) findViewById(R.id.view_id);
        view_title = (TextView) findViewById(R.id.view_title);
        view_note = (TextView) findViewById(R.id.view_note);


        //toolbar_hidden=(ActionBar) findViewById(R.id.toolbar_hidden);
        load();


        FloatingActionButton fab1 = (FloatingActionButton) findViewById(fab);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //viewMenu();
                startActivity(new Intent(getApplicationContext(), add_new_note.class));
            }
        });

//    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                    Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_SHORT).show();
//               // setContentView(R.layout.activity_view_note);
//
//
//              startActivity(new Intent(getApplicationContext(),ViewNote.class));
//                }
//        });

//        view_id.setText(mylist.get(i).get_id() + "");
//        view_title.setText(mylist.get(i).get_title());
//        view_note.setText(mylist.get(i).get_note());

//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(),"delete",Toast.LENGTH_SHORT).show();
//                return true;
//
//            }
//        });
    }
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//        switch (item.getItemId()) {
//            case R.id.action_delete:
//                //deleteNote(info.id);
//                setVisible(true);
//                return true;
//            default:
//                return super.onContextItemSelected(item);
//        }
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void load() {

        List<note> list = db.getAllNotes();
        mylist = list;
        adapter = new AppAdapter();
        this.listView.setAdapter(adapter);

        Toast.makeText(getApplicationContext(), mylist.size() + "", Toast.LENGTH_LONG).show();

    }

    class AppAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mylist.size();
        }

        @Override
        public String getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(),
                        R.layout.list_item, null);
                new ViewHolder(convertView);
            }
            ViewHolder holder = (ViewHolder) convertView.getTag();

            holder.tv_id.setText(mylist.get(position).get_id() + "");
            holder.tv_name.setText(mylist.get(position).get_title());
            holder.tv_num.setText(mylist.get(position).get_note());
//This code is working but temporary disables to try something

            holder.row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(),mylist.get(position).get_note(), Toast.LENGTH_SHORT).show();

//                    view_id.setText(mylist.get(position).get_id() + "");
//                    view_title.setText(mylist.get(position).get_title());
//                    view_note.setText(mylist.get(position).get_note());


                    Intent i=new Intent(getApplicationContext(),ViewNote.class);
                    Bundle b=new Bundle();
                    b.putInt("id",mylist.get(position).get_id());
                    b.putString("title",mylist.get(position).get_title().toString());
                    b.putString("note",mylist.get(position).get_note().toString());
                    i.putExtra("peronal",b);
                   startActivity(i);

                }
            });


            return convertView;

        }


        class ViewHolder {
            TextView tv_id;
            TextView tv_name;
            TextView tv_num;
            LinearLayout row;
//            TextView view_id;
//            TextView view_title;
//            TextView view_note;


            public ViewHolder(View view) {
                tv_id = (TextView) view.findViewById(R.id.note_id);
                tv_name = (TextView) view.findViewById(R.id.title);
                tv_num = (TextView) view.findViewById(R.id.note);
                row = (LinearLayout) view.findViewById(R.id.row);
//                view_id=(TextView) findViewById(R.id.view_id);
//                view_title=(TextView) findViewById(R.id.view_title);
//                view_note=(TextView) findViewById(R.id.view_note);

                view.setTag(this);
            }
        }
    }
}
