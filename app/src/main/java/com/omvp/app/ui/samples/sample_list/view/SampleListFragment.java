package com.omvp.app.ui.samples.sample_list.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.omvp.app.R;
import com.omvp.app.base.mvp.view.BaseViewFragment;
import com.omvp.app.base.mvp.view.BaseViewFragmentCallback;
import com.omvp.app.model.SampleModel;
import com.omvp.app.ui.samples.sample_list.adapter.SampleListAdapter;
import com.omvp.app.ui.samples.sample_list.presenter.SampleListPresenter;
import com.omvp.domain.SampleDomain;

import java.util.List;

import butterknife.BindView;

public class SampleListFragment extends BaseViewFragment<SampleListPresenter, SampleListFragment.FragmentCallback>
        implements SampleListView {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.empty_view)
    View mEmptyView;

    private SampleListAdapter mAdapter;

    public interface FragmentCallback extends BaseViewFragmentCallback {
        void onSampleItemSelected(SampleDomain sampleDomain);
    }

    public static SampleListFragment newInstance(Bundle bundle) {
        SampleListFragment fragment = new SampleListFragment();
        bundle = bundle == null ? new Bundle() : bundle;
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        setupViews();
    }

    @Override
    public void drawSampleList(List<SampleModel> sampleModelList) {
        mAdapter.setItems(sampleModelList);

        mEmptyView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyView() {
        mEmptyView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onSampleItemSelected(SampleDomain sampleDomain) {
        mCallback.onSampleItemSelected(sampleDomain);
    }

    private void setupViews() {
        mAdapter = new SampleListAdapter(
                getActivity(),
                (SampleListAdapter.AdapterCallback) mPresenter
        );
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
    }
}
