package com.components.bomcodigo.prisa.interactors.prenotados;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.components.bomcodigo.prisa.rx.SchedulersFacade;

public class PrenotadoViewModelFactory implements ViewModelProvider.Factory{

    private final PrenotadoRepository mPrenotadoRepository;
    private final SchedulersFacade mSchedulersFacade;

    public PrenotadoViewModelFactory(PrenotadoRepository detailRepository, SchedulersFacade schedulersFacade) {
        mPrenotadoRepository = detailRepository;
        mSchedulersFacade = schedulersFacade;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PrenotadoViewModel.class)) {
            return (T) new PrenotadoViewModel(mPrenotadoRepository,mSchedulersFacade);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
