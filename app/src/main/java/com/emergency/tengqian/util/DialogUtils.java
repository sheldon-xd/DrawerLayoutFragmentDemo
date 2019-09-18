package com.emergency.tengqian.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.WindowManager;

import com.emergency.tengqian.ApplicationExtra;

public class DialogUtils {

    Context context;

    public DialogUtils(Context context) {
        this.context = context;

    }

    DialogUtils instance;

    public DialogUtils getInstance(Context context) {
        if (instance == null) {
            instance = new DialogUtils(context);
        }
        return instance;
    }

    ProgressDialog dialog;

    public void showDialogProgress(String msg) {
        dialog = new ProgressDialog(context);
        dialog.setCancelable(true);
        dialog.setMessage(msg);
        dialog.show();
    }

    public void dismissDialogProgress() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public void showDialogMessage(String tittle, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(tittle);
        builder.setMessage(msg);
        builder.show();
    }

    public void showExitDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("温馨提示");
        builder.setMessage("是否退出应用？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                ApplicationExtra.getInstance().AppExit();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    /**
     * 依附于Activity的系统级Dialog
     */
    public void showWindowMessage(String tittle, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(tittle);
        builder.setMessage(msg);
//        builder.show();
        AlertDialog dialog = builder.create();
//        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_TOAST);
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.show();
    }

}
