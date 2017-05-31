package com.example.pawel.sniper.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pawel.sniper.R;
import com.example.pawel.sniper.retrofit.MyWebService;
import com.example.pawel.sniper.retrofit.pojo.repoSource;
import com.example.pawel.sniper.recycler_view_model.RecycleForProject;
import com.example.pawel.sniper.tabs.Tab1Users;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MyAdapter extends RecyclerView.Adapter
{

    public static List<String> nameProject = new ArrayList<String>();
    public static List<String> descriptionProject = new ArrayList<String>();
    public static List<String> urlPic = new ArrayList<String>();
    public static ImageView imageView ;

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
            mTitle = (TextView) pItem.findViewById(R.id.numberOfUser);
            mContent = (TextView) pItem.findViewById(R.id.userName);
            imageView = (ImageView) pItem.findViewById(R.id.iV);
        }
    }

    // konstruktor adaptera
    public MyAdapter(List<String> pArticles, RecyclerView pRecyclerView){
        items = pArticles;
        mRecyclerView = pRecyclerView;
        urlPic = Tab1Users.urlPic;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i)
    {
        // tworzymy layout artykułu oraz obiekt ViewHoldera
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.select_user_layout, viewGroup, false);

        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                int positionToDelete = mRecyclerView.getChildAdapterPosition(v);

                // tag, który jest wykorzystany do logowania
                final String CLASS_TAG = "MainActivity";

                // adapter REST z Retrofita
                RestAdapter retrofit;

                // nasz interfejs
                final MyWebService myWebService;

                // ustawiamy wybrane parametry adaptera
                retrofit = new RestAdapter.Builder()
                        // adres API
                        .setEndpoint("https://api.github.com")
                        // niech Retrofit loguje wszystko co robi
                        .setLogLevel(RestAdapter.LogLevel.FULL)
                        .build();

                // tworzymy klienta
                myWebService = retrofit.create(MyWebService.class);


                myWebService.getRepos(items.get(positionToDelete), new Callback<List<repoSource>>() {
                    @Override
                    public void success(List<repoSource> repo, Response response) {

                        for (int i = 0; i<repo.size();i++)
                        {
                            repoSource cos = repo.get(i);
                            nameProject.add(cos.getName());
                            if (cos.getDescription() == null)
                            {
                                descriptionProject.add("Brak opisu dla tego projektu!");
                            }
                            else descriptionProject.add(cos.getDescription());

                        }

                        Intent intent2;
                        intent2 =  new Intent(context, RecycleForProject.class);
                        context.startActivity(intent2);
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
            }

        });

        // tworzymy i zwracamy obiekt ViewHolder
        return new MyViewHolder(view);
    }

    public void bindView(int i)
    {
        Picasso.with(context).load(urlPic.get(i))
                .into(imageView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        // uzupełniamy layout artykułu
        ((MyViewHolder) viewHolder).mTitle.setText(items.get(i));

        try {
            bindView(i);
        } catch(Exception e) {e.printStackTrace();}
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}