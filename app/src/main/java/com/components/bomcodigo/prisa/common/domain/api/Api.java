package com.components.bomcodigo.prisa.common.domain.api;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface Api {
    @GET("dados/protresult.php?")
    Observable<String> search(@Query("texto") String texto, @Query("Submit") String submit);
}
