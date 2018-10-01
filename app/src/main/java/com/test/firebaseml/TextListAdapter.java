package com.test.firebaseml;

import android.content.pm.LabeledIntent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Arunraj on 10/1/2018
 */
public class TextListAdapter extends RecyclerView.Adapter<TextListAdapter.TextViewHolder>{

    List<String> mList;

    public TextListAdapter(List<String> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public TextViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TextViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_text, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TextViewHolder holder, int position) {
        holder.txtItem.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder {

        TextView txtItem;

        public TextViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItem = itemView.findViewById(R.id.txtItem);
        }
    }

}
