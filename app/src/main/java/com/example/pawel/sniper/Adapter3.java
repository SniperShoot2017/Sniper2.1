package com.example.pawel.sniper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by pawel on 24.05.2017.
 */

public class Adapter3 extends RecyclerView.Adapter {

    static List<String> nameProject = new ArrayList<String>();
    static List<String> descriptionProject = new ArrayList<String>();


    public Context context;
    // źródło danych
    private List<String> items = new ArrayList<>();

    // obiekt listy artykułów
    private RecyclerView mRecyclerView;

    // implementacja wzorca ViewHolder
    // każdy obiekt tej klasy przechowuje odniesienie do layoutu elementu listy
    // dzięki temu wywołujemy findViewById() tylko raz dla każdego elementu
    private class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public TextView mContent;

        public MyViewHolder(View pItem) {
            super(pItem);
            context = pItem.getContext();
            mTitle = (TextView) pItem.findViewById(R.id.description2);
            mContent = (TextView) pItem.findViewById(R.id.nameOfProject2);
        }
    }

    // konstruktor adaptera
    public Adapter3(RecyclerView pRecyclerView){
        nameProject = Tab2Projects.login;
        descriptionProject = Tab2Projects.name;
        mRecyclerView = pRecyclerView;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i)
    {
        // tworzymy layout artykułu oraz obiekt ViewHoldera
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.w2, viewGroup, false);


        // tworzymy i zwracamy obiekt ViewHolder
        return new Adapter3.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        // uzupełniamy layout artykułu
        ((Adapter3.MyViewHolder) viewHolder).mTitle.setText("Właściciel projektu: " +nameProject.get(i));
        ((Adapter3.MyViewHolder) viewHolder).mContent.setText("Nazwa projektu: " + descriptionProject
                .get(i));

    }

    @Override
    public int getItemCount() {
        return nameProject.size();
    }

}