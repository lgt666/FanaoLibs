package com.fanao.libs.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 *  网络状态
 *
 * Created by liutao on 16/3/17.
 */
public class NewrokUtils {


    /**
     * 网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connManager.getActiveNetworkInfo();

        return netInfo == null || !netInfo.isConnected() ? false : true;
    }

    /**
     * 获取网络类型
     *
     * @param context
     * @return -1:网络, 0:未知, 1:wifi, 2:手机
     */
    public static int getConnectedType(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connManager.getActiveNetworkInfo();

        if(netInfo == null)
            return -1;

        String type = netInfo.getTypeName();

        if(type == null) {
            return -1;
        }else if("WIFI".equals(type)) {
            return 1;
        }else if("MOBILE".equals(type)) {
            return 2;
        }

        return 0;
    }
}