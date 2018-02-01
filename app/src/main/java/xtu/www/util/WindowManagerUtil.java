package xtu.www.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.widget.TextView;

import static android.content.Context.ACCESSIBILITY_SERVICE;


/**
 * Created by huilin on 2017/7/26.
 */

public class WindowManagerUtil {

    public static WindowManager mWindowManager;
    public static TextView mTextView;

    private static WindowManager getWindowManager(Context context) {
        if (mWindowManager == null) {
            mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
        return mWindowManager;
    }

    public static void createView(Context context) {
        WindowManager windowManager = getWindowManager(context);
        AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService(ACCESSIBILITY_SERVICE);
        if (mTextView == null && accessibilityManager.isEnabled()) {
            mTextView = new TextView(context);
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.gravity = Gravity.LEFT|Gravity.TOP;
            layoutParams.width = DisplayUtils.dip2px(context,300);
            layoutParams.height = DisplayUtils.dip2px(context,40);
            layoutParams.format = PixelFormat.RGBA_8888;
            layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
            layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                    | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
            mTextView.setBackgroundColor(Color.TRANSPARENT);
            mTextView.setTextColor(Color.RED);
            mTextView.setText("当前类名");
            windowManager.addView(mTextView, layoutParams);

        }
    }

    public static void updateName(String s, Context context) {
        if (mTextView == null) {
            createView(context);
        }
        if(mTextView !=null){
            mTextView.setText(s);
        }
    }

    public static void removeView(Context context){
        if(mTextView !=null ){
            getWindowManager(context).removeView(mTextView);
            mTextView = null;
        }
    }
}
