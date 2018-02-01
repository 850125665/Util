package xtu.www.util;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.Toast;

/**
 * Created by huilin on 2017/7/26.
 */

public class AccessService extends AccessibilityService {
    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Toast.makeText(this, "开启成功", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {

        if (accessibilityEvent.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            Log.e("-------------------", "类名"+accessibilityEvent.getClassName().toString());
            WindowManagerUtil.updateName(accessibilityEvent.getClassName().toString(), getApplicationContext());

        }



    }

    @Override
    public void onInterrupt() {

    }
}
