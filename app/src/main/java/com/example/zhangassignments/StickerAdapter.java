package com.example.zhangassignments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StickerAdapter extends RecyclerView.Adapter<StickerAdapter.StickerViewHolder> {

    private Context context;
    private List<String> msgs;

    public StickerAdapter(Context context, List<String> msgs) {
        this.context = context;
        this.msgs = msgs;
    }

    @Override
    public StickerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sticker, parent, false);
        return new StickerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StickerViewHolder holder, int position) {
        String msg = msgs.get(position);

        if(msg.equals("1")){
            holder.imageView.setImageDrawable(context.getDrawable(R.drawable.img1));
        }else if(msg.equals("2")){
            holder.imageView.setImageDrawable(context.getDrawable(R.drawable.img2));
        }else if(msg.equals("3")){
            holder.imageView.setImageDrawable(context.getDrawable(R.drawable.img3));
        }else if(msg.equals("4")){
            holder.imageView.setImageDrawable(context.getDrawable(R.drawable.img4));
        }else if(msg.equals("5")){
            holder.imageView.setImageDrawable(context.getDrawable(R.drawable.img5));
        }else if(msg.equals("6")){
            holder.imageView.setImageDrawable(context.getDrawable(R.drawable.img6));
        }

    }

    @Override
    public int getItemCount() {
        return msgs.size();
    }

    public static class StickerViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public StickerViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_iv_steaker);
        }
    }
}
