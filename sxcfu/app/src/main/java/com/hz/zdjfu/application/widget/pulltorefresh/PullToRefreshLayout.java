package com.hz.zdjfu.application.widget.pulltorefresh;

import android.content.Context;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.widget.pulltorefresh.pullableview.Pullable;

import java.util.Timer;
import java.util.TimerTask;


/**
 * 自定义的布局，用来管理三个子控件，其中一个是下拉头，
 * 一个是包含内容的pullableView（
 * 可以是实现Pullable接口的的任何View），
 *
 * @author xiangwq
 */
public class PullToRefreshLayout extends RelativeLayout {
    public static final String TAG = "PullToRefreshLayout";
    // 初始状态
    public static final int INIT = 0;
    // 释放刷新
    public static final int RELEASE_TO_REFRESH = 1;
    // 正在刷新
    public static final int REFRESHING = 2;
    // 释放加载
    public static final int RELEASE_TO_LOAD = 3;
    // 正在加载
    public static final int LOADING = 4;
    // 操作完毕
    public static final int DONE = 5;
    // 当前状态
    private int state = INIT;
    // 刷新回调接口
    private OnRefreshListener mListener;

    //首页改版上拉显示公司相关信息
    public static final int INDEX_MORE = 11;

    // 刷新成功
    public static final int SUCCEED = 0;
    // 刷新失败
    public static final int FAIL = 1;
    // 没有更多数据了
    public static final int ALL = 2;
    // 除财富列表外的 没有更多数据了
    public static final int ALL_NODATA = 3;

    // 按下Y坐标，上一个事件点Y坐标
    private float downY, lastY;

    // 下拉的距离。注意：pullDownY和pullUpY不可能同时不为0
    public float pullDownY = 0;
    // 上拉的距离
    private float pullUpY = 0;

    // 释放刷新的距离
    private float refreshDist = 200;
    // 释放加载的距离
    private float loadmoreDist = 200;

    private MyTimer timer;
    // 回滚速度
    public float MOVE_SPEED = 8;
    // 第一次执行布局
    private boolean isLayout = false;
    // 在刷新过程中滑动操作
    private boolean isTouch = false;
    // 手指滑动距离与下拉头的滑动距离比，中间会随正切函数变化
    private float radio = 2;

    // 下拉箭头的转180°动画
    private RotateAnimation rotateAnimation;
    // 均匀旋转动画
    private RotateAnimation refreshingAnimation;

    // 下拉头
    private View refreshView;
    // 下拉的箭头
    private View pullView;
    // 正在刷新的图标
    private View refreshingView;
    // 刷新结果图标
    private View refreshStateImageView;
    // 刷新结果：成功或失败
    private TextView refreshStateTextView;

    // 上拉头
    private View loadmoreView;
    // 上拉的箭头
    private View pullUpView;
    // 正在加载的图标
    private View loadingView;
    // 加载结果图标
    private View loadStateImageView;
    // 加载结果：成功或失败
    private TextView loadStateTextView;
    //首页改版后上拉最后显示的样式
    private LinearLayout bottomLayout;

    // 实现了Pullable接口的View
    private View pullableView;
    // 过滤多点触碰
    private int mEvents;
    // 这两个变量用来控制pull的方向，如果不加控制，当情况满足可上拉又可下拉时没法下拉
    private boolean canPullDown = true;
    private boolean canPullUp = true;

    //private Context mContext;

    private boolean isListOrHomeRefresh = true, isHomeList = true;
    // 上拉，下拉的 底部 头部 回弹间隔时间
    private long onLoadMoreTime = 800;
    private long onRefreshTime = 800;

    /**
     * 执行自动回滚的handler
     */
    Handler updateHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // 回弹速度随下拉距离moveDeltaY增大而增大
            MOVE_SPEED = (float) (8 + 5 * Math.tan(Math.PI / 2
                    / getMeasuredHeight() * (pullDownY + Math.abs(pullUpY))));
            if (!isTouch) {
                // 正在刷新，且没有往上推的话则悬停，显示"正在刷新..."
                if (state == REFRESHING && pullDownY <= refreshDist) {
                    pullDownY = refreshDist;
                    timer.cancel();
                } else if (state == LOADING && -pullUpY <= loadmoreDist) {
                    pullUpY = -loadmoreDist;
                    timer.cancel();
                }
            }
            if (pullDownY > 0)
                pullDownY -= MOVE_SPEED;
            else if (pullUpY < 0)
                pullUpY += MOVE_SPEED;
            if (pullDownY < 0) {
                // 已完成回弹
                pullDownY = 0;
                pullView.clearAnimation();
                // 隐藏下拉头时有可能还在刷新，只有当前状态不是正在刷新时才改变状态
                if (state != REFRESHING && state != LOADING)
                    changeState(INIT);
                timer.cancel();
                requestLayout();
            }
            if (pullUpY > 0) {
                // 已完成回弹
                pullUpY = 0;
                pullUpView.clearAnimation();
                // 隐藏上拉头时有可能还在刷新，只有当前状态不是正在刷新时才改变状态
                if (state != REFRESHING && state != LOADING)
                    changeState(INIT);
                timer.cancel();
                requestLayout();
            }
            // Log.d("handle", "handle");
            // 刷新布局,会自动调用onLayout
            requestLayout();
            // 没有拖拉或者回弹完成
            if (pullDownY + Math.abs(pullUpY) == 0)
                timer.cancel();
        }
    };

    public void setOnRefreshListener(OnRefreshListener listener) {
        mListener = listener;
    }

    public PullToRefreshLayout(Context context) {
        super(context);
        initView(context);
    }

    public PullToRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PullToRefreshLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context) {
        // mContext = context;
        timer = new MyTimer(updateHandler);
        rotateAnimation = (RotateAnimation) AnimationUtils.loadAnimation(
                context, R.anim.reverse_anim);
        refreshingAnimation = (RotateAnimation) AnimationUtils.loadAnimation(
                context, R.anim.rotating);
        // 添加匀速转动动画
        LinearInterpolator lir = new LinearInterpolator();
        rotateAnimation.setInterpolator(lir);
        refreshingAnimation.setInterpolator(lir);
    }

    private void hide() {
        timer.schedule(5);
    }

    /**
     * 完成刷新操作，显示刷新结果。注意：刷新完成后一定要调用这个方法
     */
    /**
     * @param refreshResult PullToRefreshLayout.SUCCEED代表成功，PullToRefreshLayout.FAIL代表失败
     */
    public void refreshFinish(int refreshResult) {

        // 底部的加载完显示
        layoutButtomRefresh.setVisibility(View.VISIBLE);
        layoutNoMore.setVisibility(View.GONE);

	/*	loadingView.setVisibility(View.VISIBLE);
        loadStateImageView.setVisibility(View.VISIBLE);*/

        refreshingView.clearAnimation();
        refreshingView.setVisibility(View.GONE);

        switch (refreshResult) {
            case SUCCEED:
                // 刷新成功
                refreshStateImageView.setVisibility(View.VISIBLE);
                refreshStateTextView.setText(R.string.refresh_succeed);
                refreshStateImageView
                        .setBackgroundResource(R.mipmap.refresh_succeed);
                break;
            case FAIL:
            default:
                // 刷新失败
                refreshStateImageView.setVisibility(View.VISIBLE);
                refreshStateTextView.setText(R.string.refresh_fail);
                refreshStateImageView
                        .setBackgroundResource(R.mipmap.refresh_failed);
                break;
        }
        if (pullDownY > 0) {
            // 刷新结果停留1秒
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    changeState(DONE);
                    hide();
                }
            }.sendEmptyMessageDelayed(0, onRefreshTime);
        } else {
            changeState(DONE);
            hide();
        }
    }

    /**
     * 加载完毕，显示加载结果。注意：加载完成后一定要调用这个方法
     *
     * @param refreshResult PullToRefreshLayout.SUCCEED代表成功，PullToRefreshLayout.FAIL代表失败
     */
    public void loadmoreFinish(int refreshResult) {
        loadingView.clearAnimation();
        loadingView.setVisibility(View.GONE);

        switch (refreshResult) {
            case INDEX_MORE:
                loadmoreIndex.setVisibility(View.VISIBLE);
                hideButtom();
                break;
            case SUCCEED:
                // 加载成功
                loadStateImageView.setVisibility(View.VISIBLE);
                loadStateTextView.setText(R.string.load_succeed);
                loadStateImageView.setBackgroundResource(R.mipmap.load_succeed);
                hideButtom();
                break;
            case ALL: // 全部加载完
                layoutButtomRefresh.setVisibility(View.GONE);
                layoutNoMore.setVisibility(View.VISIBLE);
                hideButtom();
                break;
            case ALL_NODATA: // 没有更多显示
                layoutButtomRefresh.setVisibility(View.GONE);
                layoutNoMore.setVisibility(View.GONE);
                hideButtom();
                break;
            default:
                // 加载失败
                loadStateImageView.setVisibility(View.VISIBLE);
                loadStateTextView.setText(R.string.load_fail);
                loadStateImageView.setBackgroundResource(R.mipmap.load_failed);
                hideButtom();
                break;
        }
    }

    /**
     * 是否隐藏 加载的框
     */
    private void hideButtom() {
        if (pullUpY < 0) {
            // 刷新结果停留1秒
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    changeState(DONE);
                    hide();
                }
            }.sendEmptyMessageDelayed(0, onLoadMoreTime);
        } else {
            changeState(DONE);
            hide();
        }
    }

    private void changeState(int to) {
        state = to;
        switch (state) {
            case INIT:
                // 下拉布局初始状态
                refreshStateImageView.setVisibility(View.GONE);
                refreshStateTextView.setText(R.string.pull_to_refresh);
                pullView.clearAnimation();

                setHideView();

                // 上拉布局初始状态
                loadStateImageView.setVisibility(View.GONE);
                loadStateTextView.setText(R.string.pullup_to_load);
                pullUpView.clearAnimation();
                // pullUpView.setVisibility(View.VISIBLE);
                break;
            case RELEASE_TO_REFRESH:
                // 释放刷新状态
                refreshStateTextView.setText(R.string.release_to_refresh);
                pullView.startAnimation(rotateAnimation);
                break;
            case REFRESHING:
                // 正在刷新状态
                pullView.clearAnimation();
                refreshingView.setVisibility(View.VISIBLE);
                refreshingView.startAnimation(refreshingAnimation);
                refreshStateTextView.setText(R.string.refreshing);

                setHideView();

                break;
            case RELEASE_TO_LOAD:
                // 释放加载状态

                if (isListOrHomeRefresh) { // 底部向上滑动时，自动加载更多
                    // 加载操作
                    if (mListener != null)
                        mListener.onLoadMore(this);
                }

                loadStateTextView.setText(R.string.release_to_load);
                pullUpView.startAnimation(rotateAnimation);
                break;
            case LOADING:
                // 正在加载状态
                pullUpView.clearAnimation();
                loadingView.setVisibility(View.VISIBLE);
                loadStateImageView.setVisibility(View.GONE);

                setHideView();

                // pullUpView.setVisibility(View.INVISIBLE);
                loadingView.startAnimation(refreshingAnimation);
                loadStateTextView.setText(R.string.loading);
                break;
            case DONE:
                // 刷新或加载完毕，啥都不做
                break;
        }
    }


    private void setHideView() {

        if (getIsListOrHomeRefresh()) {

            pullView.setVisibility(View.GONE);

            pullUpView.setVisibility(View.GONE);
            loadStateTextView.setVisibility(View.GONE);
            loadingView.setVisibility(View.GONE);

        } else {

            pullView.setVisibility(View.GONE);
            pullUpView.setVisibility(View.GONE);

            loadStateTextView.setVisibility(View.VISIBLE);
            loadingView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 不限制上拉或下拉
     */
    private void releasePull() {
        canPullDown = true;
        canPullUp = true;
    }

    private float X, Y;

    /*
     * （非 Javadoc）由父控件决定是否分发事件，防止事件冲突
     *
     * @see android.view.ViewGroup#dispatchTouchEvent(android.view.MotionEvent)
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        try {
            // 头部说明文字是否隐藏显示
            if (isHomeList) {
                layoutComplayRefreshInfo.setVisibility(View.VISIBLE);
            } else {
                layoutComplayRefreshInfo.setVisibility(View.GONE);
            }
        } catch (Exception e) {
        }

        try {
            switch (ev.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    downY = ev.getY();
                    lastY = downY;
                    timer.cancel();
                    mEvents = 0;
                    releasePull();
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                case MotionEvent.ACTION_POINTER_UP:
                    // 过滤多点触碰
                    mEvents = -1;
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (mEvents == 0) {
                        if (pullDownY > 0 //&& Math.abs(moveX) < Math.abs(moveY)
                                || (((Pullable) pullableView).canPullDown()
                                && canPullDown && state != LOADING)) {
                            // 可以下拉，正在加载时不能下拉
                            // 对实际滑动距离做缩小，造成用力拉的感觉
                            pullDownY = pullDownY + (ev.getY() - lastY) / radio;
                            if (pullDownY < 0) {
                                pullDownY = 0;
                                canPullDown = false;
                                canPullUp = true;
                            }
                            if (pullDownY > getMeasuredHeight())
                                pullDownY = getMeasuredHeight();
                            if (state == REFRESHING) {
                                // 正在刷新的时候触摸移动
                                isTouch = true;
                            }
                        } else if (pullUpY < 0
                                || (((Pullable) pullableView).canPullUp()
                                && canPullUp && state != REFRESHING)) {
                            // 可以上拉，正在刷新时不能上拉
                            pullUpY = pullUpY + (ev.getY() - lastY) / radio;
                            if (pullUpY > 0) {
                                pullUpY = 0;
                                canPullDown = true;
                                canPullUp = false;
                            }
                            if (pullUpY < -getMeasuredHeight())
                                pullUpY = -getMeasuredHeight();
                            if (state == LOADING) {
                                // 正在加载的时候触摸移动
                                isTouch = true;
                            }
                        } else
                            releasePull();
                    } else
                        mEvents = 0;
                    lastY = ev.getY();
                    // 根据下拉距离改变比例
                    radio = (float) (2 + 2 * Math.tan(Math.PI / 2 / getMeasuredHeight()
                            * (pullDownY + Math.abs(pullUpY))));
                    if (pullDownY > 0 || pullUpY < 0)
                        requestLayout();
                    if (pullDownY > 0) {

                        if (pullDownY <= refreshDist
                                && (state == RELEASE_TO_REFRESH || state == DONE)) {
                            // 如果下拉距离没达到刷新的距离且当前状态是释放刷新，改变状态为下拉刷新
                            changeState(INIT);
                        }
                        if (pullDownY >= refreshDist && state == INIT) {
                            // 如果下拉距离达到刷新的距离且当前状态是初始状态刷新，改变状态为释放刷新
                            changeState(RELEASE_TO_REFRESH);
                        }
                    } else if (pullUpY < 0) {

                        // Log.e("注意pullUpY","1199999999999");
                        // 下面是判断上拉加载的，同上，注意pullUpY是负值
                        if (-pullUpY <= loadmoreDist
                                && (state == RELEASE_TO_LOAD || state == DONE)) {
                            changeState(INIT);
                        }
                        // 上拉操作
                        if (-pullUpY >= loadmoreDist && state == INIT) {
                            changeState(RELEASE_TO_LOAD);
                        }

                    }
                    // 因为刷新和加载操作不能同时进行，所以pullDownY和pullUpY不会同时不为0，因此这里用(pullDownY +
                    // Math.abs(pullUpY))就可以不对当前状态作区分了
                    if ((pullDownY + Math.abs(pullUpY)) > 8) {
                        // 防止下拉过程中误触发长按事件和点击事件
                        ev.setAction(MotionEvent.ACTION_CANCEL);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (pullDownY > refreshDist || -pullUpY > loadmoreDist)
                    // 正在刷新时往下拉（正在加载时往上拉），释放后下拉头（上拉头）不隐藏
                    {
                        isTouch = false;
                    }
                    if (state == RELEASE_TO_REFRESH) {
                        changeState(REFRESHING);
                        // 刷新操作
                        if (mListener != null)
                            mListener.onRefresh(this);
                    } else if (state == RELEASE_TO_LOAD) {
                        changeState(LOADING);
                        // 加载操作
                        if (mListener != null)
                            mListener.onLoadMore(this);
                    }
                    hide();
                default:
                    break;
            }
        } catch (Exception e) {
        }

        // 事件分发交给父类
        super.dispatchTouchEvent(ev);

        return true;
    }

    /**
     * 自动模拟手指滑动的task
     */
    private class AutoRefreshAndLoadTask extends
            AsyncTask<Integer, Float, String> {

        @Override
        protected String doInBackground(Integer... params) {
            while (pullDownY < 4 / 3 * refreshDist) {
                pullDownY += MOVE_SPEED;
                publishProgress(pullDownY);
                try {
                    Thread.sleep(params[0]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            changeState(REFRESHING);
            // 刷新操作
            if (mListener != null)
                mListener.onRefresh(PullToRefreshLayout.this);
            hide();
        }

        @Override
        protected void onProgressUpdate(Float... values) {
            if (pullDownY > refreshDist)
                changeState(RELEASE_TO_REFRESH);
            requestLayout();
        }

    }

    /**
     * 自动刷新
     */
    public void autoRefresh() {
        AutoRefreshAndLoadTask task = new AutoRefreshAndLoadTask();
        task.execute(5); // 下拉速度
    }

    /**
     * 自动加载
     */
    public void autoLoad() {
        pullUpY = -loadmoreDist;
        requestLayout();
        changeState(LOADING);
        // 加载操作
        if (mListener != null)
            mListener.onLoadMore(this);
    }

    private RelativeLayout layoutButtomRefresh;
    private LinearLayout layoutNoMore, layoutComplayRefreshInfo, loadmoreIndex;

    private void initView() {
        // 头部 初始化下拉布局
        pullView = refreshView.findViewById(R.id.pull_icon);
        refreshStateTextView = (TextView) refreshView.findViewById(R.id.state_tv);
        refreshingView = refreshView.findViewById(R.id.refreshing_icon);
        refreshStateImageView = refreshView.findViewById(R.id.state_iv);
        pullView.setVisibility(View.GONE);

        // 底部 初始化上拉布局
        // loadmoreView.setVisibility(View.GONE);
        pullUpView = loadmoreView.findViewById(R.id.pullup_icon);

        loadStateTextView = (TextView) loadmoreView.findViewById(R.id.loadstate_tv);
        loadingView = loadmoreView.findViewById(R.id.loading_icon);
        loadStateImageView = loadmoreView.findViewById(R.id.loadstate_iv);

        loadmoreIndex = (LinearLayout) loadmoreView.findViewById(R.id.bottom_view);
        loadmoreIndex.setVisibility(View.GONE);

        pullUpView.setVisibility(View.GONE);
        loadStateTextView.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);
        loadStateImageView.setVisibility(View.GONE);

        layoutNoMore = (LinearLayout) loadmoreView.findViewById(R.id.layout_no_more); // 已全部加载完
        layoutNoMore.setVisibility(View.GONE);


        layoutButtomRefresh = (RelativeLayout) loadmoreView.findViewById(R.id.layout_buttom_refresh);

        // 头部刷新下方，投资安全的信息veiw
        layoutComplayRefreshInfo = (LinearLayout) refreshView.findViewById(R.id.layout_complay_refresh_info);

        layoutComplayRefreshInfo.setVisibility(View.VISIBLE);

    }

	/* @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
	{*/

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


	/*@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
*/

        if (!isLayout) {
            // 这里是第一次进来的时候做一些初始化
            refreshView = getChildAt(0);
            pullableView = getChildAt(1);
            loadmoreView = getChildAt(2);
            isLayout = true;
            initView();
            refreshDist = ((ViewGroup) refreshView).getChildAt(0)
                    .getMeasuredHeight();
            loadmoreDist = ((ViewGroup) loadmoreView).getChildAt(0)
                    .getMeasuredHeight();
        }
        // 改变子控件的布局，这里直接用(pullDownY + pullUpY)作为偏移量，这样就可以不对当前状态作区分
        refreshView.layout(0,
                (int) (pullDownY + pullUpY) - refreshView.getMeasuredHeight(),
                refreshView.getMeasuredWidth(), (int) (pullDownY + pullUpY));
        pullableView.layout(0, (int) (pullDownY + pullUpY),
                pullableView.getMeasuredWidth(), (int) (pullDownY + pullUpY)
                        + pullableView.getMeasuredHeight());
        loadmoreView.layout(0,
                (int) (pullDownY + pullUpY) + pullableView.getMeasuredHeight(),
                loadmoreView.getMeasuredWidth(),
                (int) (pullDownY + pullUpY) + pullableView.getMeasuredHeight()
                        + loadmoreView.getMeasuredHeight());

        // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    class MyTimer {
        private Handler handler;
        private Timer timer;
        private MyTask mTask;

        public MyTimer(Handler handler) {
            this.handler = handler;
            timer = new Timer();
        }

        public void schedule(long period) {
            if (mTask != null) {
                mTask.cancel();
                mTask = null;
            }
            mTask = new MyTask(handler);
            timer.schedule(mTask, 0, period);
        }

        public void cancel() {
            if (mTask != null) {
                mTask.cancel();
                mTask = null;
            }
        }

        class MyTask extends TimerTask {
            private Handler handler;

            public MyTask(Handler handler) {
                this.handler = handler;
            }

            @Override
            public void run() {
                handler.obtainMessage().sendToTarget();
            }

        }
    }

    /**
     * 刷新加载回调接口
     *
     * @author chenjing
     */
    public interface OnRefreshListener {
        /**
         * 刷新操作
         */
        void onRefresh(PullToRefreshLayout pullToRefreshLayout);

        /**
         * 加载操作
         */
        void onLoadMore(PullToRefreshLayout pullToRefreshLayout);
    }

    // 设置是在list中刷新，还是一级入口头部刷新，动态的设置一些view隐藏或显示，
    // 二参数,boolean isHomeList：是否是主页理财产品的下拉刷新
    public void setIsListOrHomeRefresh(boolean isListOrHomeRefresh, boolean isHomeList) {
        this.isListOrHomeRefresh = isListOrHomeRefresh;
        this.isHomeList = isHomeList; // 出一级入口的其他刷新都没有 头部介绍部分 ： 如：安全保障等文字
    }

    public boolean getIsListOrHomeRefresh() {
        return this.isListOrHomeRefresh;
    }

    /**
     * 设置上拉时，状态view恢复的间隔时间
     *
     * @param pullUpProduactInfo
     */
    public void setOnLoadMoreTime(long pullUpProduactInfo) {
        this.onLoadMoreTime = pullUpProduactInfo;
    }

    /**
     * 设置上拉时，状态view恢复的间隔时间
     *
     * @param onRefreshTime
     */
    public void setOnRefreshTime(long onRefreshTime) {
        this.onRefreshTime = onRefreshTime;
    }

}
