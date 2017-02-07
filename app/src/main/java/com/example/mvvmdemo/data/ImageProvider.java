package com.example.mvvmdemo.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.mvvmdemo.BuildConfig;
import com.example.mvvmdemo.data.model.ImageModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

public class ImageProvider {

    private static final String BASE_URL = "https://www.dropbox.com/";

    public final ApiService apiService;

    public ImageProvider() {
        apiService = getRetrofit(getOkHttpClient(), getGsonConverterFactory()).create(ApiService.class);
    }

    @NonNull
    private Retrofit getRetrofit(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build();
    }

    @NonNull
    private GsonConverterFactory getGsonConverterFactory() {
        return GsonConverterFactory.create(new Gson());
    }

    @NonNull
    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(getLoggingInterceptor());
        }
        return builder.build();
    }

    private Interceptor getLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }


    public Observable<List<ImageModel>> getImageModels() {
        return apiService.getImages().flatMap(imageModelResponse -> {
            Log.wtf("TAG", String.valueOf(imageModelResponse.code()));
            return Observable.just(imageModelResponse.body());
        });
    }

    private interface ApiService {

        @GET("s/00vvnrfrfvl6euk/image_demo.json?dl=1")
        Observable<Response<List<ImageModel>>> getImages();

    }

}
