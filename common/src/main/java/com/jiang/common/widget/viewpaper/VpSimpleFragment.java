package com.jiang.common.widget.viewpaper;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class VpSimpleFragment extends Fragment {
    private String mTitle;
    private View mView;
    public static final String BUNDLE_TITLE = "title";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = getArguments();

        if (bundle != null) {
            mTitle = bundle.getString(BUNDLE_TITLE);
        }
        return mView;
    }

    public static VpSimpleFragment newInstance(String title, View view) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);

        VpSimpleFragment fragment = new VpSimpleFragment();
        fragment.mView = view;
        fragment.setArguments(bundle);
        return fragment;

    }

    public void notifyDataSetChanged(View view) {
        mView = null;
        mView = view;
    }
}
