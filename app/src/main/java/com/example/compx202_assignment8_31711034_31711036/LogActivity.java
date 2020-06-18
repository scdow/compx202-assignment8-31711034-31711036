package com.example.compx202_assignment8_31711034_31711036;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class LogActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        //hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //set stick immersive full-screen mode
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        getWindow().getDecorView().setSystemUiVisibility(uiOptions);


    }
    public void onClickButtonLogin(View view){
//        Toast.makeText(this, "message", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);


        //get Edittext
        EditText et = (EditText)findViewById(R.id.UsernameInput);
        String username = et.getText().toString();
        intent.putExtra("username",username);


        startActivity(intent);
    }
}
