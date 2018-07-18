package com.test.viewdemo.runtimePermission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.test.viewdemo.R;

public class RuntimePermissionDemoActivity extends AppCompatActivity {

    private final int CALL_PHONE_REQUESTCODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runtime_permission_demo);
    }

    public void applyForPermission(View view) {
        int checkSelfPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if (checkSelfPermission == PackageManager.PERMISSION_GRANTED) {//已经拥有了权限
            Toast.makeText(RuntimePermissionDemoActivity.this, "已经拥有了权限", Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, CALL_PHONE_REQUESTCODE);//动态申请打电话权限
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CALL_PHONE_REQUESTCODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                    Toast.makeText(this, "用户未允许打电话权限", Toast.LENGTH_SHORT).show();

                    //用户是否勾选了 "下次不再提醒选项"
                    boolean b = shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE);
                    if (!b) {//用户勾选了 下次不再提醒选项
                        //用户没同意授权,还不让下次继续提醒授权了,这是比较糟糕的情况
                        Toast.makeText(this, "用户勾选了 下次不再提醒选项", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            default:
                break;
        }

    }
}