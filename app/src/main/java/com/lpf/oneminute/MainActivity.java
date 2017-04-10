package com.lpf.oneminute;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.ikidou.fragmentBackHandler.BackHandlerHelper;
import com.lpf.oneminute.listeners.OnProgressShowListener;
import com.lpf.oneminute.modules.home.FragmentHome;
import com.lpf.oneminute.modules.home.HomePresenter;
import com.lpf.oneminute.modules.login.view.FragmentLoginOrRegister;
import com.lpf.oneminute.modules.recordmoney.FragmentRecordMoney;
import com.lpf.oneminute.modules.recordmoney.FragmentRecordMoneyShow;
import com.lpf.oneminute.modules.recordnote.FragmentRecordNote;
import com.lpf.oneminute.modules.recordnote.FragmentRecordNoteShow;
import com.lpf.oneminute.util.DailyWordUtil;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnProgressShowListener {
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
    @BindView(R.id.main_container)
    FrameLayout mainContainer;
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.content_main)
    FrameLayout contentMain;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    FragmentHome homeFragment = new FragmentHome();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main);
        ButterKnife.bind(this);

        initViews();

        checkPermission();

    }

    // check permission
    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
    }

    private void initViews() {

//        toolbar.setTitle("One Minute");
//        toolbar.setSubtitle("record your life");
//        toolbar.setLogo(R.mipmap.ic_launcher);
//        setSupportActionBar(toolbar);


//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();

        navView.setNavigationItemSelectedListener(this);

        new HomePresenter(MainActivity.this, homeFragment);

//        navView.getMenu().findItem(R.id.nav_login).setChecked(true);
        switchToFragment(FragmentLoginOrRegister.newInstance());    // set current Fragment

        LinearLayout linearLayout = (LinearLayout)navView.getHeaderView(0);
        ((TextView)linearLayout.findViewById(R.id.header_word)).setText(DailyWordUtil.getDayWord());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(!BackHandlerHelper.handleBackPress(this)){
                super.onBackPressed();
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_login) {
            switchToFragment(FragmentLoginOrRegister.newInstance());
        } else if (id == R.id.nav_add_note) {
            switchToFragment(FragmentRecordNote.getInstance());
        } else if (id == R.id.nav_note_show) {
            switchToFragment(FragmentRecordNoteShow.getInstance());
        } else if (id == R.id.nav_add_money) {
            switchToFragment(FragmentRecordMoney.getInstance());
        } else if (id == R.id.nav_money_show) {
            switchToFragment(FragmentRecordMoneyShow.getInstance());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void switchToFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, fragment);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void showDrawer() {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public void hideDrawer() {
        drawerLayout.closeDrawer(GravityCompat.END);
    }


}
