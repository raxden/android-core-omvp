package com.omvp.app.ui.samples.sample_list.view;

import com.omvp.app.base.mvp.view.BaseView;
import com.omvp.app.model.SampleModel;
import com.omvp.domain.SampleDomain;

import java.util.List;

public interface SampleListView extends BaseView {
    void drawSampleList(List<SampleModel> sampleModelList);

    void showEmptyView();

    void onSampleItemSelected(SampleDomain sampleDomain);
}
