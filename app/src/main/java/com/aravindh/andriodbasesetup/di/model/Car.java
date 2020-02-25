package com.aravindh.andriodbasesetup.di.model;

import android.util.Log;

import javax.inject.Inject;

import static com.aravindh.andriodbasesetup.di.DaggerActivityKt.DAGGER;

/**
 * Created by aravindh.s on 23-02-2020.
 */
public class Car {


    /*FIELD INJECTION TRIGGER 2nd*/
    @Inject
    Engine engine;

    @Inject
    Wheels wheels;

    /*CONSTRUCTOR INJECTION TRIGGER 1st*/
    @Inject
    public Car() {

    }

    public void drive() {
        engine.start();
        Log.d(DAGGER, "driving...");
    }

    /*METHOD INJECTION TRIGGER 3rd*/
    @Inject
    public void getEngineNumber() {
        Log.d(DAGGER, "engineNumber is : 157563836853");
    }

}
