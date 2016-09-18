package com.ryanbayona.rvb.unplug;

import android.app.ProgressDialog;
import android.content.Context;
import android.telephony.SmsManager;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class WebAppInterface {
    Context mContext;

    /** Instantiate the interface and set the context */
    WebAppInterface(Context c) {
        mContext = c;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    /** Sends SMS to Arduino */
    public void sendSMS(String message){

        //Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
        ProgressDialog progress = new ProgressDialog(mContext);
        progress.setTitle("Sending SMS...");
        progress.setMessage("Turn "+ message.toUpperCase());
        progress.show();

        //send sms
        String phoneNo = "+639953741904";

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);

            Toast.makeText(mContext, "SMS sent.", Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            Toast.makeText(mContext, "SMS faild, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        progress.dismiss();
    }
}