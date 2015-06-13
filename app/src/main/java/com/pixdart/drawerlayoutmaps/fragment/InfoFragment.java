package com.pixdart.drawerlayoutmaps.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pixdart.drawerlayoutmaps.R;

/**
 * Copyright (C) Drawer Layout And Maps Sample
 *
 * @author Gerónimo Oñativia <geronimox@gmail.com>
 */
public class InfoFragment extends Fragment {
    private static final String TAG = InfoFragment.class.getSimpleName();
    private String someArgument;
    private TextView textView;

    public static InfoFragment newInstance(String someArgument) {
        Log.d(TAG, "Instancing...");
        InfoFragment fragment = new InfoFragment();
        //Example of passing arguments to created fragment
        Bundle bundle = new Bundle();
        bundle.putString("ARG1", someArgument);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey("ARG1"))
            someArgument = getArguments().getString("ARG1");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.info_fragment, container, false);
        textView = (TextView) rootView.findViewById(R.id.text);

        if (someArgument != null) {
            textView.setText(someArgument);
        }

        return rootView;
    }
}
