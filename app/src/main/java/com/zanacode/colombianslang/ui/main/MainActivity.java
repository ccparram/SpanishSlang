package com.zanacode.colombianslang.ui.main;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.zanacode.colombianslang.R;
import com.zanacode.colombianslang.ui.about.AboutFragment;
import com.zanacode.colombianslang.ui.favorite.FavoriteFragment;
import com.zanacode.colombianslang.ui.random.RandomFragment;
import com.zanacode.colombianslang.ui.slang.SlangFragment;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private final Handler drawerHandler = new Handler();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        setupDrawerContent(navView);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {

        navigationView.setNavigationItemSelectedListener((final MenuItem menuItem) -> {

            drawerHandler.removeCallbacksAndMessages(null);
            drawerHandler.postDelayed(() -> {
                navigate(menuItem);
                }, 250);
            drawerLayout.closeDrawers();
                    return true;
        });
    }

    private void navigate(final MenuItem menuItem) {
        Fragment navFragment = null;

        switch (menuItem.getItemId()) {
            case R.id.nav_slang:
                setTitle(menuItem.getTitle());
                menuItem.setChecked(true);
                navFragment = SlangFragment.newInstance();
                break;
            case R.id.nav_favorite:
                setTitle(menuItem.getTitle());
                menuItem.setChecked(true);
                navFragment = FavoriteFragment.newInstance();
                break;
            case R.id.nav_random:
                setTitle(menuItem.getTitle());
                menuItem.setChecked(true);
                navFragment = RandomFragment.newInstance();
                break;
            case R.id.nav_about:
                setTitle(menuItem.getTitle());
                menuItem.setChecked(true);
                navFragment = AboutFragment.newInstance();
                break;
        }

        if (navFragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            transaction.replace(R.id.main_content_frame, navFragment).commitAllowingStateLoss();
        }
    }
}
