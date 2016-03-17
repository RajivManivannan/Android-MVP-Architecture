package com.reeuse.androidmvp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * NetworkUtils.java
 * <p/>
 * This is util class to check the internet connection.
 */
public class NetworkUtils {
    /**
     * Check the Internet connection available status
     *
     * @param context - Context environment passed by this parameter
     * @return boolean true if the Internet Connection is Available and false otherwise
     */
    public static boolean isConnected(Context context) {
        //Connectivity manager instance
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // Fetch Active internet connection from network info
        NetworkInfo netInfo = manager.getActiveNetworkInfo();
        // return the network connection is active or not.
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}