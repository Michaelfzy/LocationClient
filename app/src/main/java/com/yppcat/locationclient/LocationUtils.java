package com.yppcat.locationclient;

import android.os.Looper;
import android.util.Log;

import com.yppcat.locationclient.bmob.Location;

import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

public class LocationUtils {

    private static final String TAG = "LocationUtils";
    private static LocationUtils instance;

    private OnLocationCallback callback;
    public static final String LOCATION_ID = "7e30572440";
    BmobQuery<Location>  bmobQuery = new BmobQuery<>();

    public static synchronized LocationUtils getInstance() {
        if (instance == null) {
            instance = new LocationUtils();
        }
        return instance;
    }

    class LocationTask extends TimerTask {
        @Override
        public void run() {
            getLocation();
        }
    }

    private LocationUtils() {
        new Timer().schedule(new LocationTask(),1000,3000);
    }


    private void getLocation() {
        bmobQuery.getObject(LOCATION_ID, new QueryListener<Location>() {
            @Override
            public void done(Location location, BmobException e) {
                if (e == null){
                    if (callback != null){
                        callback.getLocation(location);
                    }
                }else {
                    Log.d(TAG, "done: failed");
                }
            }
        });
    }

    public void setCallback(OnLocationCallback callback) {
        this.callback = callback;
    }


    public interface OnLocationCallback {
        void getLocation(Location location);
    }
}
