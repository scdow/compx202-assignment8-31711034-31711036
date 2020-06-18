package com.example.compx202_assignment8_31711034_31711036;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onclickStart (View view){

//        Toast.makeText(this, "onclickStart work", Toast.LENGTH_SHORT).show();

        // Create new intent for ListActivity
        Intent intent = new Intent(this, GameActivity.class);

        // Start the activity with the intent
        startActivity(intent);

    }
}
