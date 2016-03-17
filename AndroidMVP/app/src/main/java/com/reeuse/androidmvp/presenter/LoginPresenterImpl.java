package com.reeuse.androidmvp.presenter;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import com.reeuse.androidmvp.R;
import com.reeuse.androidmvp.view.activities.LoginView;

/**
 * Created by Rajiv M.
 */
public class LoginPresenterImpl implements LoginPresenter {

    private Context context;
    private LoginView loginView;


    public LoginPresenterImpl(Context context, LoginView loginView) {
        this.context = context;
        this.loginView = loginView;
    }

    @Override
    public void validateCredentials(String email, String password) {
        if (TextUtils.isEmpty(email))
            loginView.setEmailError(context.getString(R.string.error_field_required));
        else if (TextUtils.isEmpty(password))
            loginView.setPasswordError(context.getString(R.string.error_field_required));
        else {
            loginView.showProgress();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    loginView.hideProgress();
                    loginView.navigateToHome();
                }
            }, 3000); // delay 3 seconds
        }
    }

    @Override
    public void onDestroy() {
        loginView = null;
    }


}




