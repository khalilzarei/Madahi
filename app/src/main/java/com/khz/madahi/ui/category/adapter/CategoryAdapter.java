package com.khz.madahi.ui.category.adapter;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.khz.madahi.R;
import com.khz.madahi.application.BaseActivity;
import com.khz.madahi.databinding.CategoryItem;
import com.khz.madahi.models.Category;
import com.khz.madahi.ui.category.CategoryActivity;
import com.khz.madahi.ui.category.viewmodels.CategoryItemViewModel;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Category> items;
    private       LayoutInflater layoutInflater;
    BaseActivity activity;

    public CategoryAdapter(CategoryActivity activity, List<Category> items) {
        this.activity = activity;
        this.items    = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        CategoryItem itemRow = DataBindingUtil.inflate(layoutInflater, R.layout.item_row_category, parent, false);
        return new ViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final Category        item       = items.get(position);
        ViewHolder            viewHolder = (ViewHolder) holder;
        CategoryItemViewModel viewModel  = new CategoryItemViewModel(activity, item);
        viewHolder.item.setViewModel(viewModel);

    }

    public void setData(List<Category> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        CategoryItem item;

        ViewHolder(CategoryItem item) {
            super(item.getRoot());
            this.item = item;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}