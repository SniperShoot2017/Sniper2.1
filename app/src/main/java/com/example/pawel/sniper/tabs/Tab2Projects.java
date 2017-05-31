package com.example.pawel.sniper.tabs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.pawel.sniper.retrofit.pojo.Owner;
import com.example.pawel.sniper.retrofit.pojo.Proj;
import com.example.pawel.sniper.retrofit.pojo.ProjItem;
import com.example.pawel.sniper.recycler_view_model.RecycleForProjectTab2;
import com.example.pawel.sniper.R;
import com.example.pawel.sniper.retrofit.MyWebService;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Tab2Projects  extends android.support.v4.app.Fragment
{

    Button btn ;
    public static List<String> login = new ArrayList<String>();
    public static List<String> name = new ArrayList<String>();
    public EditText editText;
    ProgressBar secondBar = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2_project, container, false);

        btn = (Button)rootView.findViewById(R.id.szukaj);
        editText = (EditText)rootView.findViewById(R.id.eT2);
        secondBar = (ProgressBar)rootView.findViewById(R.id.pb2);

        btn.findViewById(R.id.szukaj).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                turnOnProcessBar(true);

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

                //obsluga przyciskow ekranu

                final String userName = "repositories?q="+ editText.getText().toString();

                myWebService.getPro(userName, new Callback<Proj>() {
                    @Override
                    public void success(Proj proj, Response response)
                    {

                        login.clear();
                        name.clear();

                        int totalCount = proj.getTotalCount();
                        List<ProjItem> items = new ArrayList<ProjItem>();
                        items = proj.getItems();

                        ProjItem ite = items.get(0);
                        String asd = ite.getName();

                        Owner it = null;
                        it = ite.getOwner();
                        switch (totalCount) {
                            case 0:
                                Context context = getActivity();
                                CharSequence text = "Brak dopasowań dla!";
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                                break;

                            case 1:
                                name.add(ite.getName());
                                login.add(it.getLogin());
                                break;

                            default:
                                for (int i = 0; i < items.size(); i++) {
                                    ite = items.get(i);
                                    it = ite.getOwner();
                                    name.add(ite.getName());
                                    login.add(it.getLogin());
                                }
                                break;
                        }
                        turnOnProcessBar(false);
                        Intent intent = new Intent(getActivity(), RecycleForProjectTab2.class);
                        startActivity(intent);
                    }
                    @Override
                    public void failure(RetrofitError error) {
                        Log.d(CLASS_TAG, error.getLocalizedMessage());
                    }
                });
            }
        });

        return rootView;
    }

    public void turnOnProcessBar(boolean turnON_OFF)
    {
        if (turnON_OFF == true)
        {
            secondBar.setVisibility(View.VISIBLE);
        }
        else  secondBar.setVisibility(View.GONE);
    }



}
