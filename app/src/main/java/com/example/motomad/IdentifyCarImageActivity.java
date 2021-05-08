package com.example.motomad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IdentifyCarImageActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageView, imageView1, imageView2;
    private  TextView text, result_message;
    private Button submit;
    private List<CarsData> cars;
    private List<CarsData> RandomCarsList;
    private boolean click = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_car_image);
        imageView = findViewById(R.id.image_1);
        imageView1 = findViewById(R.id.image_2);
        imageView2 = findViewById(R.id.image_3);
        result_message = findViewById(R.id.status);
        imageView.setOnClickListener(this);
        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        text = findViewById(R.id.carMake);
        submit = findViewById(R.id.next_btn);

        //Initialize the cars list.
        RandomCarsList = new ArrayList<>();
        cars = new ArrayList<>();

        //String names for cars.
        String[] names = getResources().getStringArray(R.array.checked_items);
        int[] images = {R.drawable.img_0, R.drawable.img_1, R.drawable.img_2, R.drawable.img_3, R.drawable.img_4, R.drawable.img_5, R.drawable.img_6, R.drawable.img_7, R.drawable.img_8, R.drawable.img_9, R.drawable.img_10, R.drawable.img_11, R.drawable.img_12, R.drawable.img_13, R.drawable.img_14, R.drawable.img_15, R.drawable.img_16, R.drawable.img_17, R.drawable.img_18, R.drawable.img_19, R.drawable.img_20, R.drawable.img_21, R.drawable.img_22, R.drawable.img_23, R.drawable.img_24, R.drawable.img_25, R.drawable.img_26, R.drawable.img_27, R.drawable.img_28, R.drawable.img_29};
        for (int i = 0; i < images.length; i++) {
            //Adding text and car image into one custom data type to match image and name.
            cars.add(new CarsData(images[i], names[i]));
        }
        Random random = new Random();

        //Randomly generating car images.
        Label:
        for(int i=0;i<cars.size();i++){
            CarsData position=cars.get(random.nextInt(cars.size()));
            boolean nameMatch=false;
            //Check whether 3 car limit achieved.
            if(RandomCarsList.size()==3){
                //Limit reached then break loop.
                break Label;
            }else{
                //If limit is not reached set nameMatch to false.

                //Check the generated car is already in RandomCars.
                if(!RandomCarsList.contains(position)){
                    //Check if the car name is already exist in random cars.
                    for(int b=0;b<RandomCarsList.size();b++){
                        if(RandomCarsList.get(b).getName().toLowerCase().equals(position.getName().toLowerCase())){
                            nameMatch=true;
                        }
                    }
                    //If the name not exists in random, add it to random cars.
                    if(!nameMatch)
                        RandomCarsList.add(position);
                }
            }

        }
        //Set images to imageViews.
        imageView.setImageResource(RandomCarsList.get(0).image);
        imageView1.setImageResource(RandomCarsList.get(1).image);
        imageView2.setImageResource(RandomCarsList.get(2).image);
        //Set correct text to textView.
        text.setText(RandomCarsList.get(random.nextInt(RandomCarsList.size())).getName());

        //Submit button.
        submit.setOnClickListener(v -> {
            if (click) {
                startActivity(new Intent(IdentifyCarImageActivity.this, IdentifyCarImageActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Tap an image", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_1:
                //Click on first image.
                if (!click) {
                    //If already not attempt set to attempt.
                    click = true;
                    //Check if match or not.
                    if (RandomCarsList.get(0).getName().equals(text.getText().toString())) {
                        //If matched then show messages.
                        text.setTextColor(getResources().getColor(R.color.teal_700));
                        result_message.setVisibility(View.VISIBLE);
                        result_message.setText("CORRECT!");
                        result_message.setTextColor(getResources().getColor(R.color.teal_700));
                    } else {
                        //Not matched then show messages.
                        text.setTextColor(getResources().getColor(R.color.red));
                        result_message.setVisibility(View.VISIBLE);
                        result_message.setText("WRONG!");
                        result_message.setTextColor(getResources().getColor(R.color.red));
                    }
                } else {
                    //If already attempted then show message.
                    Toast.makeText(this, "Click Next", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.image_2:
                if (!click) {
                    click = true;
                    if (RandomCarsList.get(1).getName().equals(text.getText().toString())) {
                        text.setTextColor(getResources().getColor(R.color.teal_700));
                        result_message.setVisibility(View.VISIBLE);
                        result_message.setText("CORRECT!");
                        result_message.setTextColor(getResources().getColor(R.color.teal_700));
                    } else {
                        text.setTextColor(getResources().getColor(R.color.red));
                        result_message.setVisibility(View.VISIBLE);
                        result_message.setText("WRONG!");
                        result_message.setTextColor(getResources().getColor(R.color.red));
                    }
                } else {
                    Toast.makeText(this, "Click Next", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.image_3:
                if (!click) {
                    click = true;
                    if (RandomCarsList.get(2).getName().equals(text.getText().toString())) {
                        text.setTextColor(getResources().getColor(R.color.teal_700));
                        result_message.setVisibility(View.VISIBLE);
                        result_message.setText("CORRECT!");
                        result_message.setTextColor(getResources().getColor(R.color.teal_700));
                    } else {
                        text.setTextColor(getResources().getColor(R.color.red));
                        result_message.setVisibility(View.VISIBLE);
                        result_message.setText("WRONG!");
                        result_message.setTextColor(getResources().getColor(R.color.red));
                    }
                } else {
                    Toast.makeText(this, "Click Next", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}