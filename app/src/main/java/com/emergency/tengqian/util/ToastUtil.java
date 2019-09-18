package com.emergency.tengqian.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

//    public static void showInternetErr(Context context) {
//        Toast.makeText(context, R.string.internet_error, Toast.LENGTH_SHORT).show();
//    }

    public static void showMessage(Context context, int id) {
        Toast.makeText(context, id, Toast.LENGTH_SHORT).show();
    }

    public static void showMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
