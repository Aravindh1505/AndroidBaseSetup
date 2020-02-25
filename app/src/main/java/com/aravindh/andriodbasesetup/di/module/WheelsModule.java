package com.aravindh.andriodbasesetup.di.module;

import com.aravindh.andriodbasesetup.di.model.Rims;
import com.aravindh.andriodbasesetup.di.model.Tires;
import com.aravindh.andriodbasesetup.di.model.Wheels;

import dagger.Module;
import dagger.Provides;

/**
 * Created by aravindh.s on 23-02-2020.
 */

@Module
public class WheelsModule {

    @Provides
    static Rims provideRims() {
        return new Rims();
    }

    @Provides
    static Tires provideTired() {
        Tires tires = new Tires();
        tires.inflate();
        return tires;
    }

    @Provides
    static Wheels provideWheels(Rims rims, Tires tires) {
        return new Wheels(rims, tires);
    }
}
