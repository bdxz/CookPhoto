package com.example.morphtin.dishes.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.ui.fragment.MainFragment;
import com.example.morphtin.dishes.util.PermissionsChecker;

import me.yokeyword.fragmentation.SupportActivity;

public class MainActivity extends SupportActivity {
    private static final int REQUEST_CODE = 0; // 请求码

    // 所需的全部权限
    private static final String[] PERMISSIONS = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private PermissionsChecker mPermissionsChecker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fragment_container, MainFragment.newInstance());
        }

        mPermissionsChecker = new PermissionsChecker(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 缺少权限时, 进入权限配置页面
        if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
            PermissionsActivity.startActivityForResult(this, REQUEST_CODE, PERMISSIONS);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            finish();
        }
    }
}
