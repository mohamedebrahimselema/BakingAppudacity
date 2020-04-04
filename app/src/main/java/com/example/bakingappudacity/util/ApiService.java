package com.example.bakingappudacity.util;

import com.example.bakingappudacity.models.Recipe;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;



public interface ApiService {

    @GET("android-baking-app-json")
    Call<ArrayList<Recipe>> getRecipes();
}
