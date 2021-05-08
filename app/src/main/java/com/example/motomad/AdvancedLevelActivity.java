package com.example.motomad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AdvancedLevelActivity extends AppCompatActivity  {
    private ImageView imageView,imageView1,imageView2;
    private EditText editText,editText1,editText2;
    private TextView statusMsg, scoreMsg;
    private Button submit;
    private List<CarsData> cars;
    private List<CarsData> RandomCarsList;
    private int wrongAttempts=3;
    private int check=0;
    private String text,text1,text2;
    private TextView textview,textView1,textView2,result_message;
    private int score=0;
    private boolean option=false,option1=false,option2=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_level);
        imageView=findViewById(R.id.image1);
        imageView1=findViewById(R.id.image2);
        imageView2=findViewById(R.id.image3);
        editText=findViewById(R.id.guessField1);
        editText1=findViewById(R.id.guessField2);
        editText2=findViewById(R.id.guessField3);
        statusMsg=findViewById(R.id.statusMsg);
        scoreMsg=findViewById(R.id.status);
        textview=findViewById(R.id.correctName1);
        textView1=findViewById(R.id.correctName2);
        textView2=findViewById(R.id.correctName3);
        result_message=findViewById(R.id.status);
        scoreMsg.setText("Score = "+Constant.total);
        submit=findViewById(R.id.submitBtn);

        //Initialize the cars list.
        RandomCarsList=new ArrayList<>();
        cars=new ArrayList<>();

        //String names for cars.
        String[] names=getResources().getStringArray(R.array.checked_items);
        int[] images = {R.drawable.img_0,R.drawable.img_1,R.drawable.img_2,R.drawable.img_3,R.drawable.img_4,R.drawable.img_5,R.drawable.img_6,R.drawable.img_7,R.drawable.img_8,R.drawable.img_9,R.drawable.img_10,R.drawable.img_11,R.drawable.img_12,R.drawable.img_13,R.drawable.img_14,R.drawable.img_15,R.drawable.img_16,R.drawable.img_17,R.drawable.img_18,R.drawable.img_19,R.drawable.img_20,R.drawable.img_21,R.drawable.img_22,R.drawable.img_23,R.drawable.img_24,R.drawable.img_25,R.drawable.img_26,R.drawable.img_27,R.drawable.img_28,R.drawable.img_29};
        for(int i=0;i<images.length;i++){
            //Adding text and car image into one custom data type to match image and name.
            cars.add(new CarsData(images[i],names[i]));
        }
        Random random = new Random();

        Label:
        for(int i=0;i<cars.size();i++){
        CarsData position=cars.get(random.nextInt(cars.size()));
            boolean nameMatch=false;
       if(RandomCarsList.size()==3){
           break Label;
       }else{

        if(!RandomCarsList.contains(position)){
            for(int b=0;b<RandomCarsList.size();b++){
                if(RandomCarsList.get(b).getName().toLowerCase().equals(position.getName().toLowerCase())){
                    nameMatch=true;
                }
            }
            if(!nameMatch)
           RandomCarsList.add(position);
        }
       }
        }

        imageView.setImageResource(RandomCarsList.get(0).image);
        imageView1.setImageResource(RandomCarsList.get(1).image);
        imageView2.setImageResource(RandomCarsList.get(2).image);

        submit.setOnClickListener(v -> {

            //Check whether all images are selected correct or not  or   wrong attempts are over.
            if((option && option1 && option2) || wrongAttempts ==0 ){
                startActivity(new Intent(AdvancedLevelActivity.this,AdvancedLevelActivity.class));
                finish();
            }
            else{
                //Check whether images are correct or not if not then get text for them.
                if(!option)
           text=editText.getText().toString();
                if(!option1)
            text1=editText1.getText().toString();
                if(!option2)
           text2=editText2.getText().toString();
                //If edit text of cars input empty.
                if(text.isEmpty() && text1.isEmpty() && text2.isEmpty()){
                    Toast.makeText(this, "Guesses missing!", Toast.LENGTH_SHORT).show();
                }else{
                   //Go to the method to check if entered text for cars is correct or not.
                    checkCars(text.toLowerCase(),text1.toLowerCase(), text2.toLowerCase());
                }
            }

        });

    }
    private void checkCars(String text, String text1,String text2){
        //Check whether this image already selected correct or not.
               if(!option){
                   //Compare text entered with correct text.
                    if(text.equals(RandomCarsList.get(0).getName().toLowerCase())){
                        textview.setTextColor(getResources().getColor(R.color.teal_700));
                        editText.setVisibility(View.GONE);
                        textview.setText(text);
                        textview.setVisibility(View.VISIBLE);
                        score = score +1;
                        //If true, set unable to re-enter data in this field.
                        option=true;
                    }else{
                        editText.setTextColor(getResources().getColor(R.color.red));
                        //If false, to get again entry.
                        option=false;
                    }
                }

                if(!option1){

                    if(text1.equals(RandomCarsList.get(1).getName().toLowerCase())){
                        textView1.setTextColor(getResources().getColor(R.color.teal_700));
                        editText1.setVisibility(View.GONE);
                        textView1.setText(text1);
                        textView1.setVisibility(View.VISIBLE);
                        score = score +1;
                        option1=true;
                    }else{
                        editText1.setTextColor(getResources().getColor(R.color.red));
                        option1=false;

                    }
                }

                if(!option2){

                    if(text2.equals(RandomCarsList.get(2).getName().toLowerCase())){
                        textView2.setTextColor(getResources().getColor(R.color.teal_700));
                        editText2.setVisibility(View.GONE);
                        textView2.setText(text2);
                        textView2.setVisibility(View.VISIBLE);
                        score = score +1;
                      option2=true;
                    }else{
                        editText2.setTextColor(getResources().getColor(R.color.red));
                        option2=false;
                    }

                }

        //Check here all cars are selected correct or not.
        if(option && option1 && option2){
            statusMsg.setVisibility(View.VISIBLE);
            statusMsg.setText("CORRECT!");
            statusMsg.setTextColor(getResources().getColor(R.color.teal_700));
            result_message.setVisibility(View.VISIBLE);
            Constant.total=Constant.total+ score;
            result_message.setText("Score = "+Constant.total);
            submit.setText("Next");
        }else{
            //If all cars are not selected and wrong answer found then check if user have limit of wrong answers.
            if(wrongAttempts >1)
            wrongAttempts = wrongAttempts -1;
            else{
                //If don't have enough wrong answer limit show.
                wrongAttempts =0;
                statusMsg.setVisibility(View.VISIBLE);
                statusMsg.setTextColor(getResources().getColor(R.color.red));
                statusMsg.setText("WRONG!");

                editText.setVisibility(View.GONE);
                editText1.setVisibility(View.GONE);
                editText2.setVisibility(View.GONE);
                textview.setVisibility(View.VISIBLE);
                textView1.setVisibility(View.VISIBLE);
                //Show all correct answers.
                textview.setText(RandomCarsList.get(0).getName());
                textView1.setText(RandomCarsList.get(1).getName());
                textView2.setText(RandomCarsList.get(2).getName());

                result_message.setVisibility(View.VISIBLE);
                Constant.total=Constant.total+ score;
                result_message.setText("Score = "+Constant.total);
                textView2.setVisibility(View.VISIBLE);
                submit.setText("Next");
                check=1;
            }
        }

        }



}