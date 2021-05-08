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

public class HintActivity extends AppCompatActivity {
    private Button submit;
    private TextView click_message,result_message,dash_text,success_message;
    private int position=0;
    private List<CarsData> cars;
    private EditText edit_text;
    private int next=0;
    private String dashString,matchedString,clickText="";
    private int wrongAnsLimit=3;;
    private StringBuilder dashStringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);
        success_message=findViewById(R.id.statusMsg);
        result_message=findViewById(R.id.status);
        dash_text=findViewById(R.id.dash_text);
        click_message=findViewById(R.id.users_input);
        submit=findViewById(R.id.submit_button);
        edit_text=findViewById(R.id.edit_text);
        //Cars name getting from string file.
        String[] names=getResources().getStringArray(R.array.checked_items);
        //Cars data list.
        cars=new ArrayList<>();


        //Getting images randomly.
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
            if(Constant.Hint!=null)
            {
                if(Constant.Hint.size()>0)
                {
                    if(!Constant.Hint.contains(position))
                    {
                        Constant.Hint.add(position);
                        break Label;
                    }
                    else if(Constant.Hint.size()==cars.size()){
                        Constant.Hint.clear();
                        Constant.Hint.add(position);
                        break Label;
                    }
                }else{
                    Constant.Hint.add(position);
                }
            }
            else{
                Constant.Hint=new ArrayList<>();
                Constant.Hint.add(position);
            }
        }


        img.setImageResource(images[position]);
        //Dash string initialize empty.
        dashString="";
        //Loop for count dashes against car name.
        for(int i=0 ;i<names[position].length();i++){
            dashString=dashString+"-";
        }
        //Dash string builder initialize.
        dashStringBuilder=new StringBuilder(dashString);
        //Firstly set dashes to text view.
        dash_text.setText(dashString);
        //Matched name of car generated to identify.
        matchedString=cars.get(position).name.toLowerCase();
        //Submit button.
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Convert to lowercase.
                String text=edit_text.getText().toString().toLowerCase();
                //Check here when all dashes completed of all limited wrong answers will over, then next will  = 1 .
                if(next==1){
                    //Next button to new intent.
                    startActivity(new Intent(HintActivity.this,HintActivity.class));
                    finish();
                }
                //If next = 0 equals wrong answers limit is not over or you are entering right hints.
                else{
                    //Check is edit text giving empty or not.
                if(!text.isEmpty()){
                    //Convert text item on 0 index to char.
                char character=text.charAt(0);
                    //Call method to do functionality
                newMethod(character);
                }else{
                    //If edit text return empty text.
                Toast.makeText(HintActivity.this, "Guess a character", Toast.LENGTH_SHORT).show();
            }
                }
            }
        });


    }
    private void newMethod(char ch){

        clickText=clickText+ch;
        //Check match string is empty or not.
        if(!matchedString.isEmpty()){
            //Check match string is containing character entered and convert char variable into string.
            if(matchedString.contains(String.valueOf(ch))){
                //Loop on length of match string.
                for(int i=0;i<matchedString.length();i++){
                    //Check entered text position.
                    if(ch==matchedString.charAt(i)){
                        //Replace the dash on location where text is matched.
                        dashStringBuilder.replace(i,i+1,String.valueOf(ch));
                        //Set empty to edit text to get another character.
                        edit_text.setText("");
                    }
                }
                //Check whether dash string builder having dashes or not, check tells text will not contain dashes.
                if(!dashStringBuilder.toString().contains("-")){
                    //Change submit button name into next.
                    submit.setText("Next");
                    success_message.setVisibility(View.VISIBLE);
                    result_message.setVisibility(View.VISIBLE);
                    success_message.setTextColor(getResources().getColor(R.color.teal_700));
                    success_message.setText("Successful");
                    edit_text.setVisibility(View.GONE);
                    result_message.setText(cars.get(position).name);
                    //Set next submit button check 1 to move next car.
                    next=1;

                }
            }else{
                //This check will run when the text is not a car name.
                if(wrongAnsLimit>1){
                    //wrong answer limit will reduce by 1 .
                    wrongAnsLimit=wrongAnsLimit-1;
                    //Set empty to edit text.
                    edit_text.setText("");
                    Toast.makeText(this, "Wrong guess", Toast.LENGTH_SHORT).show();
                }else{
                    submit.setText("Next");
                    success_message.setVisibility(View.VISIBLE);
                    result_message.setVisibility(View.VISIBLE);
                    success_message.setText("WRONG!");
                    click_message.setText(clickText);
                    click_message.setVisibility(View.VISIBLE);
                    success_message.setTextColor(getResources().getColor(R.color.red));
                    result_message.setText(cars.get(position).name);
                    edit_text.setVisibility(View.GONE);
                    next=1;
                }
            }
        }
        dash_text.setText(dashStringBuilder);

    }
}