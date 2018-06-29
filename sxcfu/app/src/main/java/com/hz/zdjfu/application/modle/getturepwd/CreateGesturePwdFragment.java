package com.hz.zdjfu.application.modle.getturepwd;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.modle.MainActivity;
import com.hz.zdjfu.application.modle.getturepwd.LockPatternView.DisplayMode;
import com.hz.zdjfu.application.utils.PreferencesUtils;
import com.hz.zdjfu.application.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * [类功能说明]
 * 创建手势密码
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/28 0028.
 */

public class CreateGesturePwdFragment extends BaseFragment {

    private static String TAG = CreateGesturePwdFragment.class.getName();

    @BindView(R.id.gesturepwd_create_text)
    TextView mHeaderText;
    @BindView(R.id.gesturepwd_create_lockview)
    LockPatternView mLockPatternView;
    @BindView(R.id.reset_btn)
    Button mFooterLeftButton;
    @BindView(R.id.right_btn)
    Button mFooterRightButton;


    private static final int ID_EMPTY_MESSAGE = -1;
    private static final String KEY_UI_STAGE = "uiStage";
    private static final String KEY_PATTERN_CHOICE = "chosenPattern";
    protected List<LockPatternView.Cell> mChosenPattern = null;
    @BindView(R.id.fragment_creategesture_top_left)
    ImageView fragmentCreategestureTopLeft;
    @BindView(R.id.fragment_creategesture_top_title)
    TextView fragmentCreategestureTopTitle;
    @BindView(R.id.fragment_creategesture_top_right)
    TextView fragmentCreategestureTopRight;

    private Stage mUiStage = Stage.Introduction;
    private View mPreviewViews[][] = new View[3][3];
    private Animation mShakeAnim;
    private String mParent;
    private final List<LockPatternView.Cell> mAnimatePattern = new ArrayList<LockPatternView.Cell>();


    enum
    LeftButtonMode {
        Cancel(R.string.lockpattern_continue_button_cancel, true),
        CancelDisabled(android.R.string.cancel, false),
        Retry(R.string.lockpattern_retry_button_text, true),
        RetryDisabled(R.string.lockpattern_retry_button_text, false),
        Gone(ID_EMPTY_MESSAGE, false);

        /**
         * @param text    The displayed text for this mode.
         * @param enabled Whether the button should be enabled.
         */
        LeftButtonMode(int text, boolean enabled) {
            this.text = text;
            this.enabled = enabled;
        }

        final int text;
        final boolean enabled;
    }

    enum RightButtonMode {
        Continue(R.string.lockpattern_continue_button_text, true), ContinueDisabled(
                R.string.lockpattern_continue_button_text, false), Confirm(
                R.string.lockpattern_confirm_button_text, true), ConfirmDisabled(
                R.string.lockpattern_confirm_button_text, false), Ok(
                android.R.string.ok, true);

        /**
         * @param text    The displayed text for this mode.
         * @param enabled Whether the button should be enabled.
         */
        RightButtonMode(int text, boolean enabled) {
            this.text = text;
            this.enabled = enabled;
        }

        final int text;
        final boolean enabled;
    }

    protected enum Stage {

        Introduction(R.string.lockpattern_recording_intro_header,
                LeftButtonMode.Cancel, RightButtonMode.ContinueDisabled,
                ID_EMPTY_MESSAGE, true),
        HelpScreen(
                R.string.lockpattern_settings_help_how_to_record,
                LeftButtonMode.Gone, RightButtonMode.Ok, ID_EMPTY_MESSAGE,
                false),
        ChoiceTooShort(
                R.string.lockpattern_recording_incorrect_too_short,
                LeftButtonMode.Retry, RightButtonMode.ContinueDisabled,
                ID_EMPTY_MESSAGE, true),
        FirstChoiceValid(
                R.string.lockpattern_pattern_entered_header,
                LeftButtonMode.Cancel, RightButtonMode.ContinueDisabled,
                ID_EMPTY_MESSAGE, true),
        /*LeftButtonMode.Retry, RightButtonMode.Continue,
        ID_EMPTY_MESSAGE, false),*/
        NeedToConfirm(
                R.string.lockpattern_need_to_confirm,
                LeftButtonMode.Retry, RightButtonMode.ContinueDisabled,
                ID_EMPTY_MESSAGE, true),
        /* LeftButtonMode.Cancel,
         RightButtonMode.ConfirmDisabled, ID_EMPTY_MESSAGE, true),*/
        ConfirmWrong(
                R.string.lockpattern_need_to_unlock_wrong,
                LeftButtonMode.Retry, RightButtonMode.ContinueDisabled,
                ID_EMPTY_MESSAGE, true),
        /*LeftButtonMode.Cancel, RightButtonMode.ConfirmDisabled,
        ID_EMPTY_MESSAGE, true),*/
        ChoiceConfirmed(
                R.string.lockpattern_pattern_confirmed_header,
                LeftButtonMode.Cancel, RightButtonMode.Confirm,
                ID_EMPTY_MESSAGE, false);

        /**
         * @param headerMessage  The message displayed at the top.
         * @param leftMode       The mode of the left button.
         * @param rightMode      The mode of the right button.
         * @param footerMessage  The footer message.
         * @param patternEnabled Whether the pattern widget is enabled.
         */
        Stage(int headerMessage, LeftButtonMode leftMode,
              RightButtonMode rightMode, int footerMessage,
              boolean patternEnabled) {
            this.headerMessage = headerMessage;
            this.leftMode = leftMode;
            this.rightMode = rightMode;
            this.footerMessage = footerMessage;
            this.patternEnabled = patternEnabled;
        }

        final int headerMessage;
        final LeftButtonMode leftMode;
        final RightButtonMode rightMode;
        final int footerMessage;
        final boolean patternEnabled;
    }


    public static CreateGesturePwdFragment newInstance() {
        return new CreateGesturePwdFragment();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_creategesturepwd;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        initViewData();
        initPreviewViews(view);
        checkSaveInstanceData(savedInstanceState);
    }


    /**
     * 页面后端消亡后 重新绘制
     *
     * @param savedInstanceState
     */
    private void checkSaveInstanceData(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            updateStage(Stage.Introduction);
            // 隐藏帮助 手势密码演示
            // updateStage(Stage.HelpScreen);
        } else {
            // restore from previous state
            final String patternString = savedInstanceState
                    .getString(KEY_PATTERN_CHOICE);
            if (patternString != null) {
                mChosenPattern = LockPatternUtils
                        .stringToPattern(patternString);
            }
            updateStage(Stage.values()[savedInstanceState.getInt(KEY_UI_STAGE)]);
        }
    }




    /**
     * 初始化View 数据
     */
    private void initViewData() {

        Bundle mBundle = getArguments();
        if (mBundle != null) {
            mParent = mBundle.getString("MPARENT");
        }
        // 初始化演示动画
        mAnimatePattern.add(LockPatternView.Cell.of(0, 0));
        mAnimatePattern.add(LockPatternView.Cell.of(0, 1));
        mAnimatePattern.add(LockPatternView.Cell.of(1, 1));
        mAnimatePattern.add(LockPatternView.Cell.of(2, 1));
        mAnimatePattern.add(LockPatternView.Cell.of(2, 2));

        mLockPatternView.setOnPatternListener(mChooseNewLockPatternListener);
        mLockPatternView.setTactileFeedbackEnabled(true);

        mShakeAnim = AnimationUtils.loadAnimation(mActivity, R.anim.shake_x);
        fragmentCreategestureTopTitle.setText(getString(R.string.gesture_set_pwd_title)+"");
        fragmentCreategestureTopRight.setText(getString(R.string.gesture_top_right_title)+"");
    }

    private void initPreviewViews(View mview) {
        mPreviewViews = new View[3][3];
        mPreviewViews[0][0] = mview.findViewById(R.id.gesturepwd_setting_preview_0);
        mPreviewViews[0][1] = mview.findViewById(R.id.gesturepwd_setting_preview_1);
        mPreviewViews[0][2] = mview.findViewById(R.id.gesturepwd_setting_preview_2);
        mPreviewViews[1][0] = mview.findViewById(R.id.gesturepwd_setting_preview_3);
        mPreviewViews[1][1] = mview.findViewById(R.id.gesturepwd_setting_preview_4);
        mPreviewViews[1][2] = mview.findViewById(R.id.gesturepwd_setting_preview_5);
        mPreviewViews[2][0] = mview.findViewById(R.id.gesturepwd_setting_preview_6);
        mPreviewViews[2][1] = mview.findViewById(R.id.gesturepwd_setting_preview_7);
        mPreviewViews[2][2] = mview.findViewById(R.id.gesturepwd_setting_preview_8);
    }


    private void updatePreviewViews(boolean isShow) {
        if (mChosenPattern == null)
            return;
        if (isShow) { // 显示已经绘制的手势图案
            // Log.i("way", "result = " + mChosenPattern.toString());
            for (LockPatternView.Cell cell : mChosenPattern) {
            /*Log.i("way", "cell.getRow() = " + cell.getRow()
					+ ", cell.getColumn() = " + cell.getColumn());*/
                mPreviewViews[cell.getRow()][cell.getColumn()]
                        .setBackgroundResource(R.mipmap.gesture_pattern_min_selected);
            }
        } else { // 清空，还原已绘制的手势图案
            //Log.e("way", "result = " + mChosenPattern.toString());
            for (LockPatternView.Cell cell : mChosenPattern) {
                //Log.e("way", "cell.getRow() = " + cell.getRow()
                //		+ ", cell.getColumn() = " + cell.getColumn());
                mPreviewViews[cell.getRow()][cell.getColumn()]
                        .setBackgroundResource(R.mipmap.gesture_pattern_min);
            }
        }
    }


    /**
     * 后代后保存数据
     *
     * @param outState
     */
    public void saveInstanceState(Bundle outState) {
        outState.putInt(KEY_UI_STAGE, mUiStage.ordinal());
        if (mChosenPattern != null) {
            outState.putString(KEY_PATTERN_CHOICE,
                    LockPatternUtils.patternToString(mChosenPattern));
        }
    }

    private Runnable mClearPatternRunnable = new Runnable() {
        public void run() {
            mLockPatternView.clearPattern();
        }
    };


    private void showToast(String message) {

        if (!TextUtils.isEmpty(message)) {
            ToastUtils.show(mActivity, message);
        }
    }


    @OnClick({R.id.reset_btn, R.id.right_btn,R.id.fragment_creategesture_top_left, R.id.fragment_creategesture_top_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reset_btn:

                // 清空已绘制图案
                if (mUiStage.leftMode == LeftButtonMode.Retry) {
                    updatePreviewViews(false);
                    mLockPatternView.clearPattern();
                    updateStage(Stage.Introduction);

                    // 取消绘制图案Activiy
                } else if (mUiStage.leftMode == LeftButtonMode.Cancel) {
                    // They are canceling the entire wizard
                    // finish();
                } else {
                    throw new IllegalStateException(
                            "left footer button pressed, but stage of " + mUiStage
                                    + " doesn't make sense");
                }

                break;
            case R.id.right_btn:

                if (mUiStage.rightMode == RightButtonMode.Continue) {
                    if (mUiStage != Stage.FirstChoiceValid) {
                        throw new IllegalStateException("expected ui stage "
                                + Stage.FirstChoiceValid + " when button is "
                                + RightButtonMode.Continue);
                    }
                    updateStage(Stage.NeedToConfirm);
                } else if (mUiStage.rightMode == RightButtonMode.Confirm) {
                    if (mUiStage != Stage.ChoiceConfirmed) {
                        throw new IllegalStateException("expected ui stage "
                                + Stage.ChoiceConfirmed + " when button is "
                                + RightButtonMode.Confirm);
                    }
                    saveChosenPatternAndFinish();
                } else if (mUiStage.rightMode == RightButtonMode.Ok) {
                    if (mUiStage != Stage.HelpScreen) {
                        throw new IllegalStateException(
                                "Help screen is only mode with ok button, but "
                                        + "stage is " + mUiStage);
                    }
                    mLockPatternView.clearPattern();
                    mLockPatternView.setDisplayMode(DisplayMode.Correct);
                    updateStage(Stage.Introduction);
                }
                break;
            case R.id.fragment_creategesture_top_right:
                if (null != UnlockGesturePwdFragment.unlockGesturePwdFlg &&
                        "4".equals(UnlockGesturePwdFragment.unlockGesturePwdFlg)) {
                    UnlockGesturePwdFragment.unlockGesturePwdFlg = null;
                } else if (null != UnlockGesturePwdFragment.unlockGesturePwdFlg &&
                        "5".equals(UnlockGesturePwdFragment.unlockGesturePwdFlg)) {
                    UnlockGesturePwdFragment.unlockGesturePwdFlg = null;
                }

                //跳过
                startActivity(MainActivity.makeIntent(mActivity,null));
                mActivity.finish();
                break;
            case R.id.fragment_creategesture_top_left:
                mActivity.finish();
                break;
        }
    }


    protected LockPatternView.OnPatternListener mChooseNewLockPatternListener = new LockPatternView.OnPatternListener() {

        public void onPatternStart() {
            mLockPatternView.removeCallbacks(mClearPatternRunnable);
            patternInProgress();
        }

        public void onPatternCleared() {
            mLockPatternView.removeCallbacks(mClearPatternRunnable);
        }

        public void onPatternDetected(List<LockPatternView.Cell> pattern) {
            if (pattern == null)
                return;
            // Log.i("way", "result = " + pattern.toString());
            if (mUiStage == Stage.NeedToConfirm
                    || mUiStage == Stage.ConfirmWrong) {
                if (mChosenPattern == null)
                    throw new IllegalStateException(
                            "null chosen pattern in stage 'need to confirm");
                if (mChosenPattern.equals(pattern)) {
                    updateStage(Stage.ChoiceConfirmed);
                } else {
                    updateStage(Stage.ConfirmWrong);
                }
            } else if (mUiStage == Stage.Introduction
                    || mUiStage == Stage.ChoiceTooShort) {
                if (pattern.size() < LockPatternUtils.MIN_LOCK_PATTERN_SIZE) {
                    updateStage(Stage.ChoiceTooShort);
                } else {
                    mChosenPattern = new ArrayList<LockPatternView.Cell>(
                            pattern);
                    updateStage(Stage.FirstChoiceValid);
                }
            } else {
                return;
			/*	throw new IllegalStateException("Unexpected stage " + mUiStage
						+ " when " + "entering the pattern.");*/
            }
        }

        public void onPatternCellAdded(List<LockPatternView.Cell> pattern) {
        }

        private void patternInProgress() {
            mHeaderText.setTextColor(Color.WHITE);
            mHeaderText.setText(R.string.lockpattern_recording_inprogress);
            mFooterLeftButton.setEnabled(false);
            mFooterRightButton.setEnabled(false);
        }
    };

    private void
    updateStage(Stage stage) {
        mUiStage = stage;
        mHeaderText.setTextColor(Color.WHITE);

        if (stage == Stage.ChoiceTooShort) {
            mHeaderText.setText(getResources().getString(stage.headerMessage,
                    LockPatternUtils.MIN_LOCK_PATTERN_SIZE));
            mHeaderText.setTextColor(Color.RED);
            mHeaderText.startAnimation(mShakeAnim);

        } else if (stage == Stage.ConfirmWrong) {
            mHeaderText.setText(getResources().getString(stage.headerMessage,
                    LockPatternUtils.MIN_LOCK_PATTERN_SIZE));
            mHeaderText.setTextColor(Color.RED);
            mHeaderText.startAnimation(mShakeAnim);
        } else {
            mHeaderText.setText(stage.headerMessage);
        }

        if (stage.leftMode == LeftButtonMode.Gone) {
            mFooterLeftButton.setVisibility(View.GONE);
        } else {
            mFooterLeftButton.setVisibility(View.VISIBLE);
            mFooterLeftButton.setText(stage.leftMode.text);
            mFooterLeftButton.setEnabled(stage.leftMode.enabled);
        }

        mFooterRightButton.setText(stage.rightMode.text);
        mFooterRightButton.setEnabled(stage.rightMode.enabled);

        // same for whether the patten is enabled
        if (stage.patternEnabled) {
            mLockPatternView.enableInput();
        } else {
            mLockPatternView.disableInput();
        }

        mLockPatternView.setDisplayMode(DisplayMode.Correct);

        switch (mUiStage) {
            case Introduction:
                mLockPatternView.clearPattern();
                break;
            case HelpScreen:
                mLockPatternView.setPattern(DisplayMode.Animate, mAnimatePattern);
                break;
            case ChoiceTooShort:
                mLockPatternView.setDisplayMode(DisplayMode.Wrong);
                postClearPatternRunnable();
                break;
            case FirstChoiceValid:

                // 停顿2秒后 再次确认手势密码
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // mFooterLeftButton.setVisibility(View.VISIBLE);
                        updateStage(Stage.NeedToConfirm);
                    }
                }, 1500);

                break;
            case NeedToConfirm:
                mLockPatternView.clearPattern();
                updatePreviewViews(true);
                break;
            case ConfirmWrong:
                mLockPatternView.setDisplayMode(DisplayMode.Wrong);
                postClearPatternRunnable();
                break;

            case ChoiceConfirmed:
                // 停顿2秒后 保存手势密码
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        saveChosenPatternAndFinish();
                    }
                }, 1500);
                break;
        }

    }


    private void postClearPatternRunnable() {
        mLockPatternView.removeCallbacks(mClearPatternRunnable);
        mLockPatternView.postDelayed(mClearPatternRunnable, 2000);
    }


    private void saveChosenPatternAndFinish() {

        // 保存已经设置好手势密码的状态
        PreferencesUtils.putBoolean(mActivity, Constants.USER_GESTURE_STATE_PREFERENCE, true);
        //保存图案设置的密码
        LockPatternUtils.getInstance().saveLockPattern(mChosenPattern);
        // 手势密码设置成功提醒
        showToast(this.getResources().getString(R.string.lockscreen_access_pattern_detected));

        if (TextUtils.isEmpty(mParent)) {//为空则初创 既不是从设置中来 进入首页
            mActivity.overridePendingTransition(R.anim.home_in_anim, 0);
            mActivity.startActivity(MainActivity.makeIntent(mActivity, null));
        }

        mActivity.finish();
    }


    // 返回上一个画面的功能
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //关闭窗体动画显示
                mActivity.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
