package com.zanacode.colombianslang.ui.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.zanacode.colombianslang.R;
import com.zanacode.colombianslang.ui.allSlang.AllSlangFragment;
import com.zanacode.colombianslang.ui.slandDetail.SlangDetailFragment;
import com.zanacode.colombianslang.ui.slang.SlangFragment;
import com.zanacode.colombianslang.utilities.Injector;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import br.com.mauker.materialsearchview.MaterialSearchView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.search_view)
    MaterialSearchView searchView;

    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        MainViewModelFactory factory = Injector.provideMainViewModelFactory(getApplicationContext());
        viewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel.class);

        searchView.setOnItemClickListener((parent, view, position, id) -> {
            String suggestion = searchView.getSuggestionAtPosition(position);
            searchView.setQuery(suggestion, false);
            showSlangDetail(suggestion);
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                int suggestionsLength = searchView.getAdapter().getCount();

                if (suggestionsLength == 0) return true;
                if (suggestionsLength == 1) showSlangDetail(searchView.getSuggestionAtPosition(0));
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewOpened() {

            }

            @Override
            public void onSearchViewClosed() {
                bottomNavigationView.setVisibility(View.VISIBLE);
            }
        });

        viewModel.getSlangSuggestions().observe(this, slangSuggestions -> {
            searchView.addSuggestions(slangSuggestions);
        });

        bottomNavigationView.setOnNavigationItemSelectedListener((@NonNull MenuItem menuItem) -> {
            navigate(menuItem);
            return true;
        });
        bottomNavigationView.setOnNavigationItemReselectedListener((@NonNull MenuItem menuItem) -> {

        });
        navigate(bottomNavigationView.getMenu().getItem(0));
    }

    private void showSlangDetail(String slangTitle) {
        viewModel.getSlangIdByTitle(slangTitle).observe(this, slangId -> {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            Fragment prev = getSupportFragmentManager().findFragmentByTag(SlangDetailFragment.TAG);
            if (prev != null) {
                ft.remove(prev);
            }
            ft.addToBackStack(null);

            SlangDetailFragment newFragment = SlangDetailFragment.newInstance(slangId, slangTitle);
            newFragment.show(ft, SlangDetailFragment.TAG);
        });
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
        if (id == R.id.action_search) {
            searchView.openSearch();
            bottomNavigationView.setVisibility(View.GONE);
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
                navFragment = AllSlangFragment.newInstanceFavorites();
                break;
//            case R.id.nav_random:
//                setTitle(menuItem.getTitle());
//                menuItem.setChecked(true);
//                navFragment = RandomFragment.newInstance();
//                break;
        }

        if (navFragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            transaction.replace(R.id.main_content_frame, navFragment).commitAllowingStateLoss();
        }
    }

    @Override
    public void onBackPressed() {
        if (bottomNavigationView.getSelectedItemId() != R.id.nav_slang && !searchView.isOpen()) {
            navigate(bottomNavigationView.getMenu().getItem(0));
            return;
        }
        if (searchView.isOpen()) {
            // Close the search on the back button press.
            searchView.closeSearch();
            bottomNavigationView.setVisibility(View.VISIBLE);
        } else {
            super.onBackPressed();
        }
    }
}
