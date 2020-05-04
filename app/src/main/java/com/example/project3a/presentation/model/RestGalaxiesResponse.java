package com.example.project3a.presentation.model;

import com.example.project3a.presentation.model.Galaxie;

import java.util.List;

public class RestGalaxiesResponse {
    private Integer total;
    private List<Galaxie> galaxies;

    public int getTotal() {
        return total;
    }

    public List<Galaxie> getGalaxies() {
        return galaxies;
    }
}
