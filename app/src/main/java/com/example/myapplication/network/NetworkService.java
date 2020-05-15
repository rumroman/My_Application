package com.example.myapplication.network;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class NetworkService {

    private static NetworkService networkService;

    private static final String BASE_URL = "http://192.168.1.2:8081/karaoke/";
    private Retrofit retrofit;

    private NetworkService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public static synchronized NetworkService getInstance() {
        if (networkService == null) {
            networkService = new NetworkService();
        }
        return networkService;
    }

    public KaraokeService getJSONApi() {
        return retrofit.create(KaraokeService.class);
    }
}
