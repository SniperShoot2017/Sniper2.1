package com.example.pawel.sniper.retrofit;

import com.example.pawel.sniper.retrofit.pojo.Proj;
import com.example.pawel.sniper.retrofit.pojo.UserSelection;
import com.example.pawel.sniper.retrofit.pojo.repoSource;

import java.util.List;

import retrofit.Callback;
import retrofit.http.EncodedPath;
import retrofit.http.GET;

public interface MyWebService
{
    @GET("/search/{endpoint}")
    void getData(@EncodedPath("endpoint")String pEndpoint, Callback<UserSelection> pResponse);

    @GET("/search/{endpoint}")
    void getPro(@EncodedPath("endpoint")String pEndpoint, Callback<Proj> pResponse);

    @GET("/users/{endpoint}/repos")
    void getRepos(@EncodedPath("endpoint")String pEndpoint, Callback<List<repoSource>> pResponse);

}