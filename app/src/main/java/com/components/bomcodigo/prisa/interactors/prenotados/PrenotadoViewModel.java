package com.components.bomcodigo.prisa.interactors.prenotados;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.components.bomcodigo.prisa.common.viewmodel.Response;
import com.components.bomcodigo.prisa.common.viewmodel.Status;
import com.components.bomcodigo.prisa.rx.SchedulersFacade;

import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;


public class PrenotadoViewModel extends ViewModel {

    private final SchedulersFacade mSchedulersFacade;
    private final CompositeDisposable mDisposables = new CompositeDisposable();
    private final PrenotadoRepository mPrenotadoRepository;

    private final MutableLiveData<Response<String>> mResponse = new MutableLiveData<>();

    LiveData<Response<String>> getResponse() {
        return mResponse;
    }


    public PrenotadoViewModel(PrenotadoRepository prenotadoRepository,SchedulersFacade schedulersFacade) {
        mSchedulersFacade = schedulersFacade;
        mPrenotadoRepository = prenotadoRepository;
    }



    public void search(String protocol) {
        mDisposables.add(mPrenotadoRepository.search(protocol)
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(result -> {
                            if (result != null){
                                Timber.d("search -> success");
                                Response<String> response = new Response<>(
                                        Status.SUCCESS,
                                        result,
                                        null
                                        );
                                mResponse.setValue(response);
                            }
                        },
                        throwable -> {
                            Timber.e("search -> error",throwable);
                            mResponse.setValue(new Response<>(throwable));
                        }
                )
        );
    }


    @Override
    protected void onCleared() {
        mDisposables.clear();
    }
}
