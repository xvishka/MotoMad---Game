package com.example.motomad;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IdentifyCarMakeActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener  {

    private Button identify_button;
    private TextView display_message,result_message,entered_message,wrong_message,wrong_message1;
    private boolean check = false;
    private int position = 0;
    private List<CarsData> cars;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_car_make);

        //Initialize text view.
        display_message=findViewById(R.id.display_message);
        result_message=findViewById(R.id.status);
        entered_message=findViewById(R.id.entered_message);
        wrong_message=findViewById(R.id.wrong_message);
        wrong_message1=findViewById(R.id.wrong_message1);

        //Creating dropdown spinner - code copied from tutorial two.
        Spinner car_make_spinner = findViewById(R.id.car_make_spinner);
        if (car_make_spinner != null) {
            car_make_spinner.setOnItemSelectedListener(this);
        }

        //Array adapter is needed to put items into spinner.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_items, android.R.layout.simple_spinner_item);

        //Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        //Apply the adapter to the spinner.
        if (car_make_spinner != null) {
            car_make_spinner.setAdapter(adapter);
        }
        //Cars name getting from string resource.
        String[] names=getResources().getStringArray(R.array.checked_items);

        //Cars data list.
        cars=new ArrayList<>();

        //Loading car images.
        final ImageView img = (ImageView) findViewById(R.id.car_imageView);
        int[] images = {R.drawable.img_0,R.drawable.img_1,R.drawable.img_2,R.drawable.img_3,R.drawable.img_4,R.drawable.img_5,R.drawable.img_6,R.drawable.img_7,R.drawable.img_8,R.drawable.img_9,R.drawable.img_10,R.drawable.img_11,R.drawable.img_12,R.drawable.img_13,R.drawable.img_14,R.drawable.img_15,R.drawable.img_16,R.drawable.img_17,R.drawable.img_18,R.drawable.img_19,R.drawable.img_20,R.drawable.img_21,R.drawable.img_22,R.drawable.img_23,R.drawable.img_24,R.drawable.img_25,R.drawable.img_26,R.drawable.img_27,R.drawable.img_28,R.drawable.img_29};
       for(int i=0;i<images.length;i++){

           //Adding text and car image into one custom data type to match image and name.
           cars.add(new CarsData(images[i],names[i]));
       }
        Random random = new Random();

        //Break the loop to prevent the same image occurrence.
        Label:
        for(int a=0;a<cars.size();a++){
           position=random.nextInt(cars.size());
           if(Constant.IdentifyCarRandomList!=null)
           {
               if(Constant.IdentifyCarRandomList.size()>0)
               {
                   if(!Constant.IdentifyCarRandomList.contains(position))
                   {
                     Constant.IdentifyCarRandomList.add(position);
                     break Label;

           }
                   else if(Constant.IdentifyCarRandomList.size()==cars.size()){

               Constant.IdentifyCarRandomList.clear();
               Constant.IdentifyCarRandomList.add(position);
               break Label;
           }
               }else{
                   Constant.IdentifyCarRandomList.add(position);
               }
           }
           else{
               Constant.IdentifyCarRandomList=new ArrayList<>();
               Constant.IdentifyCarRandomList.add(position);
           }
       }
        img.setImageResource(images[position]);

        //Button functionality.
        identify_button=findViewById(R.id.identify_button);
        identify_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check the answer is correct or not.
                if(!check){
                    String selected_name=car_make_spinner.getSelectedItem().toString();
                    if(selected_name.equals(cars.get(position).name)){
                        display_message.setText("CORRECT!");
                        display_message.setTextColor(getResources().getColor(R.color.teal_700));
                        check=true;
                        result_message.setText(cars.get(position).name);
                        display_message.setVisibility(View.VISIBLE);
                        result_message.setVisibility(View.VISIBLE);

                        identify_button.setText("Next");

                    }else{
                        display_message.setText("WRONG!");
                        display_message.setTextColor(getResources().getColor(R.color.red));
                        check=true;
                        entered_message.setVisibility(View.VISIBLE);
                        entered_message.setText(selected_name);
                        result_message.setText(cars.get(position).name);
                        identify_button.setText("Next");
                        display_message.setVisibility(View.VISIBLE);
                        result_message.setVisibility(View.VISIBLE);
                        wrong_message.setVisibility(View.VISIBLE);
                        wrong_message1.setVisibility(View.VISIBLE);
                    }
                }else{
                    startActivity(new Intent(IdentifyCarMakeActivity.this,IdentifyCarMakeActivity.class));
                    finish();
                }
            }
        });

    }

        //Assigning the selected spinner value.
        @Override
        public void onItemSelected (AdapterView < ? > parent, View view,int i, long id){
            //Initiated within onCreate method.
        }

        @Override
        public void onNothingSelected (AdapterView < ? > parent){
            //Empty.
        }

    }






    



