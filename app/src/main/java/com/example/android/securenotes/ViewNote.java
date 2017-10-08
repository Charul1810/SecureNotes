package com.example.android.securenotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class ViewNote extends AppCompatActivity {
    TextView view_id;
    TextView view_title;
    TextView view_note;
    List<note> mylist;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);

        view_id=(TextView) findViewById(R.id.view_id);
        view_title=(TextView) findViewById(R.id.view_title);
        view_note=(TextView) findViewById(R.id.view_note);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),add_new_note.class));
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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
                            R.layout.activity_view_note, null);
                    new AppAdapter.ViewHolder(convertView);
                }
                ViewHolder holder = (ViewHolder) convertView.getTag();

                holder.tv_id.setText(mylist.get(position).get_id() + "");
                holder.tv_name.setText(mylist.get(position).get_title());
                holder.tv_num.setText(mylist.get(position).get_note());
//This code is working but temporary disables to try something

            holder.row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
     Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_SHORT).show();
                   // startActivity(new Intent(getApplicationContext(),ViewNote.class));
                   view_id.setText(mylist.get(position).get_id() + "");
                    view_title.setText(mylist.get(position).get_title());
                    view_note.setText(mylist.get(position).get_note());

                }
            });


                return convertView;
            }

            class ViewHolder {
                TextView tv_id;
                TextView tv_name;
                TextView tv_num;
                LinearLayout row;

                public ViewHolder(View view) {
                    tv_id = (TextView) view.findViewById(R.id.note_id);
                    tv_name = (TextView) view.findViewById(R.id.title);
                    tv_num = (TextView) view.findViewById(R.id.note);
                    row = (LinearLayout) view.findViewById(R.id.row);
                    view.setTag(this);
                }
            }
        }

    }

}
