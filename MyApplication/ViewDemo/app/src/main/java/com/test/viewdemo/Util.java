package com.test.viewdemo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Random;


/**
 * Created by xyb on 2015/11/10.
 */
public class Util {
    public static String dateFormat(Date date) {
        SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = dateformat1.format(date);
        return dateStr;
    }

    /**
     * 将表示十六进制的字符串格式化为偶数个，并去掉空格
     * 如ef f->ef0f
     *
     * @param str
     * @return
     */
    public static StringBuffer hexStrFormat(String str) {
        StringBuffer sb = new StringBuffer(str.replace(" ", ""));//去空格
        int length = sb.length();
        if (length % 2 != 0) {
            sb.insert(sb.length() - 1, "0");

        }
        return sb;
    }

    /**
     * 将十六进制字符串每隔连个加上空格,用于展示在TextView中
     * efeffe0f->ef ef fe 0f
     *
     * @param sb
     * @return
     */
    public static String showHexStr(StringBuffer sb) {
        int count = 0;
        for (int i = 0; i < sb.length(); i++) {
            count++;
            if (count == 3) {
                sb.insert(i, " ");
                count = 0;
            }
        }
        return sb.toString();
    }


    public static int getWindowHeight(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        WindowManager ww = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        ww.getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        int height = metric.heightPixels;   // 屏幕高度（像素）
        float density = metric.density;      // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）
        return height;
    }


    public static int getWindowWidth(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        WindowManager ww = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        ww.getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        return width;

    }

    public static int outScreenInfo(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        WindowManager ww = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        ww.getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;  // 屏幕宽度（像素）
        int height = metric.heightPixels;  // 屏幕高度（像素）
        float density = metric.density;  // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）
        Log.i("screenInfo" + "width", width + "");
        Log.i("screenInfo" + "height", height + "");
        Log.i("screenInfo" + "density", density + "");
        Log.i("screenInfo" + "densityDpi", densityDpi + "");
        return densityDpi;
    }


    /**
     * 获取id地址
     */




    /**
     * 获取使用GPRSIP地址
     *
     * @return
     */
    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e("WifiPreference IpAddress", ex.toString());
        }
        return null;
    }

    private static String intToIp(int i) {

        return (i & 0xFF) + "." +
                ((i >> 8) & 0xFF) + "." +
                ((i >> 16) & 0xFF) + "." +
                (i >> 24 & 0xFF);
    }

    public static String getCurrentTime() {
        String timeStr = null;
        Date date = new Date();
        int hour = date.getHours();
        int minut = date.getMinutes();
        timeStr = hour + ":" + minut;
        return timeStr;
    }

    /**
     * 获取app最大内存 单位byte
     *
     * @return
     */
    public static long getMaxMemory() {
        return Runtime.getRuntime().maxMemory();
    }

    /**
     * 精确两位小数
     *
     * @param price
     */
    public static void preciseTow(float price) {
        DecimalFormat decimalFormat = new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        String p = decimalFormat.format(price);//format 返回的是字符串
    }

    public static void webViewNormalSetting(WebView webview_present, String cachePath) {
        webview_present.getSettings().setJavaScriptEnabled(true);
        webview_present.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webview_present.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);  //设置 缓存模式
        // 开启 DOM storage API 功能
        webview_present.getSettings().setDomStorageEnabled(true);
        //开启 database storage API 功能
        webview_present.getSettings().setDatabaseEnabled(true);
        String cacheDirPath = Environment.getDownloadCacheDirectory() + cachePath;
        webview_present.getSettings().setDatabasePath(cacheDirPath);
        //设置  Application Caches 缓存目录
        webview_present.getSettings().setAppCachePath(cacheDirPath);
        //开启 Application Caches 功能
        webview_present.getSettings().setAppCacheEnabled(true);
        webview_present.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }

    public static int sp_to_px(float DIP, Context context) {
     /*   final float scale = context.getResources().getDisplayMetrics().density;
        int pixel = (int) (DIP * scale + 0.5f);*/
        // float fDip = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, DIP, context.getResources().getDisplayMetrics());

        float px = DIP * (context.getResources().getDisplayMetrics().densityDpi) / 160;
        return (int) px;
    }

    public static boolean floatIsZero(float x) {

        return true;
    }



    @TargetApi(19)

    private static void setTranslucentStatus(Activity activity, boolean on) {

        Window win = activity.getWindow();

        WindowManager.LayoutParams winParams = win.getAttributes();

        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;

        if (on) {

            winParams.flags |= bits;

        } else {

            winParams.flags &= ~bits;

        }

        win.setAttributes(winParams);

    }



    public static int getSDKVersion() {
        return android.os.Build.VERSION.SDK_INT;
    }

    /**
     * 生产水机字符串
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String getTimeStamp() {
        return System.currentTimeMillis() + "";
    }

    public static String getFromAssets(String fileName, Context context) {
        try {
            InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void clearWebView(String TAG, WebView webView) {
        Log.d(TAG, "Clear webview's resources");
        webView.removeAllViews();
        ((ViewGroup) webView.getParent()).removeView(webView);
        webView.setTag(null);
        webView.clearHistory();
        webView.destroy();
        webView = null;
    }

    /**
     * 设置高度为整个屏幕的几分之几
     * @param view
     * @param hr,wr 高宽比
     */
    public static  void setHeightWithScreenWith(View view, int hr, int wr, Context context){
        LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) view.getLayoutParams();
        layoutParams.height= (int) (Util.getWindowWidth(context)*hr/wr);
        view.setLayoutParams(layoutParams);
    }

    /**
     * 设置高度
     * @param view
     * @param hr,wr 高宽比
     */
    public static  void setWH(View view, int wr, int hr, Context context){
        ViewGroup.LayoutParams layoutParams= (ViewGroup.LayoutParams) view.getLayoutParams();
        layoutParams.width=wr;
        layoutParams.height= hr;
        view.setLayoutParams(layoutParams);
    }

    /**
     * 设置宽占totalWidth的比例
     * totalWidth默认为屏幕宽
     */



    public static void setWidthInTotal(View view, Context context, float ratio){
        setWidthInTotal(view,context,ratio,0);
    }

    public static void setWidthInTotal(View view, Context context, float ratio, int moreRedundance){
        setWidthInTotal(view,context,ratio,moreRedundance);
    }

    public static void setWidthInTotal(View view, Context context, float ratio, int totalWidth, int moreRedundance){
        totalWidth=totalWidth==0?getWindowWidth(context):totalWidth;
        ViewGroup.LayoutParams layoutParams= view.getLayoutParams();
        layoutParams.width= (int) ((totalWidth-moreRedundance)*ratio);
        view.setLayoutParams(layoutParams);
    }


}
