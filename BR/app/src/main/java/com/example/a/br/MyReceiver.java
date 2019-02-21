package com.example.a.br;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Object[] pdus = (Object[]) intent.getExtras().get("pdus");
        SmsMessage[] msgs = new SmsMessage[pdus.length];
        for(int i=0; i<pdus.length; i++){
            msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
            String str = "SMS from : "+msgs[i].getOriginatingAddress() +
                    " : "+msgs[i].getDisplayMessageBody();
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
            Log.d("msg", str);
        }
    }
}
