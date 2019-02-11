package com.zanacode.colombianslang.ui.slang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.zanacode.colombianslang.R;
import com.zanacode.colombianslang.ui.allSlang.AllSlangFragment;
import com.zanacode.colombianslang.ui.country.CountryFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SlangFragment extends Fragment {

    private final static int NUMBER_TABS = 2;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.container)
    ViewPager viewPager;

    public static SlangFragment newInstance() {
        SlangFragment fragment = new SlangFragment();
        return fragment;
    }

    //TODO keep open tab after rotation

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_slang, container, false);
        ButterKnife.bind(this, view);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(sectionsPagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        return view;
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return CountryFragment.newInstance();
            } else {
                return AllSlangFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return NUMBER_TABS;
        }
    }
}
