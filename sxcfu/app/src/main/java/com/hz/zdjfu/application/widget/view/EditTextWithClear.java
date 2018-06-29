package com.hz.zdjfu.application.widget.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.utils.UiUtils;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/10 0010.
 */

public class EditTextWithClear extends android.support.v7.widget.AppCompatEditText implements View.OnTouchListener,
        View.OnFocusChangeListener {

    public interface Listener {
        void didClearText();
    }

    private Drawable xD;

    public EditTextWithClear(Context context) {
        super(context);
        init();
    }

    public EditTextWithClear(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextWithClear(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        this.l = l;
    }

    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener f) {
        this.f = f;
    }

    private OnTouchListener l;
    private OnFocusChangeListener f;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (getCompoundDrawables()[2] != null) {
            boolean tappedX = event.getX() > (getWidth() - getPaddingRight() - xD
                    .getIntrinsicWidth());
            if (tappedX) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    setText("");

                }
                return true;
            }
        }
        if (l != null) {
            return l.onTouch(v, event);
        }
        return false;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            setClearIconVisible(!TextUtils.isEmpty(getText()));
        } else {
            setClearIconVisible(false);
        }
        if (f != null) {
            f.onFocusChange(v, hasFocus);
        }
    }


    private void init() {
        xD = getCompoundDrawables()[2];
        if (xD == null) {
            xD = getResources().getDrawable(getDefaultClearIconId());

        }
        xD.setBounds(0, 0, xD.getIntrinsicWidth(), xD.getIntrinsicHeight());
        setClearIconVisible(false);
        addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // TODO Auto-generated method stub
                setClearIconVisible(!TextUtils.isEmpty(getText()));
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });
        // this.setPaddingRelative(getPaddingLeft(), getPaddingTop(),
        // getPaddingRight(), getPaddingBottom());
        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);

    }


    private int getDefaultClearIconId() {
        int id = R.mipmap.delete;
        return id;

    }

    public void setClearIconVisible(boolean visible) {
        Drawable x = visible ? xD : null;
        setCompoundDrawables(getCompoundDrawables()[0],
                getCompoundDrawables()[1], x, getCompoundDrawables()[3]);
    }


    //银行卡号码的格式
    public void bankCardNumAddSpace(final EditText mEditText) {
        mEditText.addTextChangedListener(new TextWatcher() {
            int beforeTextLength = 0;
            int onTextLength = 0;
            boolean isChanged = false;

            int location = 0;// 记录光标的位置
            private char[] tempChar;
            private StringBuffer buffer = new StringBuffer();
            int konggeNumberB = 0;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                beforeTextLength = s.length();
                if (buffer.length() > 0) {
                    buffer.delete(0, buffer.length());
                }
                konggeNumberB = 0;
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == ' ') {
                        konggeNumberB++;
                    }
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                onTextLength = s.length();
                buffer.append(s.toString());
                if (onTextLength == beforeTextLength || onTextLength <= 3
                        || isChanged) {
                    isChanged = false;
                    return;
                }
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isChanged) {
                    location = mEditText.getSelectionEnd();
                    int index = 0;
                    while (index < buffer.length()) {
                        if (buffer.charAt(index) == ' ') {
                            buffer.deleteCharAt(index);
                        } else {
                            index++;
                        }
                    }

                    index = 0;
                    int konggeNumberC = 0;
                    while (index < buffer.length()) {
                        if ((index == 4 || index == 9 || index == 14 || index == 19)) {
                            buffer.insert(index, ' ');
                            konggeNumberC++;
                        }
                        index++;
                    }

                    if (konggeNumberC > konggeNumberB) {
                        location += (konggeNumberC - konggeNumberB);
                    }

                    tempChar = new char[buffer.length()];
                    buffer.getChars(0, buffer.length(), tempChar, 0);
                    String str = buffer.toString();
                    if (location > str.length()) {
                        location = str.length();
                    } else if (location < 0) {
                        location = 0;
                    }

                    mEditText.setText(str);
                    Editable etable = mEditText.getText();
                    Selection.setSelection(etable, location);
                    isChanged = false;
                }
            }
        });
    }

    // 手机号码的格式
    public void phoneNumAddSpace(final EditText mEditText,final Button enableBtnObj) {
        mEditText.addTextChangedListener(new TextWatcher() {
            int beforeTextLength = 0;
            int onTextLength = 0;
            boolean isChanged = false;

            int location = 0;// 记录光标的位置
            private char[] tempChar;
            private StringBuffer buffer = new StringBuffer();
            int konggeNumberB = 0;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                beforeTextLength = s.length();
                if (buffer.length() > 0) {
                    buffer.delete(0, buffer.length());
                }
                konggeNumberB = 0;
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == ' ') {
                        konggeNumberB++;
                    }
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

                onTextLength = s.length();

                if(onTextLength > 0 ){
                    mEditText.setGravity(Gravity.CENTER_HORIZONTAL);
                }else{
                    mEditText.setGravity(Gravity.CENTER_VERTICAL);
                }

                buffer.append(s.toString());
                if (onTextLength == beforeTextLength || onTextLength <= 3
                        || isChanged) {
                    isChanged = false;
                    // 么有输入内容的时候，按钮不可用
                    if(onTextLength < 13){
                        enableBtnObj.setEnabled(false);
                    }else if(onTextLength == 13){
                        if(UiUtils.isMobileNO(s.toString().replaceAll(" ",""))){
                            enableBtnObj.setEnabled(true);
                        }
                    }
                    return;
                }

                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isChanged) {

                    location = mEditText.getSelectionEnd();
                    int index = 0;
                    while (index < buffer.length()) {
                        if (buffer.charAt(index) == ' ') {
                            buffer.deleteCharAt(index);
                        } else {
                            index++;
                        }
                    }

                    index = 0;
                    int konggeNumberC = 0;
                    while (index < buffer.length()) {
                        if ((index == 3 || index == 8)) {
                            buffer.insert(index, ' ');
                            konggeNumberC++;
                        }
                        index++;
                    }

                    if (konggeNumberC > konggeNumberB) {
                        location += (konggeNumberC - konggeNumberB);
                    }

                    tempChar = new char[buffer.length()];
                    buffer.getChars(0, buffer.length(), tempChar, 0);
                    String str = buffer.toString();
                    if (location > str.length()) {
                        location = str.length();
                    } else if (location < 0) {
                        location = 0;
                    }
                    // enableBtnObj.setEnabled(true);
                    mEditText.setText(str);
                    Editable etable = mEditText.getText();
                    Selection.setSelection(etable, location);
                    isChanged = false;
                }
            }
        });
    }

    //  密码>=6位的时候，按钮可用
    public void pwdEdit(final EditText mEditText,final Button enableBtnObj) {
        mEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if(s.length() >= 6){ // 密码>=6位的时候，下一步按钮可用
                    enableBtnObj.setEnabled(true);

                }else{
                    enableBtnObj.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     原始登录密码和新密码 都 ≥6
     按钮可用
     */
    public void pwdModify(final EditText oldPwdEditText,final EditText newPwdEditText,final Button enableBtnObj) {
        oldPwdEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before,int count) {
                //  密码>=6 验证码=6位 位的时候，下一步按钮可用
                if(newPwdEditText.getText().toString().length() >= 6 && s.length() >= 6){
                    enableBtnObj.setEnabled(true);

                }else{
                    enableBtnObj.setEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {  }
        });
        newPwdEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before,int count) {
                //  密码>=6 验证码=6位 位的时候，下一步按钮可用
                if(s.length() >= 6 && oldPwdEditText.getText().toString().length() >= 6){
                    enableBtnObj.setEnabled(true);

                }else{
                    enableBtnObj.setEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {  }
        });
    }

    /**
     验证码=6位
     登录密码≥6位时按钮可用
     */
    public void pwdAndSMSEdit(final EditText SMSEditText,final EditText pwdEditText,final Button enableBtnObj) {
        SMSEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before,int count) {
                //  密码>=6 验证码=6位 位的时候，下一步按钮可用
                if(pwdEditText.getText().toString()
                        .length() >= 6 && s.length() == 6){
                    enableBtnObj.setEnabled(true);

                }else{
                    enableBtnObj.setEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {  }
        });
        pwdEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before,int count) {
                //  密码>=6 验证码=6位 位的时候，下一步按钮可用
                if(s.length() >= 6 && SMSEditText.length() == 16){
                    enableBtnObj.setEnabled(true);

                }else{
                    enableBtnObj.setEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {  }
        });
    }

}
