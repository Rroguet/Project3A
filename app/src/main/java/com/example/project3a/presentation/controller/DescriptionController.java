package com.example.project3a.presentation.controller;

import com.example.project3a.presentation.model.Galaxie;
import com.example.project3a.presentation.view.DescriptionActivity;

public class DescriptionController {
    private Galaxie galaxie;
    private DescriptionActivity view;

    public DescriptionController(DescriptionActivity view, Galaxie galaxie) {
        this.view = view;
        this.galaxie = galaxie;
    }

    public void onStart(){
        view.showDetail(galaxie);
    }

    public void onButtonClick(){
        view.backToList();
    }
}
