package com.example.chucknorrisjokes.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.chucknorrisjokes.R;


public class JokesFragment extends Fragment {

    public static final String TITLE = "Jokes";
    private final String BASE_URL = "http://api.icndb.com/jokes/random/";

    private Button button;
    private EditText editText;

    public static JokesFragment getInstance() {
        JokesFragment instance = new JokesFragment();
        instance.setRetainInstance(true);
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.jokes_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
