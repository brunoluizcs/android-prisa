package com.components.bomcodigo.prisa.interactors.prenotados;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.components.bomcodigo.prisa.R;
import com.components.bomcodigo.prisa.common.viewmodel.Response;
import com.components.bomcodigo.prisa.databinding.ActivityPrenotadoBinding;
import com.components.bomcodigo.prisa.interactors.prenotados.formatter.HtmlFormatter;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.components.bomcodigo.prisa.common.viewmodel.Status.SUCCESS;

public class PrenotadoActivity extends AppCompatActivity {

    @Inject PrenotadoViewModelFactory mPrenotadoViewModelFactory;
    @Inject HtmlFormatter mHtmlFormatter;


    private ActivityPrenotadoBinding mBinding;
    private PrenotadoViewModel mPrenotadoViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_prenotado);
        mPrenotadoViewModel = ViewModelProviders.of(this,
                mPrenotadoViewModelFactory).get(PrenotadoViewModel.class);

        mBinding.fab.setOnClickListener(v -> mPrenotadoViewModel.search("0000"));
        mPrenotadoViewModel.getResponse().observe(this, this::processResponse);
    }


    private void processResponse(@Nullable Response<String> stringResponse) {
        if (stringResponse.getStatus() == SUCCESS) {
            String data = mHtmlFormatter.removeLink(stringResponse.getData());
            mBinding.wbResult.loadData(data,"text/html","utf8");
        }
    }

}
