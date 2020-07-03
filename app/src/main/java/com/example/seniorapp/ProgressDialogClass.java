package com.example.seniorapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.WindowManager;

public class ProgressDialogClass {
    Context context;

    public ProgressDialog CustomCallBack(Context context, String message) {
        this.context = context;
        ProgressDialog mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage(message);
        mProgressDialog.setCanceledOnTouchOutside(false);
        return mProgressDialog;
    }
}
