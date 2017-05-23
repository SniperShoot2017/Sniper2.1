package com.example.pawel.sniper;

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

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.example.pawel.sniper.MyAdapter.urlPic;
import static com.example.pawel.sniper.Tab1Users.nameAllUsers;

public class Tab2Projects  extends android.support.v4.app.Fragment
{

    Button btn ;
    static List<String> nameAllUsers = new ArrayList<String>();
    static List<String> urlPic = new ArrayList<String>();
    public EditText editText;
    ProgressBar secondBar = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2_project, container, false);
        return rootView;
    }



}
