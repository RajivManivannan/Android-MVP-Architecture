package com.reeuse.androidmvp.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Rajiv M.
 */
public class AppPreference {
    public static final String PREF_IS_LOGGED_IN = "is_logged_in";

    private SharedPreferences sharedPreferences;

    public AppPreference(Context mContext) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    public boolean isLoggedIn() {
        //To retrieve the status of login status.
        return sharedPreferences.getBoolean(PREF_IS_LOGGED_IN, false);
    }

    public void setLoggedIn(boolean status) {
        //To store the status of login status.
        sharedPreferences.edit().putBoolean(PREF_IS_LOGGED_IN, status).apply();
    }
}
