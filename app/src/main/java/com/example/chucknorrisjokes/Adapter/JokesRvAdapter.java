package com.example.chucknorrisjokes.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chucknorrisjokes.Model.Joke;
import com.example.chucknorrisjokes.R;

import java.util.List;

public class JokesRvAdapter extends RecyclerView.Adapter<JokesRvAdapter.ViewHolder> {

    private List<Joke> data;

    public JokesRvAdapter(List<Joke> jokes) {
        data = jokes;
    }

    @Override
    public JokesRvAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // inflate the layout
        View jokeView = inflater.inflate(R.layout.item_joke, parent, false);

        // return a new holder instance
        ViewHolder viewHolder = new ViewHolder(jokeView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Joke joke = data.get(position);

        // Set item views based on view and data model
        TextView textView = holder.jokeTextView;
        textView.setText(joke.getJoke());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView jokeTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            jokeTextView = (TextView) itemView.findViewById(R.id.tv_joke_item);
        }

    }
}
