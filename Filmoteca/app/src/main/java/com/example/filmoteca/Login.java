package com.example.filmoteca;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    SharedPreferences prefs;
    EditText editEmail,editPassword;
    TextView web;
    String email,password;
    Activity activity=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        inicializate();
    }

    public void inicializate(){
        editEmail=findViewById(R.id.editEmail);
        editPassword=findViewById(R.id.editPassword);
        web=findViewById(R.id.web);
        email="";
        password="";
    }

    public void login(View view){
        if(checkValues()) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("email", email);
            editor.putString("password", password);
            editor.commit();
            finish();
        }
        else{
            Toast.makeText(activity, getString(R.string.e_email_pass), Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkValues(){
        boolean itsOk=true;
        email=editEmail.getText().toString();
        password=editPassword.getText().toString();
        if(email.equals("")){
            itsOk=false;
        }
        if(password.equals("")){
            itsOk=false;
        }
        return itsOk;
    }

    public void openWeb(View view){
        String url=web.getText().toString();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
