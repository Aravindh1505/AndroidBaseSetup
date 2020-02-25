package com.aravindh.andriodbasesetup.di.model;

import android.util.Log;

import javax.inject.Inject;

import static com.aravindh.andriodbasesetup.di.DaggerActivityKt.DAGGER;

/**
 * Created by aravindh.s on 23-02-2020.
 */
public class PetrolEngine implements Engine {

    @Inject
    public PetrolEngine() {

    }

    @Override
    public void start() {
        Log.d(DAGGER, "petrol engine started");
    }
}
