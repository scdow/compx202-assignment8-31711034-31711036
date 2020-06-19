package com.example.compx202_assignment8_31711034_31711036;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ScoreActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        //hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //set stick immersive full-screen mode
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        getWindow().getDecorView().setSystemUiVisibility(uiOptions);

        //get extra data from intent(String)
        Intent intentA = getIntent();
        String user = intentA.getStringExtra("username");
        //set textview text to the string
        TextView tv = (TextView)findViewById(R.id.mainTitleUserName);
        tv.setText(user);

        Intent intentNow = getIntent();
        String  userNow = intentNow.getStringExtra("user");
//        TextView tv = (TextView)findViewById(R.id.mainTitleUserName);
        tv.setText(userNow);


        TextView tv1 = (TextView)findViewById(R.id.mainTitleScoreNumber);
        int scoreNow =intentNow.getIntExtra("score",0);
        String scoreNowStr = String.valueOf(scoreNow);
        tv1.setText(scoreNowStr);

        String[] listArray = new String[5];
        for (int i = 0;i<5;i++){
            listArray[i]="need more infomation";
        }

//        Intent intentStr1 = getIntent();
//        String  str1 = "";
//        str1=intentStr1.getStringExtra("username_score");
//        listArray[0] = str1;

        // Create a new ArrayAdapter<String>
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listArray);

        // Get the ListView
        ListView lv = (ListView) findViewById(R.id.listView);

        //Set the adapter on the ListView
        lv.setAdapter(adapter);
    }
}
