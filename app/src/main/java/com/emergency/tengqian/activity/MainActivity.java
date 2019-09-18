package com.emergency.tengqian.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.emergency.tengqian.ApplicationExtra;
import com.emergency.tengqian.R;
import com.emergency.tengqian.fragment.MineFragment;
import com.emergency.tengqian.fragment.StoreHorseFragment;
import com.emergency.tengqian.fragment.UsualManagerFragment;
import com.emergency.tengqian.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {
    private CoordinatorLayout view_title_bottom;
    private NavigationView navigation_left;
    private boolean isDrawer = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initDrawerView();
        initView();
        initData();
    }

    BottomNavigationView navigation;
    StoreHorseFragment assessmentFragment;
    MineFragment mineFragment;
    UsualManagerFragment usualManagerFragment;
    ViewPager vp_message;

    private void initView() {
        vp_message = findViewById(R.id.vp_message);
        vp_message.setOnPageChangeListener(this);
        vp_message.setOffscreenPageLimit(2);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void initData() {
        List<Fragment> listData = new ArrayList<>();
        listData.add(usualManagerFragment.newInstance("param1", "param2"));
        listData.add(StoreHorseFragment.newInstance("param3", "param4"));
        listData.add(mineFragment.newInstance("param5", "param6"));
        vp_message.setAdapter(new PagerViewAdapter(getSupportFragmentManager(), listData));
    }

    Toolbar toolbar;

    /**
     * toolbar以及drawerlayout初始化和事件绑定
     */
    private void initDrawerView() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.usually_manager);
        setSupportActionBar(toolbar);

        /**
         * 悬浮窗
         * */
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "暂无信息", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        view_title_bottom = findViewById(R.id.view_title_bottom);
        navigation_left = findViewById(R.id.navigation_left);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open, R.string.close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

//        NavigationView navigationView = findViewById(R.id.nav_view);
        navigation_left.setNavigationItemSelectedListener(this);

        view_title_bottom.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (isDrawer) {
                    return navigation_left.dispatchTouchEvent(motionEvent);
                } else {
                    return false;
                }
            }
        });
        drawer.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                isDrawer = true;
                //获取屏幕的宽高
                WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
                Display display = manager.getDefaultDisplay();
                //设置右面的布局位置  根据左面菜单的right作为右面布局的left   左面的right+屏幕的宽度（或者right的宽度这里是相等的）为右面布局的right
                view_title_bottom.layout(navigation_left.getRight(), 0, navigation_left.getRight() + display.getWidth(), display.getHeight());
            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                isDrawer = false;
            }

            @Override
            public void onDrawerStateChanged(int newState) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_camera:
                break;
            case R.id.nav_gallery:
                break;
            case R.id.nav_slideshow:
                break;
            case R.id.nav_manage:
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.usually:
//                    mTextMessage.setText(R.string.usually_manager);
                    toolbar.setTitle(R.string.usually_manager);
                    vp_message.setCurrentItem(0);
                    return true;
                case R.id.storehourse:
                    toolbar.setTitle(R.string.storehourse_manager);
                    vp_message.setCurrentItem(1);
                    return true;
                case R.id.mine:
                    toolbar.setTitle(R.string.me_manager);
                    vp_message.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };

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

    /**
     * viewpager适配器
     */
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

    private long exitTime = 0x0;
    /**
     * 两次点击退出
     */
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
