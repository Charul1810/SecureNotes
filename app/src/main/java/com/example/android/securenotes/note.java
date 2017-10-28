package com.example.android.securenotes;

/**
 * Created by niharia on 10/09/2017.
 */

public class note {
    int _id;
    String _title;
    String _note;
    String _time;
    //Date _time;

    public note(int _id, String _title, String _note) {
        this._id = _id;
        this._title = _title;
        this._note = _note;
    }

    public note(String _title, String _note, String _time) {
        this._title = _title;
        this._note = _note;
        this._time = _time;
    }

//    public String getDateTime()
//    {
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
//         Date date=new java.util.Date();
//
//        return sdf.format(date);
//    }

    public note() {
    }

    public note(String _title, String _note) {
        this._title = _title;
        this._note = _note;
    }

//    public note(int _id, String _title, String _note, Date _time) {
//        this._id = _id;
//        this._title = _title;
//        this._note = _note;
//        this._time = _time;
//    }

//    public note(String _title, String _note, Date _time) {
//        this._title = _title;
//        this._note = _note;
//        this._time = _time;
//    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public String get_note() {
        return _note;
    }

    public void set_note(String _note) {
        this._note = _note;
    }



//    public Date get_time() {
//        return _time;
//    }
//
//    public void set_time(Date _time) {
//        this._time = _time;
//    }
}
