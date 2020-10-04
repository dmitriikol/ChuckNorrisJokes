package com.example.chucknorrisjokes.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.chucknorrisjokes.R;


public class WebFragment extends Fragment {

    public static final String TITLE = "Api info";
    private final String URL = "http://www.icndb.com/api/";

    public static WebFragment getInstance() {
        WebFragment instance = new WebFragment();
        instance.setRetainInstance(true);
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.web_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WebView webView = (WebView) view.findViewById(R.id.web_view);
        webView.loadUrl(URL);
    }
}