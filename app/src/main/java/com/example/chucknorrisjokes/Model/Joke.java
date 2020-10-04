package com.example.chucknorrisjokes.Model;

import java.util.ArrayList;
import java.util.List;

public class Joke {

    private String joke;

    public Joke(String joke) {
        this.joke = joke;
    }

    public String getJoke() {
        return joke;
    }

    public static ArrayList<Joke> getJokes(int numJokes) {
        ArrayList<Joke> list = new ArrayList<>();
        for (int i = 0; i < numJokes; i++) {
            list.add(new Joke("Joke " + i));
        }

        return list;
    }

    public static ArrayList<Joke> getJokesFromString(List<String> list) {
        ArrayList<Joke> jokes = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            jokes.add(new Joke(list.get(i)));
        }

        return jokes;
    }
}