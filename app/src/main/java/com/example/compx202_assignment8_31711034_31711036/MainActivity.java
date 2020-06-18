package com.example.compx202_assignment8_31711034_31711036;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //set stick immersive full-screen mode
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        getWindow().getDecorView().setSystemUiVisibility(uiOptions);

        //get extra data from intent(String)
        Intent intent = getIntent();
        String user = intent.getStringExtra("username");
        //set textview text to the string
        TextView tv = (TextView)findViewById(R.id.mainTitleHiUsername);
        tv.setText(user);

    }

    public void onClickButtonStart(View view){
//        Toast.makeText(this, "message", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);

    }
    public void onClickButtonScore(View view){
//        Toast.makeText(this, "message", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ScoreActivity.class);
        startActivity(intent);

    }
    public void onClickButtonLogout(View view){
//        Toast.makeText(this, "message", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LogActivity.class);
        startActivity(intent);

    }
}
