package com.khz.madahi.ui.content.adapter;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.khz.madahi.R;
import com.khz.madahi.application.BaseActivity;
import com.khz.madahi.databinding.ContentItem;
import com.khz.madahi.models.Content;
import com.khz.madahi.ui.content.viewmodels.ContentItemViewModel;

import java.util.List;

public class ContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Content>  items;
    private       LayoutInflater layoutInflater;
    BaseActivity activity;

    public ContentAdapter(BaseActivity activity, List<Content> items) {
        this.activity = activity;
        this.items    = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ContentItem itemRow = DataBindingUtil.inflate(layoutInflater, R.layout.item_row_content, parent, false);
        return new ViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final Content        item       = items.get(position);
        ViewHolder           viewHolder = (ViewHolder) holder;
        ContentItemViewModel viewModel  = new ContentItemViewModel(activity, item);
        viewHolder.item.setViewModel(viewModel);

    }

    public void setData(List<Content> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void addContent(Content content) {

        this.items.add(content);
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        ContentItem item;

        ViewHolder(ContentItem item) {
            super(item.getRoot());
            this.item = item;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}