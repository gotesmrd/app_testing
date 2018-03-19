package com.example.application.customizabletimerapptry1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class testingOutScrollableTextViews extends AppCompatActivity implements MediaPlayer.OnCompletionListener {

    TextView t5, tv6, tv7, tv8;
    int day_real, month_real, year_real, day_of_week, selected_day, selected_month, selected_year,selected_hour,selected_minute;
    String [] day = {"","SUN", "MON", "TUES","WEDN","THURS","FRI","SAT"};
    Button b2,b3, b4;
    MediaRecorder mediaRecorder;
    String mFileName;
    //Switch aSwitch;
    //boolean switched_once=false;//, recordedAudio = false;
    Toast toast;
    String period, s;
    ImageView recordPic, stopPic, playPic, pausePic, audioFile;
    Bitmap imageBitmap;
    RoundedBitmapDrawable roundedBitmapDrawable;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_out_scrollable_text_views);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        /////////////////
        recordPic=(ImageView)findViewById(R.id.imageView2);
        //get bitmap of the image
        imageBitmap=BitmapFactory.decodeResource(getResources(),  R.drawable.ic_mic_white_48dp_background);
        roundedBitmapDrawable=RoundedBitmapDrawableFactory.create(getResources(), imageBitmap);
        //setting radius
        roundedBitmapDrawable.setCircular(true);
        //roundedBitmapDrawable.setCornerRadius(50.0f);
        //roundedBitmapDrawable.setAntiAlias(true);
        recordPic.setImageDrawable(roundedBitmapDrawable);
        //recordPic.setAlpha(0.5f);
        ////////////////////////////////////
        stopPic=(ImageView)findViewById(R.id.imageView3);
        imageBitmap=BitmapFactory.decodeResource(getResources(),  R.drawable.ic_stop_white_48dp_background);
        roundedBitmapDrawable=RoundedBitmapDrawableFactory.create(getResources(), imageBitmap);
        //setting radius
        roundedBitmapDrawable.setCircular(true);
        //roundedBitmapDrawable.setCornerRadius(50.0f);
        //roundedBitmapDrawable.setAntiAlias(true);
        stopPic.setImageDrawable(roundedBitmapDrawable);
        ///////////
        playPic=(ImageView)findViewById(R.id.imageView4);
        //get bitmap of the image
        imageBitmap=BitmapFactory.decodeResource(getResources(),  R.drawable.ic_play_arrow_white_48dp_background);
        roundedBitmapDrawable=RoundedBitmapDrawableFactory.create(getResources(), imageBitmap);
        //setting radius
        roundedBitmapDrawable.setCircular(true);
        //roundedBitmapDrawable.setCornerRadius(50.0f);
        //roundedBitmapDrawable.setAntiAlias(true);
        playPic.setImageDrawable(roundedBitmapDrawable);
        playPic.setAlpha(0.5f);
        playPic.setEnabled(false);

        pausePic=(ImageView)findViewById(R.id.imageView5);
        //get bitmap of the image
        imageBitmap=BitmapFactory.decodeResource(getResources(),  R.drawable.ic_pause_white_48dp_background);
        roundedBitmapDrawable=RoundedBitmapDrawableFactory.create(getResources(), imageBitmap);
        //setting radius
        roundedBitmapDrawable.setCircular(true);
        //roundedBitmapDrawable.setCornerRadius(50.0f);
        //roundedBitmapDrawable.setAntiAlias(true);
        pausePic.setImageDrawable(roundedBitmapDrawable);
//////////////////////////
        audioFile=(ImageView)findViewById(R.id.imageView6);
        //get bitmap of the image
        imageBitmap=BitmapFactory.decodeResource(getResources(),  R.drawable.ic_audiotrack_white_48dp_background);
        roundedBitmapDrawable=RoundedBitmapDrawableFactory.create(getResources(), imageBitmap);
        //setting radius
        roundedBitmapDrawable.setCircular(true);
        //roundedBitmapDrawable.setCornerRadius(50.0f);
        //roundedBitmapDrawable.setAntiAlias(true);
        audioFile.setImageDrawable(roundedBitmapDrawable);


        t5 = (TextView) findViewById(R.id.textView5);
        tv6 = (TextView) findViewById(R.id.textView6);
        tv7 = (TextView) findViewById(R.id.textView7);
        //aSwitch = (Switch) findViewById(R.id.switch1);
        Calendar c = Calendar.getInstance();
        day_real = c.get(Calendar.DAY_OF_MONTH);
        month_real = c.get(Calendar.MONTH);
        year_real = c.get(Calendar.YEAR);
        day_of_week = c.get(Calendar.DAY_OF_WEEK);

        selected_day=day_real;
        selected_month=month_real;
        selected_year=year_real;
        //b2 = (Button) findViewById(R.id.button2);
        //b3 = (Button) findViewById(R.id.button3);
        //b4 = (Button) findViewById(R.id.button4);

        //b3.setVisibility(View.GONE);
        //Set the Month
        tv6.setText(String.format("%d-%02d-%02d",year_real, (month_real+1), day_real));
        tv6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DatePickerDialog dp = new DatePickerDialog(testingOutScrollableTextViews.this, new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                       /* if((year == year_real && month == month_real && dayOfMonth>=day_real)||
                                (year == year_real && month > month_real ) ||
                                (year > year_real))
                        {*/
                        selected_day=dayOfMonth;
                        selected_month=month;
                        selected_year=year;
                        tv6.setText(String.format("%d-%02d-%02d",year, (month+1), dayOfMonth));
                        //}

                    }
                },year_real,month_real,day_real);
                dp.getDatePicker().setMinDate(new Date().getTime());
                dp.show();
            }
        });
        //Set the Time
        period = " AM";
        int hour = 12, min = 0;
        selected_hour = hour;
        selected_minute = min;
        //tv7.setText(String.format("%02d:%02d", hour, min));
        s= String.format("%02d:%02d %s", hour, min, period);
        SpannableString ss1=  new SpannableString(s);
        ss1.setSpan(new RelativeSizeSpan(3f), 0,5, 0); // set size
        tv7.setText(ss1);
        tv7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TimePickerDialog tp = new TimePickerDialog(testingOutScrollableTextViews.this, new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //period = " AM";
                        selected_hour = hourOfDay;
                        selected_minute = minute;
                        if (hourOfDay>=12){
                            period=" PM";
                        }
                        hourOfDay%=12;
                        if(hourOfDay==0)hourOfDay=12;
                        s= String.format("%02d:%02d %s", hourOfDay, minute, period);
                        SpannableString ss1=  new SpannableString(s);
                        ss1.setSpan(new RelativeSizeSpan(3f), 0,5, 0); // set size
                        tv7.setText(ss1);
                        //tv7.setText(String.format("%02d:%02d", hourOfDay, minute));
                    }
                },5,6,false);
                //dp.getDatePicker().setMinDate(new Date().getTime());
                tp.show();
            }
        });
        /*
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if(switched_once)
                {
                    toast.cancel();
                }
                switched_once=true;
                if(isChecked)
                {
                    b4.setTextColor(Color.BLACK);
                    toast = Toast.makeText(testingOutScrollableTextViews.this, "Audio File Activated",
                            Toast.LENGTH_SHORT);
                    toast.show();
                }else
                {
                    b4.setTextColor(Color.GRAY);
                    toast = Toast.makeText(testingOutScrollableTextViews.this, "Audio File Deactivated",
                            Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });*/


    }

    public void save(View v)
    {
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int current_time = hour*60+minute;
        Log.i("Message", Integer.toString(selected_day));
        Log.i("Message", Integer.toString(selected_month));
        Log.i("Message", Integer.toString(selected_year));

        Log.i("Message", Integer.toString(day_real));
        Log.i("Message", Integer.toString(month_real));
        Log.i("Message", Integer.toString(year_real));

        Log.i("Message", Integer.toString(hour));
        Log.i("Message", Integer.toString(minute));
        Log.i("Message", Integer.toString(current_time));
        Log.i("Message", Integer.toString(selected_hour*60+selected_minute));
        if ( selected_day==day_real && selected_month==month_real && selected_year==year_real)
        {
            if (current_time>=(selected_hour*60+selected_minute)){
                if(toast!=null)
                    toast.cancel();
                toast = Toast.makeText(testingOutScrollableTextViews.this, "Selected time passed. Pick another time",
                        Toast.LENGTH_LONG);
                toast.show();
                return;
            }

        }
        //Enter code here that will send data to the MainMenu to add this timer to the arrayList
    }
    public void audioEdit(View v){
        if(toast!=null)
            toast.cancel();
        if(audioActive)
        {
            audioActive=false;
            audioFile.setAlpha(0.5f);
            toast = Toast.makeText(testingOutScrollableTextViews.this, "Audio File Deactivated",
                    Toast.LENGTH_SHORT);
            toast.show();
        }else{
            audioActive=true;
            audioFile.setAlpha(1.0f);
            toast = Toast.makeText(testingOutScrollableTextViews.this, "Audio File Activated",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    boolean audioActive=true;
    public void stop (View v) throws IOException
    {
        audioActive = true; // make the audioactive visible
        playPic.setEnabled(true);//you now have an audio file so you can play.
        playPic.setAlpha(1.0f); // make the button look clickable

        recordPic.setVisibility(View.VISIBLE);
        stopPic.setVisibility(View.GONE);
        audioFile.setAlpha(1.0f); // make sure the audiofile looks non-transparent
        audioFile.setVisibility(View.VISIBLE);

        t5.setVisibility(View.INVISIBLE);
        //b4.setVisibility(View.VISIBLE);
        //b3.setVisibility(View.INVISIBLE);
        //b2.setVisibility(View.VISIBLE);
        //aSwitch.setVisibility(View.VISIBLE);
        //aSwitch.setChecked(true);
        mediaRecorder.stop();
        mediaRecorder.release();
        mp = new MediaPlayer();
        mp.setOnCompletionListener(this);
        mp.setDataSource(path);
        mp.prepare();
    }

    String path;
    public void record(View v) throws IOException
    {
        /*if(mediaRecorder!=null)
        {
            Log.i("Message7", path);
            mediaRecorder.stop();
            Log.i("Message8", path);
            mediaRecorder.release();
            Log.i("Message9", path);
        }*/
        playPic.setEnabled(false);//deactivate the play audio button
        playPic.setAlpha(0.5f);//deactivate the play audio button
        audioFile.setVisibility(View.GONE);//get rid of the audiofile icon
        t5.setVisibility(View.VISIBLE);
        //b4.setVisibility(View.GONE);
        //b2.setVisibility(View.GONE);
        //b3.setVisibility(View.VISIBLE);
        //aSwitch.setVisibility(View.GONE);
        recordPic.setVisibility(View.GONE);
        stopPic.setVisibility(View.VISIBLE);

        mFileName = getExternalCacheDir().getAbsolutePath();
        mFileName += "/audiorecordtest1.3gp";
        path = mFileName;

        mediaRecorder = new MediaRecorder();

        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        Log.i("Message1", path);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        Log.i("Message2", path);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        Log.i("Message3", path);
        mediaRecorder.setOutputFile(path);
        Log.i("Message4", path);
        mediaRecorder.prepare();
        Log.i("Message5", path);
        mediaRecorder.start();
        Log.i("Message6", path);
    }

    public void play (View v) throws IOException
    {
        //if(recordedAudio) {
        recordPic.setAlpha(0.5f);
        recordPic.setEnabled(false);
        playPic.setVisibility(View.GONE);
        pausePic.setVisibility(View.VISIBLE);

        mp.start();
        mp.setVolume(1, 1);
        //}
    }

    public void pause (View v) throws IOException
    {
        recordPic.setAlpha(1.0f);
        recordPic.setEnabled(true);
        playPic.setVisibility(View.VISIBLE);
        pausePic.setVisibility(View.GONE);
        mp.pause();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        recordPic.setAlpha(1.0f);
        recordPic.setEnabled(true);
        playPic.setVisibility(View.VISIBLE);
        pausePic.setVisibility(View.GONE);
    }
}
