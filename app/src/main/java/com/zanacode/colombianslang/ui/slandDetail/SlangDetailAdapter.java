package com.zanacode.colombianslang.ui.slandDetail;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zanacode.colombianslang.R;
import com.zanacode.colombianslang.data.database.MeaningEntry;
import com.zanacode.colombianslang.data.database.MeaningsCountryJoin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.recyclerview.widget.RecyclerView;

public class SlangDetailAdapter  extends RecyclerView.Adapter<SlangDetailAdapter.SlangDetailViewHolder> {

    private Context context;
    Map<String, List<MeaningsCountryJoin>> meaningsCountryJoinByCountry = new HashMap<>();
    private String[] countryNames;


    SlangDetailAdapter(List<MeaningsCountryJoin> meaningsCountryJoin, Context context) {
        this.context = context;

        for (MeaningsCountryJoin meaningsCountryJoinItem: meaningsCountryJoin) {
            String countryName = meaningsCountryJoinItem.getCountryName();

            if (!meaningsCountryJoinByCountry.containsKey(countryName)) {
                meaningsCountryJoinByCountry.put(countryName, new ArrayList<>());
            }
            meaningsCountryJoinByCountry.get(countryName).add(meaningsCountryJoinItem);
        }
        countryNames = meaningsCountryJoinByCountry.keySet().toArray(new String[0]);
    }

    @NonNull
    @Override
    public SlangDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.country_detail_card, parent, false);
        view.setFocusable(true);
        return new SlangDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SlangDetailViewHolder holder, int position) {
        String countryName = countryNames[position];

        List<MeaningsCountryJoin> meaningsCountryJoin = meaningsCountryJoinByCountry.get(countryName);
        int drawableId = R.drawable.ic_default_flag;

        try {
            drawableId = R.drawable.class.getField("ic_" + meaningsCountryJoin.get(0).getCountryCode()).
                    getInt(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


        Resources res = context.getResources();
        Bitmap src = BitmapFactory.decodeResource(res, drawableId);
        RoundedBitmapDrawable dr = RoundedBitmapDrawableFactory.create(res, src);
        dr.setCornerRadius(Math.max(src.getWidth(), src.getHeight()) / 2.0f);

        holder.countryImg.setImageDrawable(dr);
        holder.countryName.setText(countryName);

        addMeaningToLinearLayout(holder.parentLayout, meaningsCountryJoin);
    }

    private void addMeaningToLinearLayout(LinearLayout parentLayout, List<MeaningsCountryJoin> meanings) {

        parentLayout.removeAllViews();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view;

        for (MeaningsCountryJoin meaningCountryJoin : meanings) {
            MeaningEntry meaningEntry = meaningCountryJoin.getMeaning();

            view = layoutInflater.inflate(R.layout.country_detail_nested_description, parentLayout, false);
            TextView description = view.findViewById(R.id.country_detail_nested_description);
            TextView example = view.findViewById(R.id.country_destail_nested_example);

            description.setText(meaningEntry.getDescription());
            example.setText(meaningEntry.getExample());
            parentLayout.addView(view);
        }
    }

    @Override
    public int getItemCount() {
        return meaningsCountryJoinByCountry.size();
    }

    class SlangDetailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView countryImg;
        TextView countryName;
        LinearLayout parentLayout;

        public SlangDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            countryName = itemView.findViewById(R.id.country_detail_card_name);
            countryImg = itemView.findViewById(R.id.country_detail_img_flag);
            parentLayout = itemView.findViewById(R.id.country_detail_container_meanings);
        }

        @Override
        public void onClick(View v) {
        }
    }
}
