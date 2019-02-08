package com.zanacode.colombianslang.ui.slandDetail;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zanacode.colombianslang.R;

import androidx.fragment.app.Fragment;

public class SlangDetailFragment extends Fragment {


    public SlangDetailFragment() {
    }

    public static SlangDetailFragment newInstance() {
        return new SlangDetailFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_slang_detail, container, false);
    }

}
