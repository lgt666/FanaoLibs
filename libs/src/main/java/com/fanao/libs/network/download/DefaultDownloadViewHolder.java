package com.fanao.libs.network.download;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.x;

import java.io.File;

/**
 * Created by wyouflf on 15/11/11.
 */
public class DefaultDownloadViewHolder extends DownloadViewHolder {

    public DefaultDownloadViewHolder(View view, DownloadInfo downloadInfo) {
        super(view, downloadInfo);
    }

    @Override
    public void onWaiting() {

    }

    @Override
    public void onStarted() {
        Toast.makeText(x.app(), "开始下载", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLoading(long total, long current) {

    }

    @Override
    public void onSuccess(File result) {
        Toast.makeText(x.app(), "下载完成, "+ result.toString(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.parse("file://" + result.toString());
        intent.setData(uri);
        x.app().sendBroadcast(intent);
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        Toast.makeText(x.app(), "下载失败", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCancelled(Callback.CancelledException cex) {
    }
}
