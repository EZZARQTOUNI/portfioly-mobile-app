package com.portfioly.utils;


import android.app.Activity;
import android.widget.Button;

import com.codezal.sweetalert.SweetAlertDialog;
import com.portfioly.R;


public class Toast {
    static public void success(Activity activity,String title, String message){
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(activity, SweetAlertDialog.SUCCESS_TYPE);
        sweetAlertDialog.setTitleText(title)
                .setContentText(message)
                .setConfirmText("Done")
                .show();
        setButton((Button)sweetAlertDialog.findViewById(com.codezal.sweetalert.R.id.confirm_button));
    }
    static public void info(Activity activity,String title, String message){
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(activity, SweetAlertDialog.NORMAL_TYPE);
        sweetAlertDialog.setTitleText(title)
                .setContentText(message)
                .setConfirmText("Done")
                .show();
        setButton((Button)sweetAlertDialog.findViewById(com.codezal.sweetalert.R.id.confirm_button));
    }
    static public void error(Activity activity,String title, String message){
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE);
        sweetAlertDialog.setTitleText(title)
                .setContentText(message)
                .setConfirmText("Done")
                .show();
        setButton((Button)sweetAlertDialog.findViewById(com.codezal.sweetalert.R.id.confirm_button));
    }
    static public void setButton(Button btn){
        btn.setPadding(2,5,2,5);
    }

}
