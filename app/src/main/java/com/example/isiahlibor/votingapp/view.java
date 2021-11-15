package com.example.isiahlibor.votingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class view extends AppCompatActivity {

    ListView listPres, listVice, listSec,listTrea;

    Intent back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        listPres = (ListView)findViewById(R.id.listPres);
        listVice = (ListView)findViewById(R.id.listVice);
        listSec = (ListView)findViewById(R.id.listSec);
        listTrea = (ListView)findViewById(R.id.listTrea);

        receiveData();

    }

    private void receiveData(){
        try{
            Intent i=this.getIntent();
            list list = (list) i.getSerializableExtra("list");
            listPres.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, list.getList()));
        }catch (Exception e){
            Toast.makeText(view.this,""+e,Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onBackPressed() {

        back.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(back);
        view.super.finish();

    }

}
