package com.example.ejemploapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    String API_ROUTE = "/posts";

    @GET(API_ROUTE)
    Call<List<Posts>> getPosts();
}
