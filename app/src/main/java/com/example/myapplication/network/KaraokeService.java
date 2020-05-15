package com.example.myapplication.network;

import com.example.myapplication.model.CompositionInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface KaraokeService {

    @GET("composition")
    Call<List<CompositionInfo>> getCompositions();
}
