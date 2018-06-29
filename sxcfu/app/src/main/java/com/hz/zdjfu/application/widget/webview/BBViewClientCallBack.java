package com.hz.zdjfu.application.widget.webview;

/**
 * [类功能说明]
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */
public interface BBViewClientCallBack {
    void onProgressChanged(int progress);

    void onProgresscomplete();

    void onReceivedTitle(String title);
}
