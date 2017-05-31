package com.example.pawel.sniper.recycler_view_model;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.pawel.sniper.R;
import com.example.pawel.sniper.adapters.AdapterForRepo;
import com.example.pawel.sniper.adapters.MyAdapter;

public class RecycleForProject extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_look);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.items);
        // w celach optymalizacji
        recyclerView.setHasFixedSize(true);

        // ustawiamy LayoutManagera
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // ustawiamy animatora, który odpowiada za animację dodania/usunięcia elementów listy
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // tworzymy adapter oraz łączymy go z RecyclerView
        recyclerView.setAdapter(new AdapterForRepo(MyAdapter.nameProject, MyAdapter.descriptionProject, recyclerView));
    }
}
