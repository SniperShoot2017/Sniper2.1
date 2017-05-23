package com.example.pawel.sniper;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MyAdapter extends RecyclerView.Adapter
{

    static List<String> nameProject = new ArrayList<String>();
    static List<String> descriptionProject = new ArrayList<String>();
    static List<String> urlPic = new ArrayList<String>();
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

        // dla elementu listy ustawiamy obiekt OnClickListener,
        // który usunie element z listy po kliknięciu na niego
        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // odnajdujemy indeks klikniętego elementu
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
                        intent2 =  new Intent(context, specificationAboutUserProject.class);
                        context.startActivity(intent2);
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
            }


            // usuwamy element ze źródła danych
            //items.remove(positionToDelete);
            // poniższa metoda w animowany sposób usunie element z listy
            // notifyItemRemoved(positionToDelete);

        });

        // tworzymy i zwracamy obiekt ViewHolder
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        // uzupełniamy layout artykułu
        ((MyViewHolder) viewHolder).mTitle.setText(items.get(i));

            Glide.with(viewHolder.itemView.getContext())
                    .load(urlPic.get(i))
                    .override(160, 132)
                    .crossFade()
                    .signature(new StringSignature(String.valueOf(System.currentTimeMillis())))
                    .into(imageView);



           // Glide.clear(imageView);
            // remove the placeholder (optional); read comments below
           // imageView.setImageDrawable(null);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}