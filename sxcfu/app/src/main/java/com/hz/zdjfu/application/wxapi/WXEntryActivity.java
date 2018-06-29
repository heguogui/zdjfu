package com.hz.zdjfu.application.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.even.WeiXinShareEven;
import com.hz.zdjfu.application.modle.MainActivity;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;


import org.greenrobot.eventbus.EventBus;

/**
 * [类功能说明]
 *微信分享回调
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2018/01/04 0019
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

	// IWXAPI 是第三方app和微信通信的openapi接口
	private IWXAPI api;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// 通过WXAPIFactory工厂，获取IWXAPI的实例
		api = WXAPIFactory.createWXAPI(this, Constants.WX_SHARE_APP_ID, false);
		// 将该app注册到微信
		api.registerApp(Constants.WX_SHARE_APP_ID);
		api.handleIntent(getIntent(), this);

		}
	// 微信发送请求到第三方应用时，会回调到该方法


	@Override
	public void onReq(BaseReq req) {

		switch (req.getType()) {
			case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
				break;
			case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:

				break;
			default:
				break;
		}
	}

	// 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
	@Override
	public void onResp(BaseResp resp) {

		String result =null;

		switch (resp.errCode) {
			// 分享成功
			case BaseResp.ErrCode.ERR_OK:
				// 回调查看结果
				result ="分享成功!";
				EventBus.getDefault().post(new WeiXinShareEven(true));
				break;
			case BaseResp.ErrCode.ERR_USER_CANCEL:
				result ="取消分享!";
				break;
			case BaseResp.ErrCode.ERR_AUTH_DENIED:
				result ="分享失败!";
				break;
			default:
				result ="分享失败!";
				break;
		}
		ToastUtils.show(this,result);
		finish();
	}

}