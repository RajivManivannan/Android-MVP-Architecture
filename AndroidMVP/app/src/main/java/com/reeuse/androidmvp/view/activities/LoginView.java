package com.reeuse.androidmvp.view.activities;
/**
*Created by Rajiv M.
*/
public interface LoginView {

    void showProgress();

    void hideProgress();

    void setEmailError(String error);

    void setPasswordError(String error);

    void showMessage(String message);

    void navigateToHome();
}
