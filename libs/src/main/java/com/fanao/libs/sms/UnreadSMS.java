package com.fanao.libs.sms;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取未读的短信
 * 
 * @author liutao
 *
 */
public class UnreadSMS {
	
	public List<String> getUnreadSms(Context context) {
		ArrayList<String> list = new ArrayList<String>();
		
		 String SMS_URI_ALL = "content://sms/";
		 
         Uri uri = Uri.parse(SMS_URI_ALL);
         
         String[] projection = new String[] { "_id", "read", "body", "date", "type" };

         Cursor cur = context.getContentResolver().query(uri, projection, "read=? and type=?",
                         new String[] { "0", "1" }, "date desc");
         try {
             if (cur != null && cur.moveToFirst()) {
                 int body = cur.getColumnIndex("body");
                 
                 String sms = null;
                 do {
                     sms = cur.getString(body);

                     list.add(sms);

                 } while (cur.moveToNext());

             }
         } catch (Exception e) {
        	 
         } finally {
             cur.close();
             cur = null;
         }
		
		return list;
	}
	
}
