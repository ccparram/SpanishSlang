package com.zanacode.colombianslang.ui.random;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zanacode.colombianslang.R;

import androidx.fragment.app.Fragment;

public class RandomFragment extends Fragment {

    public RandomFragment() {
    }

    public static RandomFragment newInstance() {
        return new RandomFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_random, container, false);
    }

}
