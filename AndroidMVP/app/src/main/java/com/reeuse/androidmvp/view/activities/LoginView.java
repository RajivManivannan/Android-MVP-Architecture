package com.reeuse.androidmvp.view.activities;

/**
 * Created by Rajiv M.
 */
public interface LoginView {

    String getPassword();

    void showProgress();

    void hideProgress();

    void setUsernameError(String error);

    void setPasswordError(String error);

    String getUsername();

    void showMessage(String message);

    void navigateToHome();
}
