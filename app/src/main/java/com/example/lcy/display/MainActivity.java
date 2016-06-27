package com.example.lcy.display;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void get(View view) {
        Display display = getWindowManager().getDefaultDisplay();
        System.out.println(display.getName());//内置屏幕

        Point point = new Point();
        display.getSize(point);
        System.out.println(""+point);//Point(900, 1392)

        point = new Point();
        display.getRealSize(point);
        System.out.println(point);//Point(900, 1440)

        int width = display.getWidth();
        int height = display.getHeight();
        System.out.println(width+"|"+height);//900|1392

        //方法2
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        System.out.println("density="+dm.density);//density = dpi/160
        System.out.println("scaledDensity="+dm.scaledDensity);//1.0
        //density和scaledDensity的区别在于
        //density：The logical density of the display.显示密度density = dpi/160
        //scaledDensity：A scaling factor for fonts displayed on the display.

        System.out.println("dm.densityDpi="+dm.densityDpi);//160
        System.out.println("dm.heightPixels="+dm.heightPixels);
        System.out.println("dm.widthPixels="+dm.widthPixels);
        System.out.println("xdpi="+dm.xdpi);//xdpi:即水平方向每英寸中的像素数,
        System.out.println("ydpi="+dm.ydpi);//ydpi:即垂直方向每英寸的像素数.
        System.out.println("------------------------");
        System.out.println(dp2Px(2));
        System.out.println(px2dp(4));

    }

    private int dp2Px(int dpVal){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dpVal,getResources().getDisplayMetrics());
    }

    private int sp2Px(int pxVal){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,pxVal,getResources().getDisplayMetrics());
    }

    private float px2dp(int pxVal){
        final float scale = getResources().getDisplayMetrics().density;
        return (pxVal/scale);
    }

    public static float px2sp(Context context, float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }
}
