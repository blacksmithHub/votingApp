package com.example.isiahlibor.votingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText pass;
    Button btnlogin;

    Intent admin, voter;

    String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pass = (EditText)findViewById(R.id.txtpass);
        btnlogin = (Button)findViewById(R.id.btnlogin);

        admin = new Intent(MainActivity.this, admin.class);
        voter = new Intent(MainActivity.this, voter.class);

        code = "admin";

        password();
        login();

    }

    private void password(){

        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(pass.length() != 0) {
                } else {
                    if(pass.getText().length() == 0) {
                        pass.setError("This field cannot be blank");
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void login(){

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pass.getText().toString().equals(code)){

                    startActivity(admin);

                }else{

                    startActivity(voter);

                }


            }
        });

    }

}
