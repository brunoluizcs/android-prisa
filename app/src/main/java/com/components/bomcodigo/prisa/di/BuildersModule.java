package com.components.bomcodigo.prisa.di;

import com.components.bomcodigo.prisa.interactors.prenotados.PrenotadoActivity;
import com.components.bomcodigo.prisa.interactors.prenotados.PrenotadoModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector(modules = {PrenotadoModule.class})
    abstract PrenotadoActivity bindPrenotadoActivity();



}
