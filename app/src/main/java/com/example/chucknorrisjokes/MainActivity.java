package com.example.chucknorrisjokes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.chucknorrisjokes.Fragments.JokesFragment;
import com.example.chucknorrisjokes.Fragments.WebFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        if (savedInstanceState != null) {
            if (savedInstanceState.getInt("open_fragment") == R.id.web_item) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, WebFragment.getInstance())
                        .commit();
                getSupportActionBar().setTitle(WebFragment.TITLE);
            }
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, JokesFragment.getInstance())
                    .commit();
            getSupportActionBar().setTitle(JokesFragment.TITLE);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("open_fragment", bottomNavigationView.getSelectedItemId());
        super.onSaveInstanceState(outState);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.jokes_item:
                    fragment = new JokesFragment();
                    getSupportActionBar().setTitle(JokesFragment.TITLE);
                    break;
                case R.id.web_item:
                    fragment = WebFragment.getInstance();
                    getSupportActionBar().setTitle(WebFragment.TITLE);
                    break;
            }

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();

            return true;
        }
    };
}