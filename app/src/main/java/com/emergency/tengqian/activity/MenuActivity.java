package com.emergency.tengqian.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.emergency.tengqian.ApplicationExtra;
import com.emergency.tengqian.R;
import com.emergency.tengqian.fragment.StoreHorseFragment;
import com.emergency.tengqian.fragment.MineFragment;
import com.emergency.tengqian.fragment.UsualManagerFragment;
import com.emergency.tengqian.util.DialogUtils;
import com.emergency.tengqian.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 老版主界面
 */
public class MenuActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private TextView mTextMessage;
    //默认是1,因为程序启动时首先会连接一个蓝牙
    private int current_pos = 1;

    //hanlder消息标识 message.what
    public static final int MESSAGE_STATE_CHANGE = 1; // 状态改变
    public static final int MESSAGE_READ = 2;          // 读取数据
    public static final int MESSAGE_WRITE = 3;         // 给硬件传数据，暂不需要，看具体需求
    public static final int MESSAGE_DEVICE_NAME = 4;  // 设备名字
    public static final int MESSAGE_TOAST = 5;         // Toast

    private final static int REQUEST_CODE_PERMISSION_LOCATION = 1;
    private final static int REQUEST_CODE_OPEN_GPS = 2;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.usually:
                    mTextMessage.setText(R.string.usually_manager);
                    vp_message.setCurrentItem(0);
                    return true;
                case R.id.storehourse:
                    mTextMessage.setText(R.string.storehourse_manager);
                    vp_message.setCurrentItem(1);
                    return true;
                case R.id.mine:
                    mTextMessage.setText(R.string.me_manager);
                    vp_message.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        checkPermissions();
    }

    BottomNavigationView navigation;
    ImageView iv_back;

    private void initView() {
//        mTextMessage = findViewById(R.id.message);
//        LayoutInflater inflater = LayoutInflater.from(this);
        View view = findViewById(R.id.ll_title);
        mTextMessage = view.findViewById(R.id.tv_title_text);
        iv_back = view.findViewById(R.id.iv_tittle_back);
        iv_back.setOnClickListener(this);
        vp_message = findViewById(R.id.vp_message);
        vp_message.setOnPageChangeListener(this);
        vp_message.setOffscreenPageLimit(2);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    StoreHorseFragment assessmentFragment;
    MineFragment mineFragment;
    UsualManagerFragment usualManagerFragment;
    ViewPager vp_message;


    private void initData() {
        List<Fragment> listData = new ArrayList<>();
        listData.add(usualManagerFragment.newInstance("param1", "param2"));
        listData.add(StoreHorseFragment.newInstance("param3", "param4"));
        listData.add(mineFragment.newInstance("param5", "param6"));
        vp_message.setAdapter(new PagerViewAdapter(getSupportFragmentManager(), listData));
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        navigation.setSelectedItemId(navigation.getMenu().getItem(i).getItemId());
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_tittle_back:
                new DialogUtils(this).showExitDialog();
                break;
        }
    }

    class PagerViewAdapter extends FragmentPagerAdapter {
        List<Fragment> viewData;
        FragmentManager mFragmentManager;

        public PagerViewAdapter(FragmentManager fm, List<Fragment> viewData) {
            super(fm);
            this.viewData = viewData;
            this.mFragmentManager = fm;
        }

        @Override
        public Fragment getItem(int i) {
            return viewData.get(i);
        }

        @Override
        public int getCount() {
            return viewData.size();
        }


        @Override
        public int getItemPosition(@NonNull Object object) {

            if (((Fragment) object).isAdded() || !viewData.contains(object)) {
                return POSITION_NONE;
            }
            return viewData.indexOf(object);
        }

        @Override
        public long getItemId(int position) {

            return position;
        }
    }

    //检测权限
    private void checkPermissions() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!bluetoothAdapter.isEnabled()) {
            ToastUtil.showMessage(this, "请打开蓝牙");
            return;
        }
        if (!checkGPSIsOpen()) {
            ToastUtil.showMessage(this, "请打开GPS");
            return;
        }
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
        List<String> permissionDeniedList = new ArrayList<>();
        for (String permission : permissions) {
            int permissionCheck = ContextCompat.checkSelfPermission(this, permission);
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                onPermissionGranted(permission);
            } else {
                permissionDeniedList.add(permission);
            }
        }
        if (!permissionDeniedList.isEmpty()) {
            String[] deniedPermissions = permissionDeniedList.toArray(new String[permissionDeniedList.size()]);
            ActivityCompat.requestPermissions(this, deniedPermissions, REQUEST_CODE_PERMISSION_LOCATION);
        }
    }

    private void onPermissionGranted(String permission) {
        switch (permission) {
            case Manifest.permission.ACCESS_FINE_LOCATION:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !checkGPSIsOpen()) {
                    new AlertDialog.Builder(this)
                            .setTitle(R.string.notifyTitle)
                            .setMessage(R.string.gpsNotifyMsg)
                            .setNegativeButton(R.string.cancel,
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                        }
                                    })
                            .setPositiveButton(R.string.setting,
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                            startActivityForResult(intent, REQUEST_CODE_OPEN_GPS);
                                        }
                                    })

                            .setCancelable(false)
                            .show();
                } else {
//                    startDiscovery();
                    //dosome thing
                }
                break;
        }
    }

    private boolean checkGPSIsOpen() {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager == null) return false;
        return locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER);
    }

    @Override
    public final void onRequestPermissionsResult(int requestCode,
                                                 @NonNull String[] permissions,
                                                 @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION_LOCATION:
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                            onPermissionGranted(permissions[i]);
                        }
                    }
                }
                break;
        }
    }

    private long exitTime = 0x0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtil.showMessage(getApplicationContext(), "再按一次退出程序");
                exitTime = System.currentTimeMillis();

            } else {
                ApplicationExtra.getInstance().AppExit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
