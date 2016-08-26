package com.fanao.libs.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * 网络连接, 请求
 *
 * @author liutao
 */
public class HttpConnect {

    private Context context = null;
    private Callback.Cancelable mCancelable = null;

    public HttpConnect(Context context) {
        this.context = context;
    }

    /**
     * 网络连接状态
     *
     * @return true已连接, false未连接
     */
    public boolean isConnected() {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connManager.getActiveNetworkInfo();
        try {
            if (netInfo == null || !netInfo.isConnected()) {
                Toast.makeText(context, "网络未连接", Toast.LENGTH_LONG).show();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            netInfo = null;
            connManager = null;
        }
        return true;
    }

    /**
     * 关闭请求
     */
    public void cancel() {
        if(mCancelable != null)
            mCancelable.cancel();
    }

    public <ResponseType> void post(RequestParams params, Callback.CommonCallback<ResponseType> callback) {
        if(!isConnected()) {
            callback.onFinished();
            return;
        }

        mCancelable = x.http().post(params, callback);
    }

    public <ResponseType> void get(RequestParams params, Callback.CommonCallback<ResponseType> callback) {
        if(!isConnected()) {
            callback.onFinished();
            return;
        }

        mCancelable = x.http().get(params, callback);
    }

}