package com.aravindh.andriodbasesetup.di.component;

import com.aravindh.andriodbasesetup.network.RetrofitModule;
import com.aravindh.andriodbasesetup.repositary.MainRepository;

import dagger.Component;

/**
 * Created by aravindh.s on 24-02-2020.
 */

@Component(modules = RetrofitModule.class)
public interface AppComponent {

    void inject(MainRepository mainRepository);
}
