package lib.common.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import lib.common.CAPP;

public class UtilScreen {
    private  static  UtilScreen instance;
    public static UtilScreen getInstance(){
        if (null==instance){
            instance=new UtilScreen();
        }
        return instance;
    }

    //获取屏幕的宽度
    public  int getScreenWidth(){
        DisplayMetrics displayMetrics= CAPP.getInstance().getApplication().getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }
    //获取屏幕的高度
    public  int getScreenHeight(){
        DisplayMetrics displayMetrics= CAPP.getInstance().getApplication().getResources().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }
    //获取屏幕的密度
    public  float getScreenDensity(){
        DisplayMetrics displayMetrics= CAPP.getInstance().getApplication().getResources().getDisplayMetrics();
        return displayMetrics.density;
    }
    /**
     * 获取当前屏幕截图，包含状态栏
     * @return
     */
    public  Bitmap snapShotWithStatusBar(){
        View view = UtilActivity.getInstance().getActivity().getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int width =getScreenWidth();
        int height = getScreenHeight();
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;

    }

    /**
     * 获取当前屏幕截图，不包含状态栏
     * @return
     */
    public Bitmap snapShotWithoutStatusBar() {
        View view = UtilActivity.getInstance().getActivity().getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Rect frame = new Rect();
        UtilActivity.getInstance().getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        int width = getScreenWidth();
        int height = getScreenHeight();
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height
                - statusBarHeight);
        view.destroyDrawingCache();
        return bp;
    }
    /**
     * 得到设备的密度
     */
    public float getScreenDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }
    /**
     * dp转px
     * @param dpVal
     * @return
     */
    public  int dp2px(float dpVal){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, CAPP.getInstance().getApplication().getApplicationContext().getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     * @param pxVal
     * @return
     */
    public  float px2dp(float pxVal){
        final float scale = CAPP.getInstance().getApplication().getApplicationContext().getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }
    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public  int sp2px(float spVal){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, CAPP.getInstance().getApplication().getResources().getDisplayMetrics());
    }
    /**
     * 把密度转换为像素
     */
    public int dip2px(Context context, float px) {
        final float scale = getScreenDensity(context);
        return (int) (px * scale + 0.5);
    }


    public  int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public int px2sp( float pxValue) {
        final float fontScale = CAPP.getInstance().getApplication().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

}
