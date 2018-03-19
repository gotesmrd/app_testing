package com.example.application.customizabletimerapptry1;

import android.animation.ObjectAnimator;
import android.app.TimePickerDialog;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;

import java.io.IOError;
import java.io.IOException;

public class Timer extends AppCompatActivity {

    TextView t8, t4, t5;
    SeekBar sb1;
    Button b2,b3, b4;
    Chronometer chr1;
    AudioManager audioManager;
    MediaRecorder mediaRecorder;
    String mFileName;
    long time_in_milliseconds = 1*60*1000;
    ProgressBar progressBar;
    ObjectAnimator animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        t8 = (TextView) findViewById(R.id.textView8);
        //t4 = (TextView) findViewById(R.id.textView4);
        t5 = (TextView) findViewById(R.id.textView5);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);
        //chr1 = (Chronometer) findViewById (R.id.chronometer3) ;
        b3.setVisibility(View.GONE);
        long hours = time_in_milliseconds / 3600000;
        long minutes = (time_in_milliseconds % 3600000)/60000;
        long seconds = ((time_in_milliseconds % 3600000)%60000)/1000;




        t8.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
        /*audioManager =
                (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        sb1 = (SeekBar) findViewById(R.id.seekBar);
        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(
        ){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                t4.setText(Integer.toString(progress)+ " "+ (int)
                        Math.round(progress/100.0*audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)));
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                        (int)  Math.round(progress/100.0*audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)),
                       // (int) (progress/100.0*audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)),
                AudioManager.FLAG_PLAY_SOUND);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //Log.i("MESSAGE1", Integer.toString(sb1.getProgress()));
        t4.setText(Integer.toString(sb1.getProgress()));
        */


        //try{
        //    Thread.sleep(5000);
        //}catch (Exception e){}
        ////////////
        //animation = ObjectAnimator.ofInt (progressBar, "progress", 50, 100); // see this max value coming back here, we animate towards that value
        //animation.setDuration (5000); //in milliseconds
        //animation.setInterpolator (new DecelerateInterpolator());
        //animation.start ();

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        animation = ObjectAnimator.ofInt (progressBar, "progress", 0, 60000); // see this max value coming back here, we animate towards that value
        animation.setDuration (time_in_milliseconds); //in milliseconds
        //animation.setInterpolator (new DecelerateInterpolator());
    }
    CountDownTimer cdt;
    boolean start=false;
    public void timer_start(View v)
    {

        Log.i("Message",Integer.toString((int)time_in_milliseconds) );
        if(!start){animation.start();start=true;}
        else
            animation.resume ();

        cdt = new CountDownTimer(time_in_milliseconds, 100) {

            public void onTick(long millisUntilFinished) {
                time_in_milliseconds = millisUntilFinished;
                long hours = millisUntilFinished / 3600000;
                long minutes = (millisUntilFinished % 3600000)/60000;
                long seconds = ((millisUntilFinished % 3600000)%60000)/1000;

                t8.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
                //t3.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                t8.setText("done!");
            }
        }.start();


    }

    public void pickTimer(View v)
    {
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(Timer.this, android.R.style.Theme_Holo_Dialog, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                t8.setText( selectedHour + ":" + selectedMinute);
            }
        },  0/*hour*/, 0/*minute*/, true);
        mTimePicker.setTitle("Select Time");
        //mTimePicker.set
        mTimePicker.show();
    }

    public void timer_stop(View v)
    {
        cdt.cancel();
        animation.pause();
    }

    public void stop (View v)
    {
        t5.setVisibility(View.INVISIBLE);
        b4.setVisibility(View.VISIBLE);
        b3.setVisibility(View.INVISIBLE);
        b2.setVisibility(View.VISIBLE);
        mediaRecorder.stop();
        mediaRecorder.release();
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

        t5.setVisibility(View.VISIBLE);
        b4.setVisibility(View.GONE);
        b2.setVisibility(View.GONE);
        b3.setVisibility(View.VISIBLE);
        mFileName = getExternalCacheDir().getAbsolutePath();
        mFileName += "/audiorecordtest.3gp";
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
        MediaPlayer mp = new MediaPlayer();
        mp.setDataSource(path);
        mp.prepare();
        mp.start();
        mp.setVolume(10, 10);
    }

}
