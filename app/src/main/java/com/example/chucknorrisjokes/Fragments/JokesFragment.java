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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.chucknorrisjokes.Adapter.JokesRvAdapter;
import com.example.chucknorrisjokes.Model.Joke;
import com.example.chucknorrisjokes.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class JokesFragment extends Fragment {

    ArrayList<Joke> jokes;
    JokesRvAdapter adapter;

    public static final String TITLE = "Jokes";
    private final String BASE_URL = "http://api.icndb.com/jokes/random/";
    private final String LOG_TAG = "ChuckJokesLogs";

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

        RecyclerView rvJokes = view.findViewById(R.id.rvJokes);

        // Init jokes
        jokes = Joke.getJokes(20);
        adapter = new JokesRvAdapter(jokes);
        rvJokes.setAdapter(adapter);
        rvJokes.setLayoutManager(new LinearLayoutManager(getActivity()));

        button = (Button) view.findViewById(R.id.reload_button);
        editText = (EditText) view.findViewById(R.id.editText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest(editText.getText().toString());
                Log.d(LOG_TAG, "Запрошено шуток: " + editText.getText().toString());
            }
        });
    }

    private void sendRequest(String count) {

        Log.d(LOG_TAG, "SendRequest started");
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = BASE_URL + count;

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            stringResponseToArray(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d(LOG_TAG,"JSON Response: " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(LOG_TAG, error.toString());
                    }
                }
        );

        queue.add(request);
    }

    private void stringResponseToArray(String response) throws JSONException {

        ArrayList<String> list = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(response);
        JSONArray jsonArray = jsonObject.getJSONArray("value");

        Log.d(LOG_TAG, "Количество шуток: " + String.valueOf(jsonArray.length()));

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsObj = jsonArray.getJSONObject(i);
            list.add(jsObj.get("joke").toString());
            Log.d(LOG_TAG, "Шутка №" + (i + 1) + ": " + jsObj.get("joke").toString());
        }

        jokes.clear();
        jokes.addAll(Joke.getJokesFromString(list));
        adapter.notifyDataSetChanged();
    }

}
