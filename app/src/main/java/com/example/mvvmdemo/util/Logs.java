package com.example.mvvmdemo.util;


import android.util.Log;

public class Logs {


    public static void s(Throwable throwable) {
        // Skip
    }


    public static void e(Throwable throwable) {
    }

    public static void d(String mess) {
        Log.d("MVVM", mess);
    }

    public static void e(String mess, Throwable throwable) {
        Log.e("MVVM", mess, throwable);
    }

}
