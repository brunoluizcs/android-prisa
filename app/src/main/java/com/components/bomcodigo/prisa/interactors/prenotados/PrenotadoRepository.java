package com.components.bomcodigo.prisa.interactors.prenotados;


import com.components.bomcodigo.prisa.common.domain.api.Api;

import javax.inject.Inject;

import io.reactivex.Observable;

public class PrenotadoRepository {

    private final Api mApi;

    @Inject
    public PrenotadoRepository(Api api) {
        mApi = api;
    }


    Observable<String> search(String protocol){
        return mApi.search(protocol,"Pesquisar");
    }

}
