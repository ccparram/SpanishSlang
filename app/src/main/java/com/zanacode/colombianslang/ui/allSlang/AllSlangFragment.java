package com.zanacode.colombianslang.ui.allSlang;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zanacode.colombianslang.R;
import com.zanacode.colombianslang.data.database.SlangEntry;
import com.zanacode.colombianslang.ui.slandDetail.SlangDetailFragment;
import com.zanacode.colombianslang.utilities.Injector;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AllSlangFragment extends Fragment implements
        SlangAdapter.OnItemClickListener,
        SlangAdapter.OnFavoriteIconClickListener {

    public static final String TAG = "AllSlangFragment";
    public static final String ARG_COUNTRY_CODE = "1";
    public static final String ARG_COUNTRY_NAME = "2";
    @BindView(R.id.country_card_img)
    ImageView countryCardImg;
    @BindView(R.id.country_card_country_name)
    TextView countryCardCountryName;

    private String countryCode;
    private String countryName;
    private boolean isViewByContry;

    private AllSlangFragmentViewModel viewModel;
    @BindView(R.id.recycle_slang)
    RecyclerView recyclerSlang;

    public AllSlangFragment() {
    }

    public static AllSlangFragment newInstance(String code, String name) {
        Bundle args = new Bundle();
        args.putString(ARG_COUNTRY_CODE, code);
        args.putString(ARG_COUNTRY_NAME, name);
        AllSlangFragment fragment = new AllSlangFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static AllSlangFragment newInstance() {
        return new AllSlangFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AllSlangViewModelFactory factory = Injector.provideAllSlangViewModelFactory(getActivity().getApplicationContext());
        viewModel = ViewModelProviders.of(this, factory).get(AllSlangFragmentViewModel.class);

        if (getArguments() != null) {
            countryCode = getArguments().getString(ARG_COUNTRY_CODE);
            countryName = getArguments().getString(ARG_COUNTRY_NAME);
            isViewByContry = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_slang, container, false);

        ButterKnife.bind(this, view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerSlang.setLayoutManager(layoutManager);
        recyclerSlang.hasFixedSize();

        SlangAdapter adapter = new SlangAdapter(getContext(), this, this);


        if (isViewByContry) {
            countryCardCountryName.setText(countryName);

            int drawableId = R.drawable.ic_default_flag;

            try {
                drawableId = R.drawable.class.getField("ic_" + countryCode).getInt(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }

            countryCardImg.setImageDrawable(getResources().getDrawable(drawableId));

            viewModel.getSlangEntriesByCountry(countryCode).observe(this, slangEntries -> {
                adapter.swapItems(slangEntries);
                recyclerSlang.setAdapter(adapter);
            });
        } else {
            viewModel.getSlangEntries().observe(this, slangEntries -> {
                adapter.swapItems(slangEntries);
                recyclerSlang.setAdapter(adapter);
            });
        }
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

    @Override
    public void onItemClick(SlangEntry slangEntry) {
        showSlangDetail(slangEntry);
    }

    private void showSlangDetail(SlangEntry slangEntry) {

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag(SlangDetailFragment.TAG);
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        SlangDetailFragment fragment = SlangDetailFragment.newInstance(slangEntry.getId(),
                                                                        slangEntry.getTitle());
        fragment.show(ft, SlangDetailFragment.TAG);
    }

    @Override
    public void onFavoriteIconClick(SlangEntry slangEntry) {
        slangEntry.setIsFavorite(!slangEntry.isFavorite());
        viewModel.updateSlang(slangEntry);
    }
}
