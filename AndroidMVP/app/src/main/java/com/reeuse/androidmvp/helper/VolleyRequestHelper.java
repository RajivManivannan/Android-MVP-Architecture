package com.reeuse.androidmvp.helper;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.reeuse.androidmvp.app.AppConstants;
import com.reeuse.androidmvp.utils.Logger;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * VolleyRequest.java
 * An helper class to executes the web service using the volley. Supported
 * Methods: GET and POST. By default it will be executed on POST method.
 */
public class VolleyRequestHelper {

    private static final String TAG = Logger.makeLogTag(VolleyRequestHelper.class);


    private Context context;
    private RequestQueue requestQueue;
    private OnRequestCompletedListener mRequestCompletedListener;


    /**
     * Used to call web service and get response as JSON using post method.
     *
     * @param context  - context of the activity.
     * @param callback - The callback reference.
     */
    public VolleyRequestHelper(Context context, OnRequestCompletedListener callback) {
        mRequestCompletedListener = callback;
        this.context = context;
    }

    /**
     * Request String response from the Web API.
     *
     * @param requestName   the String refers the request name
     * @param webserviceUrl the String refers the web service URL.
     * @param requestParams the list of parameters in byte array.
     * @param webMethod     the integer indicates the web method.
     * @param getCache      the boolean indicates whether cache can enable/disable
     */
    public void requestString(final String requestName,
                              final String webserviceUrl,
                              final byte[] requestParams, final int webMethod,
                              final boolean getCache) {
        StringRequest stringRequest = new StringRequest(webMethod,
                webserviceUrl, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


                mRequestCompletedListener.onRequestCompleted(
                        requestName, true, response, null);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                String errorResponse = "";
                if (getCache) {
                    final Cache cache = getRequestQueue().getCache();
                    final Cache.Entry entry = cache.get(webserviceUrl);
                    if (entry != null) {
                        try {
                            errorResponse = new String(entry.data, "UTF-8");
                            mRequestCompletedListener
                                    .onRequestCompleted(requestName,
                                            true, errorResponse, null);
                            return;
                        } catch (UnsupportedEncodingException e) {
                            Logger.e(TAG, e);
                        }
                    } else {
                        Logger.e(TAG, requestName
                                + " Cache does not exist");
                    }
                }
                try {
                    VolleyError responseError = new VolleyError(
                            new String(error.networkResponse.data));
                    try {
                        final JSONObject responseJson = new JSONObject(responseError.getMessage());
                        // Show Alert Information
                        errorResponse = responseJson.getString(AppConstants.API_MESSAGE);
                    } catch (Exception e) {
                        errorResponse = "Unknown";
                    }
                } catch (Exception e) {
                    Logger.e(TAG, e);
                }
                mRequestCompletedListener.onRequestCompleted(
                        requestName, false, null,
                        errorResponse);
            }
        }) {

            @Override
            public String getBodyContentType() {
                return AppConstants.APPLICATION_JSON;
            }


            @Override
            public byte[] getBody() throws AuthFailureError {
                final byte[] body = requestParams;
                if (body != null) {
                    return body;
                }
                return super.getBody();
            }
        };
        // Adding String request to request queue
        addToRequestQueue(stringRequest, requestName);
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        req.setRetryPolicy(new DefaultRetryPolicy(90 * 1000, 0, 1.0f));
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }

    /**
     * A callback interface indicates the status about the completion of HTTP
     * request.
     */
    public interface OnRequestCompletedListener {
        /**
         * Called when the String request has been completed.
         *
         * @param requestName  the String refers the request name
         * @param status       the status of the request either success or failure
         * @param response     the String response returns from the Webservice. It may be
         *                     null if request failed.
         * @param errorMessage the String refers the error message when request failed to
         *                     get the response
         */
        void onRequestCompleted(String requestName, boolean status,
                                String response, String errorMessage);

    }
}