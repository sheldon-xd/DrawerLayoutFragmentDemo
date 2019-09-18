package com.emergency.tengqian.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class SharedPreferencesUtils {

    private static Context mContext;

    private static SharedPreferences sharedPreferences;

    public static void setContext(Context context) {
        mContext = context;
    }

    private static SharedPreferences getSharePreferences() {
        if (null == sharedPreferences && mContext != null) {
            sharedPreferences = mContext.getSharedPreferences("muscletraining",
                    Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    public static void putString(String key, String value) {
        SharedPreferences perference = getSharePreferences();
        if (perference == null) {
            return;
        }
        SharedPreferences.Editor editor = perference.edit();
        if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(key)) {
            editor.putString(key, value);
        }
        editor.commit();
    }

    public static String getString(String key, String defaultValue) {
        SharedPreferences perference = getSharePreferences();
        if (perference == null || TextUtils.isEmpty(key)) {
            return "-1";
        }
        return perference.getString(key, defaultValue);
    }



}
