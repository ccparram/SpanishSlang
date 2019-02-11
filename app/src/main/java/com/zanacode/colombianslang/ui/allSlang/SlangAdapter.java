package com.zanacode.colombianslang.ui.allSlang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zanacode.colombianslang.R;
import com.zanacode.colombianslang.data.database.SlangEntry;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SlangAdapter extends RecyclerView.Adapter<SlangAdapter.SlangAdapterViewHolder> {

    private Context context;
    private List<SlangEntry> slangEntries;
    private SlangAdapterOnItemCliclListener slangAdaptarOnItemClickHandler;

    public SlangAdapter(Context context, SlangAdapterOnItemCliclListener listener) {
        this.context = context;
        this.slangAdaptarOnItemClickHandler = listener;
    }

    @NonNull
    @Override
    public SlangAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.slang_card, parent, false);
        view.setFocusable(true);
        return new SlangAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SlangAdapterViewHolder holder, int position) {
        holder.slangTitleTxt.setText(slangEntries.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        if (slangEntries == null) return 0;
        return slangEntries.size();
    }

    public void swapItems(List<SlangEntry> slangEntries){
        this.slangEntries = slangEntries;
        notifyDataSetChanged();
    };

    class SlangAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView slangTitleTxt;

        SlangAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            slangTitleTxt = itemView.findViewById(R.id.slang_card_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            SlangEntry slang = slangEntries.get(getAdapterPosition());
            slangAdaptarOnItemClickHandler.onItemClick(slang.getId(), slang.getTitle());
        }
    }

    public interface SlangAdapterOnItemCliclListener {
        void onItemClick(int slangId, String slangTitle);
    }
}
