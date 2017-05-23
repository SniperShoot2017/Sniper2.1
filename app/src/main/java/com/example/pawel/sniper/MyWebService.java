package com.example.pawel.sniper;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.EncodedPath;
import retrofit.http.GET;

public interface MyWebService
{
    @GET("/search/{endpoint}")
    void getData(@EncodedPath("endpoint")String pEndpoint, Callback<UserSelection> pResponse);

    @GET("/users/{endpoint}/repos")
    void getRepos(@EncodedPath("endpoint")String pEndpoint, Callback<List<repoSource>> pResponse);

}