package com.zanacode.colombianslang.ui.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.zanacode.colombianslang.R;
import com.zanacode.colombianslang.ui.about.AboutFragment;
import com.zanacode.colombianslang.ui.favorite.FavoriteFragment;
import com.zanacode.colombianslang.ui.random.RandomFragment;
import com.zanacode.colombianslang.ui.slang.SlangFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        bottomNavigationView.setOnNavigationItemSelectedListener((@NonNull MenuItem menuItem) ->{
            navigate(menuItem);
            return true;
        });
        bottomNavigationView.setOnNavigationItemReselectedListener((@NonNull MenuItem menuItem) -> {

        });
        navigate(bottomNavigationView.getMenu().getItem(0));
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

    private void navigate(@NonNull MenuItem menuItem) {

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
