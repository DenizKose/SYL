package ru.greenslonik.deniz.syl.fragments;


import android.annotation.SuppressLint;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import ru.greenslonik.deniz.syl.CatGames5;
import ru.greenslonik.deniz.syl.R;


public class Games5 extends ListFragment {

    Intent intent;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.games_cat5));
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        intent = new Intent();
        intent.setClass(getActivity(), CatGames5.class);
        intent.putExtra("head",position);
        startActivity(intent);
    }

    @SuppressLint("InflateParams")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_style,null);
    }
}