package com.components.bomcodigo.prisa.interactors.prenotados;


import com.components.bomcodigo.prisa.common.domain.api.Api;
import com.components.bomcodigo.prisa.common.domain.api.ApiModule;
import com.components.bomcodigo.prisa.interactors.prenotados.formatter.DefaultHtmlFormatter;
import com.components.bomcodigo.prisa.interactors.prenotados.formatter.HtmlFormatter;
import com.components.bomcodigo.prisa.rx.SchedulersFacade;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ApiModule.class})
public class PrenotadoModule {

    @Provides
    PrenotadoRepository provideDetailRepository(Api api){
        return new PrenotadoRepository(api);
    }

    @Provides
    HtmlFormatter provideHtmlFormatter(){
        return new DefaultHtmlFormatter();
    }

    @Provides
    PrenotadoViewModelFactory provideDetailViewModel(PrenotadoRepository prenotadoRepository, SchedulersFacade schedulersFacade){
        return new PrenotadoViewModelFactory(prenotadoRepository,schedulersFacade);
    }



}
