package com.aravindh.andriodbasesetup.di.component;

import com.aravindh.andriodbasesetup.di.DaggerActivity;
import com.aravindh.andriodbasesetup.di.model.Car;
import com.aravindh.andriodbasesetup.di.model.PetrolEngineModule;
import com.aravindh.andriodbasesetup.di.module.WheelsModule;
import com.aravindh.andriodbasesetup.ui.activity.MainActivity;

import dagger.Component;

/**
 * Created by aravindh.s on 23-02-2020.
 */

@Component(modules = {WheelsModule.class, PetrolEngineModule.class})
public interface CarComponent {

    Car getCar();

    void inject(MainActivity mainActivity);

    void inject(DaggerActivity daggerActivity);
}
