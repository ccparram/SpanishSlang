package com.zanacode.colombianslang.ui.country;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.zanacode.colombianslang.R;
import com.zanacode.colombianslang.data.database.CountryEntry;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private List<CountryEntry> countries;
    private Context context;
    private final CountryAdapterOnItemClickListener countryAdapterOnItemClickListenerHandler;

    public CountryAdapter(List<CountryEntry> countries, Context context, CountryAdapterOnItemClickListener listener) {
        this.countries = countries;
        this.context = context;
        this.countryAdapterOnItemClickListenerHandler = listener;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.country_card, parent, false);
        view.setFocusable(true);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {

        CountryEntry country = countries.get(position);
        int drawableId = R.drawable.ic_default_flag;

        try {
            drawableId = R.drawable.class.getField("ic_" + country.getCode()).getInt(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        holder.countryImg.setImageDrawable(context.getResources().getDrawable(drawableId));
        holder.countryName.setText(countries.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    class CountryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView countryImg;
        TextView countryName;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            countryImg = itemView.findViewById(R.id.country_card_img);
            countryName = itemView.findViewById(R.id.country_card_country_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            CountryEntry countryEntry = countries.get(getAdapterPosition());
            countryAdapterOnItemClickListenerHandler.onItemClick(countryEntry.getCode(), countryEntry.getName());
        }
    }

    public interface CountryAdapterOnItemClickListener{
        void onItemClick(String code, String name);
    }
}
