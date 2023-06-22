package com.example.ejemploapi;

/*
Guia: https://ed.team/blog/como-consumir-una-api-rest-desde-android
*/

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> data = new ArrayList<>();
    ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,data);

    String respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Se agrega el Adapter al Listview
        listView = findViewById(R.id.list);
        listView.setAdapter(arrayAdapter);

        getPosts();
    }

    private void getPosts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService postService = retrofit.create(APIService.class);
        Call<List<Posts>> call = postService.getPosts();

        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                for(Posts post : response.body()) {
                    data.add(post.getTitle());
                    respuesta = "POST: " + post.getId() + "\nUser: " + post.getUserId() + "\n"
                            + post.getTitle() + "\n" + post.getBody();
                    System.out.println("Respuesta:"+respuesta);
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
            }
        });
    }
}