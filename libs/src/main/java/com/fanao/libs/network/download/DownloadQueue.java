package com.fanao.libs.network.download;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eafoon on 2016/8/23.
 */
public class DownloadQueue {

    private List<String> urlList = null;

    private static volatile DownloadQueue instance;

    private DownloadQueue() {
        urlList = new ArrayList<>();
    }

    public static DownloadQueue getInstance() {
        if(instance == null) {
            synchronized (DownloadQueue.class) {
                if(instance == null) {
                    instance = new DownloadQueue();
                }
            }
        }
        return instance;
    }

    public boolean contains(String url) {
        return urlList.contains(url);
    }

    public void addUrl(String url) {
        urlList.add(url);
    }

    public void removeUrl(String url) {
        urlList.remove(url);
    }
}
