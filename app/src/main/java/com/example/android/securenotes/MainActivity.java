package com.example.android.securenotes;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.example.android.securenotes.R.id.fab;

public class MainActivity extends AppCompatActivity {
    EditText note_id, title, note;
    CardView cardView;
    ListView listView;
    List<note> mylist;
    DatabaseHandler db;
    AppAdapter adapter;
    private CoordinatorLayout cdl;
    private boolean isOpen = false;
    TextView view_id;
    TextView view_title;
    TextView view_note;
    TextView view_time;
    TextView txtid, txttitle, txtnote;
    ActionMode actionMode;
    List<note> noteList = new ArrayList<note>();
    int count = 0;
    GridView grid;
    MenuItem sort;


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
        registerForContextMenu(listView);
        db = new DatabaseHandler(this);
        cdl = (CoordinatorLayout) findViewById(R.id.actioncontainer);
        view_id = (TextView) findViewById(R.id.view_id);
        view_title = (TextView) findViewById(R.id.view_title);
        view_note = (TextView) findViewById(R.id.view_note);
        view_time = (TextView) findViewById(R.id.view_time);
        //grid = (GridView) findViewById(R.id.grid);
        sort = (MenuItem) findViewById(R.id.sort);


        load();

        FloatingActionButton fab1 = (FloatingActionButton) findViewById(fab);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), add_new_note.class));
            }
        });

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                Intent in = new Intent(getApplicationContext(), add_new_note.class);
//                in.putExtra("id", mylist.get(i).get_id() + "");
//                in.putExtra("title", mylist.get(i).get_title().toString());
//                in.putExtra("note", mylist.get(i).get_note().toString());
//                in.putExtra("time",mylist.get(i).get_time().toString());
//                startActivity(in);
//                finish();
//
//            }
//        });
//

//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                    registerForContextMenu(listView);
//                    return true;
//            }
//        });

    }


//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        //menu.setHeaderTitle("Select the action");
//        menu.add(0, v.getId(), 0, "Edit");
//        menu.add(0, v.getId(), 0, "Delete");
//        super.onCreateContextMenu(menu, v, menuInfo);
//
////        if (v.getId()==R.id.list_view) {
////            MenuInflater inflater = getMenuInflater();
////            inflater.inflate(R.menu.contextual_menu, menu);
////        }
//    }
//
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//        int index = info.position;
//        if (item.getTitle() == "Edit") {
//            startActivity(new Intent(getApplicationContext(), add_new_note.class));
//        } else if (item.getTitle() == "Delete") {
//
//            //db.deleteNote(new note(Integer.parseInt(note_id.getText().toString()), title.getText().toString(), note.getText().toString()));
//            db.deleteNote(new note(Integer.parseInt(note_id.getText().toString()), title.getText().toString(), note.getText().toString()));
//            load();
//            Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
//        }
//        return true;
//    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        CharSequence sorting[]= new CharSequence[]{"By Alphabet","By Date"};
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(), "Settings selected", Toast.LENGTH_SHORT).show();
            return true;
        }

//        if (id == R.id.grid) {
//            listView.setVisibility(View.GONE);
//            grid.setVisibility(View.VISIBLE);
//
//        }
        if (id==R.id.sort)
        {
//            Toast.makeText(getApplicationContext(),"Sort Selected",Toast.LENGTH_SHORT).show();
                AlertDialog.Builder UnitSelection = new AlertDialog.Builder(this);
                UnitSelection.setTitle("Select Unit");
                UnitSelection.setItems(sorting, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(i==0)
                        {
                            up();
                            Toast.makeText(getApplicationContext(),"By alpa",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
//                UnitSelection.setSingleChoiceItems(id, 0, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int item) {
//                        Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT).show();
//                    }
//                });
                AlertDialog alert = UnitSelection.create();
                alert.show();

        }


        return super.onOptionsItemSelected(item);
    }

    public void up(){

        Sort("By Alphabet");
    }
    private void Sort() {

        Comparator <note> comp=new Comparator<note>() {
            @Override
            public int compare(note note, note t1) {
                int first=Integer.parseInt(note.get_id()+"");
                int second=Integer.parseInt(note.get_id()+"");



                return (first > second ?-1 : (first==second? 0:1));
            }

        };

        Collections.sort(mylist,comp);
        adapter.notifyDataSetChanged();
        load();
    }
    // set repeat mode from None, Hour, Daily, Monthly, Yearly
//            private AlertDialog repeatDialog() {
//                private AlertDialog sortdialog()
//                {
//                    final int sort_asc=
//                    return new AlertDialog.Builder(this)
//                            .setTitle("Sort")
//                            .setSingleChoiceItems()
//                }
//            final int prevRepeat = mRepeatMode;
//            return new AlertDialog.Builder(this)
//                    .setTitle("Repeat")
//                    .setSingleChoiceItems(REPEAT_MODES, 0, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int i) {
//                            mRepeatMode = i;
//                        }
//                    })
//                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int i) {
//                            // set label to selected repeat mode
//                            mAlarmRepeat.put("subtext", REPEAT_MODES[mRepeatMode]);
//                            mAdapter.notifyDataSetChanged();
//                            dialog.dismiss();
//                        }
//                    })
//                    .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int i) {
//                            mRepeatMode = prevRepeat;
//                        }
//                    })
//                    .create();
//        }


    //List<note> list = db.getAllNotes();


    public void load() {

        List<note> list = db.getAllNotes();
        mylist = list;
        adapter = new AppAdapter();
        listView.setAdapter(adapter);



    }


    public void Sort(String items){


    }


//   Collections.sort(myIntegerList, new Comparator<Integer>() {
//        public int compare(Integer one, Integer other) {
//            if (one >= other) {
//                return -1;
//            } else {
//                return 1;
//            }
//        }
//    });




    public void setSort () {


//        noteList.sort(new Comparator<com.example.android.securenotes.note>() {
//            @Override
//            public int compare(note note, note t1) {
//                return note.toString().compareTo(t1.toString());
//            }
//
//        });
//        adapter.notifyDataSetChanged();
//        return noteList;
        mylist.sort(new Comparator<com.example.android.securenotes.note>() {
            @Override
            public int compare(note note, note t1) {
                return note.toString().compareTo(t1.toString());
            }
        });
        adapter.notifyDataSetChanged();



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
        public View getView(final int position, View convertView, final ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(),
                        R.layout.list_item, null);
                new ViewHolder(convertView);
            }


            final ViewHolder holder = (ViewHolder) convertView.getTag();

            holder.tv_id.setText(mylist.get(position).get_id() + "");
            holder.tv_name.setText(mylist.get(position).get_title());
            holder.tv_num.setText(mylist.get(position).get_note());
            holder.tv_time.setText(mylist.get(position).get_time());


            //Long press -Delete

            //Click

            //holder.tv_time.setText(mylist.get(position).getDateTime().toString());

            //  This code is working but temporary disables to try something

            holder.row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(getApplicationContext(), ViewNote.class);
                    i.putExtra("id", mylist.get(position).get_id() + "");
                    i.putExtra("title", mylist.get(position).get_title().toString());
                    i.putExtra("note", mylist.get(position).get_note().toString());
                    i.putExtra("time", mylist.get(position).get_time().toString());
                    startActivity(i);

                }
            });


            holder.row.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

                    // Setting Dialog Title
                    alertDialog.setTitle("Confirm Delete...");

                    // Setting Dialog Message
                    alertDialog.setMessage("Are you sure you want delete this?");

                    // Setting Icon to Dialog
                    //alertDialog.setIcon(R.drawable.delete);

                    // Setting Positive "Yes" Button
                    alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            // Write your code here to invoke YES event
                            //Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();
                            db.deleteNote(new note(mylist.get(position).get_id(), mylist.get(position).get_title(), mylist.get(position).get_note()));
                            load();
                            Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();

                        }
                    });

                    // Setting Negative "NO" Button
                    alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to invoke NO event
                            // Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                    });

                    // Showing Alert Message
                    alertDialog.show();

                    //Toast.makeText(getApplicationContext(),mylist.get(position).get_id() + "",Toast.LENGTH_LONG).show();
//                    txtid.setText(mylist.get(position).get_id() + "");
//                    txttitle.setText(mylist.get(position).get_title());
//                    txtnote.setText(mylist.get(position).get_note());
                    return true;
                }
            });


            return convertView;

        }


        class ViewHolder {
            TextView tv_id;
            TextView tv_name;
            TextView tv_num;
            TextView tv_time;
            LinearLayout row;


            public ViewHolder(View view) {
                tv_id = (TextView) view.findViewById(R.id.note_id);
                tv_name = (TextView) view.findViewById(R.id.title);
                tv_num = (TextView) view.findViewById(R.id.note);
                tv_time = (TextView) view.findViewById(R.id.c_time);
                row = (LinearLayout) view.findViewById(R.id.list_viewrow);
                view.setTag(this);
            }
        }
    }
}
