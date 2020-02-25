package com.aravindh.andriodbasesetup.di.model;

import dagger.Binds;
import dagger.Module;

/**
 * Created by aravindh.s on 23-02-2020.
 */

@Module
public abstract class PetrolEngineModule {

    @Binds
    abstract Engine bindEngine(PetrolEngine engine);
}
