package com.zanacode.colombianslang.ui.slang;

import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zanacode.colombianslang.R;
import com.zanacode.colombianslang.ui.allSlang.AllSlangFragment;
import com.zanacode.colombianslang.ui.country.CountryFragment;

public class SlangFragment extends Fragment {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private final static int NUMBER_TABS = 2;

    public static SlangFragment newInstance() {
        SlangFragment fragment = new SlangFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
    ViewGroup container,
    @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_slang, container, false);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());

        mViewPager = view.findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        return view;
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
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
