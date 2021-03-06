package com.example.pawel.sniper.tabs;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.pawel.sniper.recycler_view_model.RecycleForUsers;
import com.example.pawel.sniper.R;
import com.example.pawel.sniper.retrofit.pojo.UserSelection;
import com.example.pawel.sniper.retrofit.pojo.Item;
import com.example.pawel.sniper.retrofit.MyWebService;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Tab1Users  extends Fragment
{
    Button btn ;
    public static List<String> nameAllUsers = new ArrayList<String>();
    public static List<String> urlPic = new ArrayList<String>();
    public EditText editText;

    ProgressBar secondBar = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.tab1_users, container, false);

        btn = (Button)rootView.findViewById(R.id.zatwierdz);
        editText = (EditText)rootView.findViewById(R.id.userName);
        secondBar = (ProgressBar)rootView.findViewById(R.id.progressBar);

        btn.findViewById(R.id.zatwierdz).setOnClickListener(new View.OnClickListener()
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
                    String userName = null;
                     userName = "users?q="+ editText.getText().toString();

                nameAllUsers.clear();
                urlPic.clear();

                myWebService.getData(userName, new Callback<UserSelection>() {
                    @Override
                    public void success(UserSelection userSelection, Response response)
                    {
                        List<Item> items = null;
                        int totalCount = userSelection.getTotalCount();
                        items = new ArrayList<Item>();
                        items = userSelection.getItems();

                        Item ite  = null  ;
                        ite = items.get(0);
                        String asd = null;
                        ite.getLogin();

                        Item it = null;
                        switch (totalCount) {
                            case 0:
                                Context context = getActivity();
                                CharSequence text = "Brak dopasowań dla!";
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                                break;

                            case 1:
                                it = items.get(0);
                                nameAllUsers.add(it.getLogin());
                                urlPic.add(it.getAvatarUrl());
                                break;

                            default:
                                for (int i = 0; i < userSelection.getItems().size(); i++) {
                                    it = items.get(i);
                                    nameAllUsers.add(it.getLogin());
                                    urlPic.add(it.getAvatarUrl());
                                }
                                break;
                        }
                        turnOnProcessBar(false);
                        Intent intent = new Intent(getActivity(), RecycleForUsers.class);
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


