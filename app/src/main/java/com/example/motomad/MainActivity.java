package com.example.motomad;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //onClick to evoke identify car make activity.
        Button identify_car_make_btn = findViewById(R.id.identify_car_make_btn);
        identify_car_make_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IdentifyCarMakeActivity.class);
                startActivity(intent);
            }
        });

        //onClick to evoke hints activity.
        findViewById(R.id.hints_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HintActivity.class);
                startActivity(intent);
            }
        });

        //onClick to evoke identify car image activity.
        findViewById(R.id.identify_car_image_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IdentifyCarImageActivity.class);
                startActivity(intent);
            }
        });

        //onClick to evoke advanced level activity.
        findViewById(R.id.advanced_level_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AdvancedLevelActivity.class);
                startActivity(intent);
            }
        });

    }
}


