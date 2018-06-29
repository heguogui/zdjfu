package com.hz.zdjfu.application.modle.getturepwd;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.modle.login.LoginActivity;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.dialog.CustomProgressDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * [类功能说明]
 * 解锁密码图案
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/28 0028.
 */

public class UnlockGesturePwdFragment extends BaseFragment {
    private static String TAG = UnlockGesturePwdFragment.class.getName();
    @BindView(R.id.gesturepwd_unlock_lockview)
    LockPatternView mLockPatternView;
    @BindView(R.id.txtBtn_other_acount)
    TextView txtBtnOtherAcount;
    @BindView(R.id.gesturepwd_unlock_forget)
    TextView gesturepwdUnlockForget;
    @BindView(R.id.gesturepwd_unlock_text)
    TextView mHeadTextView;
    @BindView(R.id.fragment_unlockgesturepwd_top_left)
    ImageView fragmentUnlockgesturepwdTopLeft;
    @BindView(R.id.fragment_unlockgesturepwd_top_title)
    TextView fragmentUnlockgesturepwdTopTitle;
    @BindView(R.id.fragment_unlockgesturepwd_top_right)
    TextView fragmentUnlockgesturepwdTopRight;
    @BindView(R.id.fragment_unlockgesturepwd_top_ll)
    RelativeLayout fragmentUnlockgesturepwdTopll;

    private int mFailedPatternAttemptsSinceLastTimeout = 0;
    private CountDownTimer mCountdownTimer = null;
    private Handler mHandler = new Handler();
    private Animation mShakeAnim;
    private String inputFlg;
    public static String unlockGesturePwdFlg = null;
    private CustomProgressDialog dialogcz;

    public static UnlockGesturePwdFragment newInstance() {
        return new UnlockGesturePwdFragment();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_unlockgesturepwd;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        Bundle mBundle = getArguments();
        if (mBundle != null) {
            inputFlg = mBundle.getString("MPARENT");
        }
        mLockPatternView.setOnPatternListener(mChooseNewLockPatternListener);
        mLockPatternView.setTactileFeedbackEnabled(true);
        mShakeAnim = AnimationUtils.loadAnimation(mActivity, R.anim.shake_x);
        fragmentUnlockgesturepwdTopTitle.setText(getString(R.string.gesture_input_pwd_title)+"");
        fragmentUnlockgesturepwdTopLeft.setVisibility(View.GONE);
        fragmentUnlockgesturepwdTopRight.setVisibility(View.GONE);
        if(!TextUtils.isEmpty(inputFlg)){
            if (inputFlg.equals("1")) { // 从设置里进行解除手势，或新绘制
                txtBtnOtherAcount.setVisibility(View.GONE);
                mHeadTextView.setText("请输入原手势密码");
                fragmentUnlockgesturepwdTopll.setVisibility(View.VISIBLE);
            } else if (inputFlg.equals("2")) {
                txtBtnOtherAcount.setVisibility(View.VISIBLE);
                mHeadTextView.setText("请输入手势密码");
                fragmentUnlockgesturepwdTopll.setVisibility(View.GONE);
            }
        }
    }


    @OnClick({R.id.txtBtn_other_acount, R.id.gesturepwd_unlock_forget,R.id.fragment_unlockgesturepwd_top_left, R.id.fragment_unlockgesturepwd_top_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txtBtn_other_acount:
                unlockGesturePwdFlg = "2";
                startActivity(LoginActivity.makeIntent(mActivity, null));
                break;
            case R.id.gesturepwd_unlock_forget:
                dialogcz = CustomProgressDialog.createDialogContent(mActivity, confirmOnClickGoLogin);
                dialogcz.setTextContent("", getResources().getString(R.string.gesture_password_guide_reset_login));
                Button btnOK = (Button) dialogcz.findViewById(R.id.btn_OK);
                btnOK.setText(getResources().getString(R.string.gesture_reset_login));
                dialogcz.show();
                break;
            case R.id.fragment_unlockgesturepwd_top_left:
                mActivity.finish();
                break;
            case R.id.fragment_unlockgesturepwd_top_right:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mCountdownTimer != null)
            mCountdownTimer.cancel();
    }


    private Runnable mClearPatternRunnable = new Runnable() {
        public void run() {
            mLockPatternView.clearPattern();
        }
    };

    protected LockPatternView.OnPatternListener mChooseNewLockPatternListener = new LockPatternView.OnPatternListener() {

        public void onPatternStart() {
            mLockPatternView.removeCallbacks(mClearPatternRunnable);
            patternInProgress();
        }

        public void onPatternCleared() {
            mLockPatternView.removeCallbacks(mClearPatternRunnable);
        }

        @Override
        public void onPatternCellAdded(List<LockPatternView.Cell> pattern) {
        }

        public void onPatternDetected(List<LockPatternView.Cell> pattern) {
            if (pattern == null)
                return;

            // 验证手势密码
            if (LockPatternUtils.getInstance().checkPattern(pattern)) {
                mLockPatternView.setDisplayMode(LockPatternView.DisplayMode.Correct);

                if (inputFlg.equals("2")) { // 解锁成功后，跳转到我的财富页
                    // 手势密码验证成功
                } else if (inputFlg.equals("TYPE_SETTING")) {// 设置页
                    // 清空手势密码
                }
                mActivity.finish();

            } else {
                mLockPatternView
                        .setDisplayMode(LockPatternView.DisplayMode.Wrong);
                if (pattern.size() >= LockPatternUtils.MIN_PATTERN_REGISTER_FAIL) {
                    mFailedPatternAttemptsSinceLastTimeout++;
                    int retry = LockPatternUtils.FAILED_ATTEMPTS_BEFORE_TIMEOUT
                            - mFailedPatternAttemptsSinceLastTimeout;
                    if (retry >= 0) {
                        if (retry == 0)
                            showToast("您已5次输错密码，请30秒后再试");
                        mHeadTextView.setText("密码错误，还可以再输入" + retry + "次");
                        mHeadTextView.setTextColor(Color.RED);
                        mHeadTextView.startAnimation(mShakeAnim);
                    }

                } else {
                    // showToast("输入长度不够，请重试");
                    mHeadTextView.setText(getResources().getString(R.string.lockpattern_recording_incorrect_too_short));
                    mHeadTextView.setTextColor(Color.RED);
                    mHeadTextView.startAnimation(mShakeAnim);
                }

                if (mFailedPatternAttemptsSinceLastTimeout >= LockPatternUtils.FAILED_ATTEMPTS_BEFORE_TIMEOUT) {
                    mHandler.postDelayed(attemptLockout, 2000);
                } else {
                    mLockPatternView.postDelayed(mClearPatternRunnable, 2000);
                }
            }
        }

        private void patternInProgress() {
        }
    };

    Runnable attemptLockout = new Runnable() {
        @Override
        public void run() {
            mLockPatternView.clearPattern();
            mLockPatternView.setEnabled(false);
            mCountdownTimer = new CountDownTimer(
                    LockPatternUtils.FAILED_ATTEMPT_TIMEOUT_MS + 1, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    int secondsRemaining = (int) (millisUntilFinished / 1000) - 1;
                    if (secondsRemaining > 0) {
                        mHeadTextView.setText(secondsRemaining + " 秒后重试");
                    } else {
                        mHeadTextView.setText("请绘制手势密码");
                        mHeadTextView.setTextColor(Color.WHITE);
                    }
                }

                @Override
                public void onFinish() {
                    mLockPatternView.setEnabled(true);
                    mFailedPatternAttemptsSinceLastTimeout = 0;
                }
            }.start();
        }
    };


    private void showToast(String message) {
        if (!TextUtils.isEmpty(message)) {
            ToastUtils.show(mActivity, message);
        }
    }


    /**
     * 重新登录
     */
    private View.OnClickListener confirmOnClickGoLogin = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_cancel:
                    dialogcz.dismiss();
                    break;
                case R.id.btn_OK:
                    dialogcz.dismiss();
                    startActivity(LoginActivity.makeIntent(mActivity, null));
                    break;
                default:
                    break;
            }
        }
    };

    // 返回上一个画面的功能
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
