package com.reeuse.androidmvp.presenter;

/**
 * Created by Rajiv M.
 */
public interface LoginPresenter {
    void validateCredentials(String username, String password);

    void onDestroy();
}
