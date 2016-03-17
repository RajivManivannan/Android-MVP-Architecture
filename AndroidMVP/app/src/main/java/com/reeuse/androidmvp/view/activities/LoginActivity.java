package com.reeuse.androidmvp.view.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.reeuse.androidmvp.R;
import com.reeuse.androidmvp.presenter.LoginPresenter;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements LoginView {


    private TextInputLayout usernameTil;
    private TextInputLayout passwordTil;
    private EditText usernameEt;
    private EditText passwordEt;
    private View progressView;
    private View loginFormView;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (appPreference.isLoggedIn()) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }

        usernameEt = (EditText) findViewById(R.id.login_username_et);
        passwordEt = (EditText) findViewById(R.id.login_password_et);
        usernameTil = (TextInputLayout) findViewById(R.id.login_username_til);
        passwordTil = (TextInputLayout) findViewById(R.id.login_password_til);

        loginFormView = findViewById(R.id.login_form);
        progressView = findViewById(R.id.login_progress);

        loginPresenter = new LoginPresenter(this, this);
    }

    public void onSubmitClicked(View v) {
        loginPresenter.onSubmitClicked();
    }

    @Override
    protected void onDestroy() {
        loginPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public String getPassword() {
        return passwordEt.getText().toString().trim();
    }

    @Override
    public void showProgress() {
        showProgress(true);
    }

    @Override
    public void hideProgress() {
        showProgress(false);
    }

    @Override
    public void setUsernameError(String error) {
        usernameTil.setError(error);
    }

    @Override
    public void setPasswordError(String error) {
        passwordTil.setError(error);
    }

    @Override
    public String getEmail() {
        return usernameEt.getText().toString().trim();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToHome() {
        appPreference.setLoggedIn(true);
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }


    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            loginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            progressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


}
