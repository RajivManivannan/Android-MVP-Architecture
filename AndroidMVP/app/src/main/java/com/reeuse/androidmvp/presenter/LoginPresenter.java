package com.reeuse.androidmvp.presenter;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import com.reeuse.androidmvp.R;
import com.reeuse.androidmvp.view.activities.LoginView;

/**
 * Created by Rajiv M.
 */
public class LoginPresenter {

    private Context context;
    private LoginView loginView;

    public LoginPresenter(Context context, LoginView loginView) {
        this.context = context;
        this.loginView = loginView;
    }


    public void onSubmitClicked() {
        if (TextUtils.isEmpty(loginView.getEmail())) {
            loginView.setUsernameError(context.getString(R.string.error_field_required));
            return;
        }
        if (TextUtils.isEmpty(loginView.getPassword())) {
            loginView.setPasswordError(context.getString(R.string.error_field_required));
            return;
        }
        loginView.showProgress();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loginView.hideProgress();
                loginView.navigateToHome();
            }
        }, 3000); // delay 3 seconds

    }

    public void onDestroy() {
        loginView = null;
    }
}




