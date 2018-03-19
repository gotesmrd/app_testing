package com.example.application.customizabletimerapptry1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Calendar;
import android.os.Handler;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {

    TextView t1, t2;
    ImageView reminderPic, timerPic;
    Bitmap imageBitmap;
    RoundedBitmapDrawable roundedBitmapDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //displayDate();
        t1 = (TextView) findViewById(R.id.textView);
        t2 = (TextView) findViewById(R.id.textView2);
        ////////////////
        timerPic=(ImageView)findViewById(R.id.imageView7);
        //get bitmap of the image
        imageBitmap= BitmapFactory.decodeResource(getResources(),  R.drawable.ic_timer_white_48dp_background);
        roundedBitmapDrawable= RoundedBitmapDrawableFactory.create(getResources(), imageBitmap);
        //setting radius
        roundedBitmapDrawable.setCircular(true);
        //roundedBitmapDrawable.setCornerRadius(50.0f);
        //roundedBitmapDrawable.setAntiAlias(true);
        timerPic.setImageDrawable(roundedBitmapDrawable);
        /////////////
        reminderPic=(ImageView)findViewById(R.id.imageView8);
        //get bitmap of the image
        imageBitmap= BitmapFactory.decodeResource(getResources(),  R.drawable.ic_reminder_white_48dp_background);
        roundedBitmapDrawable= RoundedBitmapDrawableFactory.create(getResources(), imageBitmap);
        //setting radius
        roundedBitmapDrawable.setCircular(true);
        //roundedBitmapDrawable.setCornerRadius(50.0f);
        //roundedBitmapDrawable.setAntiAlias(true);
        reminderPic.setImageDrawable(roundedBitmapDrawable);

        handler.postDelayed(r, 0);
    }
    String [] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUNE", "JULY", "AUG", "SEPT", "OCT", "NOV","DEC"};
    String [] day = {"","SUN", "MON", "TUES","WED","THUR","FRI","SAT"};

    Handler handler = new Handler();
    Runnable r = new Runnable() {
            @Override
            public void run() {
                String period = "AM";
                Calendar calendar = Calendar.getInstance(Locale.getDefault());
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                int date = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                int second = calendar.get(Calendar.SECOND);
                int day_of_week = calendar.get(Calendar.DAY_OF_WEEK);

                if (hour >11)
                {
                    period = "PM";
                }
                hour = hour%12;
                if(hour==0)
                {
                    hour=12;
                }
                //Log.i("MyMessage", Integer.toString(day_of_week));
                String s = String.format("%02d:%02d:%02d %s", hour, minute, second, period);
                SpannableString ss1=  new SpannableString(s);
                ss1.setSpan(new RelativeSizeSpan(3f), 0,8, 0); // set size
                t1.setText(ss1);


                //t1.setText(String.format("%02d:%02d:%02d %s", hour, minute, second, period));
                t2.setText(String.format("%s %02d %s", day[day_of_week], date, months[month]));

                handler.postDelayed(this, 100);
            }

    };

    public void timer_screen(View v)
    {
        Intent i = new Intent(this, Timer.class);
        startActivity(i);
    }
    public void scrollingPractice(View v)
    {
        Intent i2 = new Intent(this, testingOutScrollableTextViews.class);
        startActivity(i2);
    }


}
