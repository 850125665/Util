package xtu.www.util;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.Toast;
/**
 * Created by huilin on 2017/7/26.
 */
public class MainActivity extends AppCompatActivity {

    Button mBtOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permission();
        mBtOpen = (Button) findViewById(R.id.bt_open);
        mBtOpen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                permission();
            }
        });

    }

    private void permission() {
        if (Build.VERSION.SDK_INT >= 23 && !Settings.canDrawOverlays(MainActivity.this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                       Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent,10);
            Toast.makeText(MainActivity.this, "授权窗口显示", Toast.LENGTH_SHORT).show();
        }else {
            showWindow();
        }
    }

    private void showWindow() {
        AccessibilityManager accessibilityManager = (AccessibilityManager) getSystemService(ACCESSIBILITY_SERVICE);
        if (!accessibilityManager.isEnabled()) {
            try {
                //打开系统设置中辅助功能
                Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "查看窗口顶层类", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        WindowManagerUtil.createView(this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 10) {
            if (Build.VERSION.SDK_INT >= 23 && !Settings.canDrawOverlays(this)) {
                Toast.makeText(MainActivity.this,"未授权",Toast.LENGTH_SHORT).show();
            }else{
                showWindow();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WindowManagerUtil.removeView(this);
    }
}
