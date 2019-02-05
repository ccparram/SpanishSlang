package com.zanacode.colombianslang.ui.allSlang;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zanacode.colombianslang.R;
import com.zanacode.colombianslang.utilities.Injector;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AllSlangFragment extends Fragment {

    AllSlangFragmentViewModel viewModel;
    @BindView(R.id.recycle_slang)
    RecyclerView recyclerSlang;

    public AllSlangFragment() {
    }

    public static AllSlangFragment newInstance() {
        return new AllSlangFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AllSlangViewModelFactory factory = Injector.provideAllSlangViewModelFactory(getActivity().getApplicationContext());
        viewModel = ViewModelProviders.of(getParentFragment(), factory).get(AllSlangFragmentViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_slang, container, false);

        ButterKnife.bind(this, view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerSlang.setLayoutManager(layoutManager);
        recyclerSlang.hasFixedSize();

        SlangAdapter adapter = new SlangAdapter(getContext());

        viewModel.getSlangEntries().observe(this, slangEntries -> {
            adapter.swapItems(slangEntries);
            recyclerSlang.setAdapter(adapter);
        });

        return view;
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
