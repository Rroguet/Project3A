package com.example.project3a.data;

import com.example.project3a.presentation.model.RestGalaxiesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GalaxieAPI {
    @GET("galaxies.json")
    Call<RestGalaxiesResponse> getGalaxieResponse();
}
