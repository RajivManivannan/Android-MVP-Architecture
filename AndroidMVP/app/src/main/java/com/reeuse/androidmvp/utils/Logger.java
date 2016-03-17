package com.reeuse.androidmvp.utils;

import android.util.Log;

import com.reeuse.androidmvp.BuildConfig;


/**
 * Logger.java
 * To get the different types of log in the application we can use this LogUtils class.
 * By default before creating the signed APK the application will be in debug-able mode
 * Once, you created the signed APK, SDK itself change to debug-able false.Since,we have
 * checked the condition BuildConfig.DEBUG and showing log.
 */
public class Logger {
    private static final String LOG_PREFIX = "mvp_";
    private static final int LOG_PREFIX_LENGTH = LOG_PREFIX.length();
    private static final int MAX_LOG_TAG_LENGTH = 23;

    //Default constructor.
    private Logger() {
    }

    /**
     * Make log tag.
     *
     * @param str the str
     * @return the string
     */
    public static String makeLogTag(String str) {
        if (str.length() > MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH) {
            return LOG_PREFIX + str.substring(0, MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH - 1);
        }
        return LOG_PREFIX + str;
    }

    /**
     * Don't use this when obfuscating class names!
     */
    public static String makeLogTag(Class<?> cls) {
        return makeLogTag(cls.getSimpleName());
    }

    /**
     * D void.
     *
     * @param tag     the tag
     * @param message the message
     */
    public static void d(final String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, message);
        }
    }

    /**
     * D void.
     *
     * @param tag   the tag
     * @param cause the cause
     */
    public static void d(final String tag, Throwable cause) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, tag, cause);
        }
    }

    /**
     * V void.
     *
     * @param tag     the tag
     * @param message the message
     */
    public static void v(final String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.v(tag, message);
        }
    }

    /**
     * V void.
     *
     * @param tag     the tag
     * @param message the message
     * @param cause   the cause
     */
    public static void v(final String tag, String message, Throwable cause) {
        if (BuildConfig.DEBUG && Log.isLoggable(tag, Log.VERBOSE)) {
            Log.v(tag, message, cause);
        }
    }

    /**
     * I void.
     *
     * @param tag     the tag
     * @param message the message
     */
    public static void i(final String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, message);
        }
    }

    /**
     * I void.
     *
     * @param tag   the tag
     * @param cause the cause
     */
    public static void i(final String tag, Throwable cause) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, tag, cause);
        }
    }

    /**
     * W void.
     *
     * @param tag     the tag
     * @param message the message
     */
    public static void w(final String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.w(tag, message);
        }
    }

    /**
     * W void.
     *
     * @param tag   the tag
     * @param cause the cause
     */
    public static void w(final String tag, Throwable cause) {
        if (BuildConfig.DEBUG) {
            Log.w(tag, tag, cause);
        }
    }

    /**
     * E void.
     *
     * @param tag     the tag
     * @param message the message
     */
    public static void e(final String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, message);
        }
    }

    /**
     * E void.
     *
     * @param tag   the tag
     * @param cause the cause
     */
    public static void e(final String tag, Throwable cause) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, tag, cause);
        }
    }

}

