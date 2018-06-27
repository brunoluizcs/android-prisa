package com.components.bomcodigo.prisa.common.viewmodel;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class Response<T> {
    private final Status mStatus;

    @Nullable
    public final T mData;

    @Nullable
    private final Throwable mError;

    public Response(Status status, @Nullable T data, @Nullable Throwable error){
        this.mStatus = status;
        this.mData = data;
        this.mError = error;
    }

    public Response(@NonNull Throwable error){
        this.mStatus = Status.ERROR;
        this.mData = null;
        this.mError = error;
    }

    public Status getStatus() {
        return mStatus;
    }

    @Nullable
    public T getData() {
        return mData;
    }

    @Nullable
    public Throwable getError() {
        return mError;
    }
}
