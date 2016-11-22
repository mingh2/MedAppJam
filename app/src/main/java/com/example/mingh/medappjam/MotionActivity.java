package com.example.mingh.medappjam;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.content.Context;

import android.os.Vibrator;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class MotionActivity extends AppCompatActivity implements SensorEventListener {
    private SessionManager session;
    private Button play;
    private VideoView videoView;
    private MediaController mediaController;

    private float lastX=0, lastY=0, lastZ=0, deltaX=0, deltaY = 0, deltaZ = 0, vibrateThreshold=0 ;
    public Vibrator v;
    private TextView accInfo;
    //currentX, currentY, currentZ, maxX, maxY, maxZ;
    private float deltaXMax = 0;
    private float deltaYMax = 0;
    private float deltaZMax = 0;

    public static int i=3;

    private SensorManager sensorManager;
    private Sensor accelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion);

        session = new SessionManager(getApplicationContext());
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            // success! we have an accelerometer

            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            vibrateThreshold = accelerometer.getMaximumRange() / 2;
        } else {
            // fai! we dont have an accelerometer!
        }

        play = (Button) findViewById(R.id.play_button);
        videoView = (VideoView) findViewById(R.id.video_view);
        mediaController = new MediaController(this);
        accInfo = (TextView) findViewById(R.id.accInfo);
        v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    //onResume() register the accelerometer for listening the events
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
//        Toast.makeText(getApplicationContext(),event.values[0]+"",Toast.LENGTH_LONG).show();

        // get the change of the x,y,z values of the accelerometer
        deltaX = Math.abs(lastX - event.values[0]);
        deltaY = Math.abs(lastY - event.values[1]);
        deltaZ = Math.abs(lastZ - event.values[2]);


//        currentX.setText(String.format("%.2f", deltaX));
//
//        currentY.setText(String.format("%.2f", deltaY));
//        currentY.setText(String.format("%.2f", deltaY));
//
//        maxX.setText(String.format("%.2f", event.values[0]));
//        maxY.setText(String.format("%.2f", event.values[1]));
//        maxZ.setText(String.format("%.2f", event.values[2]));
//
        accInfo.setText(String.format("%.2f", deltaX + deltaY + deltaZ));
        // if the change is below 2, it is just plain noise
        if (deltaX < 2)
            deltaX = 0;
        if (deltaY < 2)
            deltaY = 0;
        if ((deltaZ > vibrateThreshold) || (deltaY > vibrateThreshold) || (deltaZ > vibrateThreshold)) {
//            v.vibrate(50);
        }
        // set the last know values of x,y,z
        lastX = event.values[0];
        lastY = event.values[1];
        lastZ = event.values[2];

    }


    //Add Menu to the Tool Bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id)
        {
            case R.id.action_signout:
                session.logoutUser();

        }

        return super.onOptionsItemSelected(item);
    }

    public void videoPlay(View v)
    {
        String videoPath = "android.resource://com.example.mingh.medappjam/" + R.raw.arm;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        videoView.start();
    }
}


