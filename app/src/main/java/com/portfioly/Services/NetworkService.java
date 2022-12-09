package com.portfioly.Services;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.portfioly.Activity.NetworkErrorActivity;

public class NetworkService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if(!(netInfo != null && netInfo.isConnected())){
                context.startActivity(new Intent(context, NetworkErrorActivity.class));
            }


    }
}
