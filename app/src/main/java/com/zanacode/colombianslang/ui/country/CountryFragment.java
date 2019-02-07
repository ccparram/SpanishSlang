package com.zanacode.colombianslang.ui.country;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.zanacode.colombianslang.R;
import com.zanacode.colombianslang.utilities.Injector;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CountryFragment extends Fragment {

    @BindView(R.id.country_recycler)
    RecyclerView recycler;

    private CountryFragmentViewModel viewModel;

    public CountryFragment() {
    }

    public static CountryFragment newInstance() {
        return new CountryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_country, container, false);

        ButterKnife.bind(this, view);

        CountryViewModelFactory factory = Injector.provideCountryViewModelFactory(getActivity().getApplicationContext());
        viewModel = ViewModelProviders.of(getParentFragment(), factory).get(CountryFragmentViewModel.class);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        recycler.hasFixedSize();

        viewModel.getSlangEntries().observe(this, countriesEntries -> {
            CountryAdapter adapter = new CountryAdapter(countriesEntries, getContext());
            recycler.setAdapter(adapter);
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
