package com.fanao.libs.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.telephony.SmsMessage;
import android.text.TextUtils;

/**
 * 通过广播监听器，自动获取验证码
 * 
 * @author liutao
 *
 */
public class MonitorSMS extends BroadcastReceiver{
	public static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
	public static final String startStr = "您校验码是：";  // 过滤字符串，不同的模板格式，要修改这个值
	
	private Handler handler = null;
	
	public MonitorSMS(Handler handler) {
		this.handler = handler;
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		if(intent.getAction().equals(SMS_RECEIVED)) {
			SmsMessage[] smsMana = getMessagesFromIntent(intent);
			
			 for (SmsMessage message : smsMana) {
				System.out.println(message.getOriginatingAddress() +" : "+
								   message.getDisplayOriginatingAddress() +" : "+
								   message.getDisplayMessageBody() +" : "+
								   message.getTimestampMillis());
				
				String smsContent = message.getDisplayMessageBody();
				
				if(!TextUtils.isEmpty(smsContent) && smsContent.startsWith(startStr)) {
					int end = smsContent.indexOf("，");
					smsContent = smsContent.substring(startStr.length(), end);
					
					handler.sendMessage(handler.obtainMessage(0, smsContent));
				}
			 }
		}
	}

	private SmsMessage[] getMessagesFromIntent(Intent intent) {
        Object[] messages = (Object[]) intent.getSerializableExtra("pdus");

        byte[][] pduObjs = new byte[messages.length][];

        for (int i = 0; i < messages.length; i++) {
            pduObjs[i] = (byte[]) messages[i];
        }

        byte[][] pdus = new byte[pduObjs.length][];

        int pduCount = pdus.length;

        SmsMessage[] msgs = new SmsMessage[pduCount];

        for (int i = 0; i < pduCount; i++){
            pdus[i] = pduObjs[i];
            msgs[i] = SmsMessage.createFromPdu(pdus[i]);
        }
        return msgs;
    }
	
}