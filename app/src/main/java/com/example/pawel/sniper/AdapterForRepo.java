package com.example.pawel.sniper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class AdapterForRepo extends RecyclerView.Adapter
{

    public Context context;
    // źródło danych
    private List<String> items = new ArrayList<>();
    private List<String> description = new ArrayList<>();

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
            mTitle = (TextView) pItem.findViewById(R.id.description);
            mContent = (TextView) pItem.findViewById(R.id.nameOfProject);
        }
    }

    // konstruktor adaptera
    public AdapterForRepo(List<String> pArticles,List<String> mArticles , RecyclerView pRecyclerView){
        description = pArticles;
        items = mArticles;
        mRecyclerView = pRecyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        // tworzymy layout artykułu oraz obiekt ViewHoldera
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_specification_about_user_project, viewGroup, false);

        // dla elementu listy ustawiamy obiekt OnClickListener,
        // który usunie element z listy po kliknięciu na niego
        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // odnajdujemy indeks klikniętego elementu
                int positionToDelete = mRecyclerView.getChildAdapterPosition(v);
                // usuwamy element ze źródła danych
                //items.remove(positionToDelete);
                // poniższa metoda w animowany sposób usunie element z listy
                // notifyItemRemoved(positionToDelete);
            }
        });

        // tworzymy i zwracamy obiekt ViewHolder
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        // uzupełniamy layout artykułu

        ((MyViewHolder) viewHolder).mContent.setText(items.get(i));
        ((MyViewHolder) viewHolder).mTitle.setText(description.get(i));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
