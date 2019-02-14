package com.zanacode.colombianslang.ui.slandDetail;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.zanacode.colombianslang.R;
import com.zanacode.colombianslang.utilities.Injector;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SlangDetailFragment extends DialogFragment {

    public static final String TAG = "DetailDialog";

    public static final String ARG_SLANG_ID = "1";
    public static final String ARG_SLANG_TITLE = "2";

    @BindView(R.id.slang_detail_recycler)
    RecyclerView recycler;
    @BindView(R.id.slang_detail_txt_title)
    TextView slangDetailTxtTitle;

    private SlangDetailFragmentViewModel viewModel;
    private int slangId;
    private String slangTitle;

    public SlangDetailFragment() {
    }

    public static SlangDetailFragment newInstance(int slangId, String slangTitle) {
        Bundle args = new Bundle();
        args.putInt(ARG_SLANG_ID, slangId);
        args.putString(ARG_SLANG_TITLE, slangTitle);
        SlangDetailFragment fragment = new SlangDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slang_detail, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        if (getArguments() != null) {
            slangId = getArguments().getInt(ARG_SLANG_ID);
            slangTitle = getArguments().getString(ARG_SLANG_TITLE);
        }

        ButterKnife.bind(this, view);
        slangDetailTxtTitle.setText(slangTitle);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        recycler.hasFixedSize();

        SlangDetailViewModelFactory factory = Injector.provideSlangDetailViewModelFactory(getActivity().getApplicationContext());
        viewModel = ViewModelProviders.of(this, factory).get(SlangDetailFragmentViewModel.class);
        viewModel.setCurrentSlangId(slangId);

        viewModel.getCurrentMeaningsCountryJoin().observe(this, currentMeaningsCountryJoin -> {
            SlangDetailAdapter adapter = new SlangDetailAdapter(currentMeaningsCountryJoin, getContext());
            recycler.setAdapter(adapter);
        });
        return view;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}

