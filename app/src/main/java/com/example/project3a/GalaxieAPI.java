package com.example.project3a;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GalaxieAPI {
    @GET("galaxies.json")
    Call<RestGalaxiesResponse> getGalaxieResponse();
}
