package com.example.isiahlibor.votingapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.sqlitelib.DataBaseHelper;
import com.sqlitelib.SQLite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class admin extends AppCompatActivity {

    EditText name, num;
    Spinner post;
    Button btnAdd, btnLogout, btnView;

    String getPost;

    Intent out, view;

    ArrayList<String> candidates;

    private DataBaseHelper dbhelper = new DataBaseHelper(admin.this, "voterDatabase", 2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        name = (EditText)findViewById(R.id.name);
        num = (EditText)findViewById(R.id.num);

        post = (Spinner)findViewById(R.id.post);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnLogout = (Button)findViewById(R.id.btnLogout);
        btnView = (Button)findViewById(R.id.btnView);

        out = new Intent(admin.this, MainActivity.class);
        view = new Intent(admin.this, view.class);

        name();
        number();

        position();

        btnAdd();
        btnLogout();
        btnView();

        reloadAll();

    }

    private void reloadAll(){

        setDefault();
        reload();
        reloadPres();

    }

    private void setDefault(){

        name.setText("");
        num.setText("");
        post.setPrompt("please choose...");

    }

    private void reloadPres(){

        try {
            SQLiteDatabase db = dbhelper.getWritableDatabase();
            Cursor list = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='tblcandidates'", null);
            list.moveToNext();
            if (list.getCount() == 0) {
                SQLite.FITCreateTable("voterDatabase", this, "tblcandidates",
                        " id INTEGER(90) AUTO-INCREMENT PRIMARY KEY," +
                                "name VARCHAR(90), position VARCHAR(90), number INTEGER");
            }else {
                list = db.rawQuery("Select * FROM tblcandidates WHERE name = '" + "qqq" + "'" , null);
                String value[] = new String[list.getCount()];
                int ctrl = 0;
                candidates.clear();
                while (list.moveToNext()) {
                    String strFor = "";
                    strFor += System.lineSeparator() + "Name: " + list.getString(list.getColumnIndex("name"));
                    strFor += System.lineSeparator() + "Contact #: " + list.getString(list.getColumnIndex("number"));
                    value[ctrl] = strFor;
                    candidates.add(strFor);
                    ctrl++;
                }
            }
        } catch (Exception e) {
            Toast.makeText(admin.this,"pres-"+e,Toast.LENGTH_LONG).show();
        }

    }

    private void reload(){

        try {

            SQLiteDatabase db = dbhelper.getWritableDatabase();
            Cursor candids = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='tblcandidates'", null);
            candids.moveToNext();

            if (candids.getCount() == 0) {
                SQLite.FITCreateTable("voterDatabase", this, "tblcandidates",
                        " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "name VARCHAR(90), position VARCHAR(90), number INTEGER");
            }

        } catch (Exception e) {
            Toast.makeText(admin.this,"reload-"+e,Toast.LENGTH_LONG).show();
        }

    }

    private void btnLogout(){

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(out);
                admin.super.finish();

            }
        });

    }

    private list getList()
    {
        list list = new list();
        list.setList(candidates);
        return list;
    }

    private void sendData()
    {
        view.putExtra("list",this.getList());
        startActivity(view);
    }

    private void btnView(){

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendData();

            }
        });

    }

    private void btnAdd(){

        final SQLiteDatabase db = dbhelper.getWritableDatabase();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    String sqlStr = "INSERT INTO tblcandidates (name, position, number) VALUES ('"
                            + name.getText().toString() + "', '" + getPost + "', '" + num.getText().toString() + "')";
                    db.execSQL(sqlStr);
                    reloadAll();
                    Toast.makeText(admin.this,"candidate registered",Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    Toast.makeText(admin.this,"insert-"+e,Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void position(){

        post.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                getPost = post.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void number(){

        num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                if(num.length() != 0) {
                } else {
                    if(num.getText().length() == 0) {
                        num.setError("This field cannot be blank");
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void name(){

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(name.length() != 0) {
                } else {
                    if(name.getText().length() == 0) {
                        name.setError("This field cannot be blank");
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

}
