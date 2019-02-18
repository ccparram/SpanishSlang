package com.zanacode.colombianslang.ui.allSlang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zanacode.colombianslang.R;
import com.zanacode.colombianslang.data.database.SlangEntry;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SlangAdapter extends RecyclerView.Adapter<SlangAdapter.SlangAdapterViewHolder> {

    private Context context;
    private List<SlangEntry> slangEntries;
    private OnItemClickListener onItemClickHandler;
    private OnFavoriteIconClickListener onIconClickHandler;

    public SlangAdapter(Context context,
                        OnItemClickListener listener,
                        OnFavoriteIconClickListener iconClickListener) {
        this.context = context;
        this.onItemClickHandler = listener;
        this.onIconClickHandler = iconClickListener;
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
        SlangEntry slang = slangEntries.get(position);
        holder.slangTitleTxt.setText(slang.getTitle());
        int drawableId = slang.isFavorite() ? R.drawable.ic_cupid : R.drawable.ic_cupid_outline;
        holder.slangFavoriteImg.setImageDrawable(context.getResources().getDrawable(drawableId));
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
        ImageView slangFavoriteImg;

        SlangAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            slangTitleTxt = itemView.findViewById(R.id.slang_card_title);
            slangFavoriteImg = itemView.findViewById(R.id.slang_card_img_heart);
            itemView.setOnClickListener(this);
            slangFavoriteImg.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            SlangEntry slang = slangEntries.get(getAdapterPosition());

            if (v.getId() == R.id.slang_card_img_heart) {
                onIconClickHandler.onFavoriteIconClick(slang);
            } else {
                onItemClickHandler.onItemClick(slang);
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(SlangEntry slangEntry);
    }

    public interface OnFavoriteIconClickListener {
        void onFavoriteIconClick(SlangEntry slangEntry);
    }
}
