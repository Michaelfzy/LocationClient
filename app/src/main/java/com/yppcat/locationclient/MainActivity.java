package com.yppcat.locationclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.yppcat.locationclient.bmob.Location;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private LocationUtils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView lng = findViewById(R.id.textlong);
        final TextView lat = findViewById(R.id.textlat);
        
        utils = LocationUtils.getInstance();
        utils.setCallback(new LocationUtils.OnLocationCallback() {
            @Override
            public void getLocation(Location location) {
                //这里获取到了位置信息
                Log.e(TAG, "getLocation: "+ location.getLongitude() );
                double lngd =  location.getLongitude();
                double latd =  location.getLongitude();
                lng.setText(Double.toString(lngd));
                lat.setText(Double.toString(latd));

            }
        });
    }
}
