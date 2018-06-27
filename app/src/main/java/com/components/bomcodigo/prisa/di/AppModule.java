package com.components.bomcodigo.prisa.di;


import android.content.Context;


import com.components.bomcodigo.prisa.App;

import java.util.UUID;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final UUID mUserSessionId;

    public AppModule() {
        mUserSessionId = UUID.randomUUID();
    }

    @Provides
    Context provideContext(App application) {
        return application.getApplicationContext();
    }

    @Provides
    @Named("UserSessionId")
    UUID provideUserSessionId(){
        return mUserSessionId;
    }

}
