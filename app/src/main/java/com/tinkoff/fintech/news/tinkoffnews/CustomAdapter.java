package com.tinkoff.fintech.news.tinkoffnews;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tinkoff.fintech.news.tinkoffnews.model.NewsObject;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private List<NewsObject> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.label);
        }
    }

    public CustomAdapter(List<NewsObject> myDataset) {
        Log.d(getClass().getCanonicalName(), myDataset.toString());
        mDataset = myDataset;
    }

    public void addNews(List<NewsObject> newsObjects) {
        mDataset = newsObjects;
        notifyDataSetChanged();
    }

    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_elem, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mDataset.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
