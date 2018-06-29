
package com.hz.zdjfu.application.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.data.bean.UserDetailBean;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UiUtils {

    private static Map<String,String[]> map;


    static public int getScreenWidthPixels(Context context) {

        DisplayMetrics dm = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
                .getMetrics(dm);
        return dm.widthPixels;
    }

    static public int dipToPx(Context context, int dip) {
        return (int) (dip * getScreenDensity(context) + 0.5f);
    }

    static public float getScreenDensity(Context context) {
        try {
            DisplayMetrics dm = new DisplayMetrics();
            ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
                    .getMetrics(dm);
            return dm.density;
        } catch (Exception e) {
            return DisplayMetrics.DENSITY_DEFAULT;
        }
    }

    // 此方法在setAdapter之前调用
    // 动态的改变list的高度
    static public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    // 金额的格式化 BigDecimal
    static public String formatMoneyToBigDecimal(String money, String formatType) {
        String newMoney = "";
        if(null != money && !money.isEmpty() && !TextUtils.equals(".",money)){
            // 金额格式化 ###，###
            DecimalFormat moneyFormat = new DecimalFormat(formatType);
            newMoney =  moneyFormat.format(Double.parseDouble(money));
        }else{
            newMoney = "0";
        }
        return  newMoney;
    }

    //数值减法运算
    public static double sub(String v1, String v2){
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2).doubleValue();
    }

    //保留两位小数
    public static String formatSub(double s){
        DecimalFormat df = new DecimalFormat("#.00");
        String string = df.format(s);
        return string;
    }

    // 充值，网银支付银行列表获取
    static public HashMap<String,String> getRechargeBanks(){
        HashMap<String, String> map = new HashMap<String,String>();

        map.put(CCBStr,CCB); // YES
        map.put(CIBStr,CIB);
        // map.put(BOSStr,BOS);
        map.put(CMBStr,CMB);// YES
        map.put(CEBStr,CEB);// YES

       // map.put(ICBCStr,ICBC);
       // map.put(CNCBStr,CNCB);
       // map.put(BOCStr,BOC);
       // map.put(PSBCStr,PSBC);
       // map.put(CMBCStr,CMBC);
       // map.put(SPDBStr,SPDB);
       // map.put(SRCBStr,SRCB);

        return map;
    };

   // private final static String NO = "NO",NOStr = "请选择";
    private final static String CNCB = "CNCB",CNCBStr = "中信银行";
    private final static String ICBC = "ICBC",ICBCStr = "中国工商银行";
    private  final static String ABC = "ABC",ABCStr = "中国农业银行";
    private  final static String CMB = "CMB",CMBStr = "招商银行";
    private final  static String CCB = "CCB",CCBStr = "中国建设银行";
    private  final static String PSBC = "PSBC",PSBCStr = "中国邮政储蓄银行";
    private  final static String BOC = "BOC",BOCStr = "中国银行";
    private  final static String COMM = "COMM",COMMStr = "交通银行";

    private  final static String CEB = "CEB",CEBStr = "中国光大银行";
    private  final static String CIB = "CIB",CIBStr = "兴业银行";
    private  final static String CMBC = "CMBC",CMBCStr = "中国民生银行";
    private  final static String GDB = "GDB",GDBStr = "广发银行";
    private  final static String HXB = "HXB",HXBStr = "华夏银行";
    private  final static String HZCB = "HZCB",HZCBStr = "杭州银行";
    private  final static String SZPAB = "SZPAB",SZPABStr = "平安银行";
    private  final static String BJRCB = "BJRCB",BJRCBStr = "北京农村商业银行";
    private  final static String SPDB = "SPDB",SPDBStr = "浦发银行";
    private  final static String BCCB = "BCCB",BCCBStr = "北京银行";
    private  final static String BOS = "BOS",BOSStr = "上海银行";
    private  final static String CBHB = "CBHB",CBHBStr = "渤海银行";
    private  final static String CZB = "CZB",CZBStr = "浙商银行";
    private  final static String NJCB = "NJCB",NJCBStr = "南京银行";
    private  final static String SRCB = "SRCB",SRCBStr = "上海农村商业银行";
    private  final static String CITIC = "CITIC",CITICStr = "中信银行";

    // 根据银行卡type获取相应的背景图片，银行卡图标
    static public Map<String,String[]> CreaterBankCardIcon(){

         map = new HashMap<String,String[]>();

        map.put(ICBC,new String[]{R.mipmap.ic_bank_bg_red+"", R.mipmap.ic_bank_gs+"",ICBCStr,R.color.red3+""});// 工商*
        map.put(ABC,new String[]{R.mipmap.ic_bank_bg_gre+"", R.mipmap.ic_bank_ny+"",ABCStr,R.color.green0+""});// 农业*
        map.put(CCB,new String[]{R.mipmap.ic_bank_bg_blu+"", R.mipmap.ic_bank_js+"",CCBStr,R.color.blue11+""});// 建设*
        map.put(PSBC,new String[]{R.mipmap.ic_bank_bg_gre+"", R.mipmap.ic_bank_yz+"",PSBCStr,R.color.green0+""});// 邮储银行*
        map.put(BOC,new String[]{R.mipmap.ic_bank_bg_red+"", R.mipmap.ic_bank_zg+"",BOCStr,R.color.red3+""});// 中国银行*
        map.put(CEB,new String[]{R.mipmap.ic_bank_bg_yel+"", R.mipmap.ic_bank_gd+"",CEBStr,R.color.gold7+""});// 光大银行*
        map.put(CIB,new String[]{R.mipmap.ic_bank_bg_blu+"", R.mipmap.ic_bank_xy+"",CIBStr,R.color.blue11+""});// 兴业银行*
        map.put(HXB,new String[]{R.mipmap.ic_bank_bg_red+"", R.mipmap.ic_bank_hx+"",HXBStr,R.color.red3+""});// 华夏银行*
        map.put(CITIC,new String[]{R.mipmap.ic_bank_bg_red+"", R.mipmap.ic_bank_zx+"",CITICStr,R.color.red3+""});// 中信银行*
        map.put(BOS,new String[]{R.mipmap.ic_bank_bg_yel+"",R.mipmap.ic_bank_sh+"",BOSStr,R.color.gold7+""});// 上海银行*
        map.put(CMB,new String[]{R.mipmap.ic_bank_bg_red+"", R.mipmap.ic_bank_zs+"",CMBStr,R.color.red3+""});// 招商*
        map.put(COMM,new String[]{R.mipmap.ic_bank_bg_blu+"", R.mipmap.ic_bank_jt+"",COMMStr,R.color.blue11+""});// 交通银行*
        map.put(GDB,new String[]{R.mipmap.ic_bank_bg_red+"", R.mipmap.ic_bank_gf+"",GDBStr,R.color.red3+""});// 广发银行*
        map.put(SZPAB,new String[]{R.mipmap.ic_bank_bg_yel+"", R.mipmap.ic_bank_pa+"",SZPABStr,R.color.gold7+""});// 平安银行*
        map.put(SPDB,new String[]{R.mipmap.ic_bank_bg_gre+"",R.mipmap.ic_bank_pf+"",SPDBStr,R.color.blue11+""});// 上海浦发*



        // 默认图标的银行
        map.put(BJRCB,new String[]{R.mipmap.ic_bank_bg_red+"","0",BJRCBStr,R.color.red3+""});// 北京农村商业银行
        map.put(BCCB,new String[]{R.mipmap.ic_bank_bg_red+"","0",BCCBStr,R.color.red3+""});// 北京银行
        map.put(CBHB,new String[]{R.mipmap.ic_bank_bg_gre+"","0",CBHBStr,R.color.green0+""});// 渤海银行
        map.put(CZB,new String[]{R.mipmap.ic_bank_bg_yel+"","0",CZBStr,R.color.gold7+""});// 浙商银行
        map.put(NJCB,new String[]{R.mipmap.ic_bank_bg_red+"","0",NJCBStr,R.color.red3+""});// 南京银行
        map.put(SRCB,new String[]{R.mipmap.ic_bank_bg_blu+"","0",SRCBStr,R.color.blue11+""});// 上海农村商业银行
        map.put(HZCB,new String[]{R.mipmap.ic_bank_bg_blu+"", R.mipmap.ic_bank_hzyh+"",HZCBStr,R.color.blue11+""});// 杭州银行
        map.put(CMBC,new String[]{R.mipmap.ic_bank_bg_gre+"", R.mipmap.ic_bank_ms+"",CMBCStr,R.color.green0+""});// 民生银行
        // map.put("HZCB",new int[]{R.mipmap.ic_bank_bg_blu,R.mipmap.ic_bank_hzyh});// 浙江民泰商业银行 TODo
       return  map;
    }

    /**
     * 根据银行卡type获取相应的背景图片，银行卡图标
     * @param bankType 银行类型
     * @return  int[] 获取的图标ID
     */
    static public String[] getBankCardIcon(String bankType){
        // 获取银行信息
        bankType = bankType.trim().replaceAll(" ","");
        String[] obj = map.get(bankType);
        if(null == obj || obj.length == 0){// 使用默认图标
            obj = new String[]{R.mipmap.ic_bank_bg_red+"","0","未知银行",R.color.red3+""};
        }
        return obj;
    }


    /**
     *
     * 验证手机号
     * @param mobiles
     *
     * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
       联通：130、131、132、152、155、156、185、186
       电信：133、153、180、189、（1349卫通）
        还有：17** 14**
     *
     * @return 是否合法
     *
     */
    public static boolean isMobileNO(String mobiles){
        Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(17[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 默认弹出软键盘 在 AndroidManifest.xml 中 配置 android:windowSoftInputMode="stateVisible"
     * @param textObj
     */
    public static void showSoftInput(EditText textObj){

        // 默认弹出软键盘
        textObj.setFocusable(true);
        textObj.setFocusableInTouchMode(true);
        textObj.requestFocus();
        InputMethodManager inputManager =
                (InputMethodManager)textObj.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(textObj, 0);
        // InputMethodManager.SHOW_FORCED(提供当前操作的标记，SHOW_FORCED表示强制显示));
        inputManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_NOT_ALWAYS);

    }


    /**
     * 隐藏输入法
     * @param textObj
     */
    public static void hideSoftInput(EditText textObj, Activity activity){
        InputMethodManager inputMethodManager =
                (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(textObj.getWindowToken(), 0);
    }


    /**
     * 替换手机号 135****2548
     * @param phone
     */
    public static String replacePhoneToStar(String phone){
        String newPhone = "";
        if(!TextUtils.equals("",phone) && phone.length() >= 11){
            phone = phone.replaceAll(" ","");
            newPhone =  phone.substring(0,3)+"****"+phone.substring(7,phone.length());
        }
        return newPhone;
    }

    // URL encodeing 后， 再进行关键字符替换
    public static String URLReplaceAll(String url) {
        url = url.replaceAll("%3A", ":");
        url = url.replaceAll("%2F", "/");
        url = url.replaceAll("\\+", "%20");
        url = url.replaceAll("%23", "#");
        url = url.replaceAll("%253F%2526", "?");
        url = url.replaceAll("%253D", "=");
        url = url.replaceAll("%3D", "=");
        url = url.replaceAll("%3F", "?");
        return url;
    }

    public static String URLEncoderFileImage(String url){
        if(TextUtils.isEmpty(url)){
            return "";
        }
        String murl =url;
        String enUft = null;
        try {
            if(murl.contains("HTTP")){
                murl =murl.replace("HTTP","http");
            }
            enUft = URLEncoder.encode(murl,"UTF-8");
            String mURL = UiUtils.URLReplaceAll(enUft);
            return mURL;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }


    // 拨打客服电话
    public static void cellPhoneSevers(Context context,String phone){

        String mPhone = "tel:"+ phone;
        Intent intent = new Intent(Intent.ACTION_DIAL);

        //  new Intent(Intent.ACTION_CALL);
        // 这种方式的特点就是，直接拨打了你所输入的号码，所以这种方式对于用户没有直接的提示效果，
        // Android推荐使用第一种方式，如果是第二种的话，建议在之前加一个提示，是否拨打号码，然后确定后再拨打。
        Uri data = Uri.parse(mPhone);
        intent.setData(data);
        context.startActivity(intent);
    }

    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }



    public static boolean isCheckAttestation(UserDetailBean bean){
        if(bean.getStatus()==3||bean.getStatus()==4){
            return true;
        }else {
            return false;
        }
    }



}
