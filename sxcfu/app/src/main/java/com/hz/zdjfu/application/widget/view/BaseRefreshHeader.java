package com.hz.zdjfu.application.widget.view;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/7 0007
 */
public interface BaseRefreshHeader {
    int STATE_NORMAL = 0;
    int STATE_RELEASE_TO_REFRESH = 1;
    int STATE_REFRESHING = 2;
    int STATE_DONE = 3;

    void onMove(float delta);

    boolean releaseAction();

    void refreshComplete();
}
