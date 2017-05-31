package com.example.pawel.sniper.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pawel.sniper.R;
import com.example.pawel.sniper.tabs.Tab2Projects;

import java.util.ArrayList;
import java.util.List;



public class AdapterForProjects extends RecyclerView.Adapter {

    static List<String> nameProject = new ArrayList<String>();
    static List<String> descriptionProject = new ArrayList<String>();


    public Context context;
    // źródło danych
    private List<String> items = new ArrayList<>();

    // obiekt listy artykułów
    private RecyclerView mRecyclerView;

    // implementacja wzorca ViewHolder

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
    public AdapterForProjects(RecyclerView pRecyclerView){
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
        return new AdapterForProjects.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        // uzupełniamy layout artykułu
        ((AdapterForProjects.MyViewHolder) viewHolder).mTitle.setText("Właściciel projektu: " +nameProject.get(i));
        ((AdapterForProjects.MyViewHolder) viewHolder).mContent.setText("Nazwa projektu: " + descriptionProject
                .get(i));

    }

    @Override
    public int getItemCount() {
        return nameProject.size();
    }

}