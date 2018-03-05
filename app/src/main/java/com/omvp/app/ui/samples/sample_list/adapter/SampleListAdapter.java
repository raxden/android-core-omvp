package com.omvp.app.ui.samples.sample_list.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.omvp.app.model.SampleModel;
import com.omvp.components.SampleItemView;
import com.raxdenstudios.recycler.RecyclerAdapter;

public class SampleListAdapter extends RecyclerAdapter<SampleModel, SampleListAdapter.SampleListViewHolder> {

    private AdapterCallback mAdapterCallback;

    public interface AdapterCallback {
        void sampleItemSelected(int position);
    }

    public SampleListAdapter(Context context, AdapterCallback callback) {
        super(context, -1);

        mAdapterCallback = callback;
    }

    @Override
    public SampleListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        SampleItemView sampleItemView = new SampleItemView(parent.getContext());
        sampleItemView.setLayoutParams(params);
        return new SampleListViewHolder(sampleItemView);
    }

    @Override
    public void onBindViewItemHolder(SampleListViewHolder holder, SampleModel data, int position) {
        holder.bindView(data);
    }

    class SampleListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private SampleItemView mItemView;

        public SampleListViewHolder(SampleItemView itemView) {
            super(itemView);

            mItemView = itemView;
            mItemView.setOnClickListener(this);
        }

        public void bindView(SampleModel data) {
            mItemView.setSampleText(data.getTitle());
        }

        @Override
        public void onClick(View v) {
            mAdapterCallback.sampleItemSelected(getAdapterPosition());
        }
    }
}
