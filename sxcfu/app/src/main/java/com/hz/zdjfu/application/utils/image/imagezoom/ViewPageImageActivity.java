package com.hz.zdjfu.application.utils.image.imagezoom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.utils.image.ImageLoader;
import com.hz.zdjfu.application.widget.photoview.PhotoView;


public class ViewPageImageActivity extends AppCompatActivity implements OnPageChangeListener{

	
	 private ViewPager viewPager;
	 private static List<Map<String,String>> savebitMaps = new ArrayList<Map<String,String>>();
	 private Intent intent;
	 private  WindowManager mWindowManager;  
	 private  LayoutParams wmParams;
	 private  RelativeLayout mFloatLayout;  
	 private  Button mFloatView; 
	 private PopupWindow popupWindow;
	 private String checkimage;
	 private int checkposition;
	 private String typesString;
	 private Map<String,String> map;


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		viewPager = new HackyViewPager(this);
		viewPager.setBackgroundColor(getResources().getColor(R.color.colorBackPrimary));;
		setContentView(viewPager);

		checkposition =0;
		Map<String,String> map1 = new HashMap<>();
		map1.put("bitmapPath","http://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E7%83%AD%E8%A1%80%E6%B1%9F%E6%B9%96&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&cs=2392152497,789448589&os=354950750,3678692837&simid=3439234675,425569745&pn=32&rn=1&di=178595981631&ln=3929&fr=&fmq=1497236099927_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=http%3A%2F%2Ff.hiphotos.baidu.com%2Fzhidao%2Fwh%253D450%252C600%2Fsign%3Deb976948d0160924dc70aa1fe13719cc%2Ff11f3a292df5e0fec44f871f5c6034a85edf725c.jpg&rpstart=0&rpnum=0&adpicid=0");
		savebitMaps.add(map1);

		Map<String,String> map2 = new HashMap<>();
		map2.put("bitmapPath","http://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E7%BE%8E%E5%A5%B3%E5%B8%85%E5%93%A5&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&cs=3671398267,1017625149&os=3871885810,1798171867&simid=2073128,731610095&pn=19&rn=1&di=31604780900&ln=1983&fr=&fmq=1497239711068_R&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&is=0,0&istype=2&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=http%3A%2F%2Fimg0.ph.126.net%2FVbQJgwLAi97Y6yjQUAkyJg%3D%3D%2F6598256139764936856.png&rpstart=0&rpnum=0&adpicid=0");
		savebitMaps.add(map2);

		Map<String,String> map3 = new HashMap<>();
		map3.put("bitmapPath","http://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E8%B7%91%E8%BD%A6&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&cs=2274353102,2287824485&os=200017138,2939768508&simid=4126576276,665263551&pn=20&rn=1&di=23426617721&ln=1997&fr=&fmq=1497239737138_R&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&is=0,0&istype=2&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=http%3A%2F%2Ftupian.enterdesk.com%2F2014%2Flxy%2F2014%2F09%2F25%2F10.jpg&rpstart=0&rpnum=0&adpicid=0");
		savebitMaps.add(map3);

		typesString ="http://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E8%B7%91%E8%BD%A6&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&cs=2274353102,2287824485&os=200017138,2939768508&simid=4126576276,665263551&pn=20&rn=1&di=23426617721&ln=1997&fr=&fmq=1497239737138_R&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&is=0,0&istype=2&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=http%3A%2F%2Ftupian.enterdesk.com%2F2014%2Flxy%2F2014%2F09%2F25%2F10.jpg&rpstart=0&rpnum=0&adpicid=0";
		viewPager.setAdapter(new SamplePagerAdapter(typesString,viewPager,ViewPageImageActivity.this));
		viewPager.setCurrentItem(checkposition);//第一次显示你点击的图片
		viewPager.setOnPageChangeListener(this);//监听viewpage 用于修改显示的页数

	}



	/**
	 * viewpage 适配器
	 * @author heguogui
	 *
	 */
	static class SamplePagerAdapter extends PagerAdapter {
		
		private String typeString;		
		private ViewPager viewPager;
		private Context context;
		
		public SamplePagerAdapter(String typesString,ViewPager viewPager,Context context) {
			super();
			this.typeString = typesString;
			this.viewPager =viewPager;
			this.context =context;
		}

		@Override
		public int getCount() {		
	
			return Integer.MAX_VALUE;
		}

		@SuppressWarnings("static-access")
		@Override
		public View instantiateItem(ViewGroup container, int position) {
			PhotoView photoView = new PhotoView(container.getContext());
			String imagePath = savebitMaps.get(position%savebitMaps.size()).get("bitmapPath");
			photoView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_launcher));
			container.addView(photoView, LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
			ImageLoader.getInstance().displayImage(context,imagePath,photoView);
			return photoView;

		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

	}

	
	
	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		//System.out.println("滑动2"+arg0);
	};

	@Override
	public void onPageSelected(int position) {
		// TODO Auto-generated method stub	

	}
	
			


	 
	 /**
	  * 结束时销毁悬浮的按钮
	  */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		finish();
	}
	
}
