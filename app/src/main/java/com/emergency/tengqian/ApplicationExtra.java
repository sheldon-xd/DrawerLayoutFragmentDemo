package com.emergency.tengqian;

import android.app.Activity;
import android.app.Application;

import java.util.Stack;

public class ApplicationExtra extends Application {
    private static Stack<Activity> activityStack;

    private static ApplicationExtra singleTon;

    public void onCreate() {
        super.onCreate();
        singleTon = this;
    }

    public static ApplicationExtra getInstance() {
        return singleTon;
    }

    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack();
        }
        activityStack.add(activity);
    }

    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
//            activity = null;
        }
    }

//    public void finishActivity(Class<?> cls) {
//        Activity activity = (Activity)activityStack.iterator().next()) {
//        Activity activity = null;
//        for (int i = 0; i < activityStack.size(); i++) {
//            activity = activityStack.get(i);
//            if (activity.getClass().equals(cls)) {
//                finishActivity(activity);
//            }
//        }
//
//    }

    public void finishAllActivity() {
        Activity taskActivity = null;
        for (int i = 0; i<  activityStack.size();  i++) {
            if (activityStack.get(i) != null) {
//                (Activity)activityStack.get(i).finish();
                taskActivity = activityStack.get(i);
                taskActivity.finish();
            }
        }
        activityStack.clear();
    }

    public void AppExit() {
        try {
            finishAllActivity();
            return;
        } catch (Exception localException1) {
        }
    }
}
