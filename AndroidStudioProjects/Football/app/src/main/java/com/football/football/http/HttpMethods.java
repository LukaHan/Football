package com.football.football.http;

import com.football.football.Constant;
import com.grapesnberries.curllogger.CurlLoggerInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/9/7.
 */
public class HttpMethods {
    public static final String BASE_URL = Constant.BASE_URL_ONLINE;

    private static final int DEFAULT_TIMEOUT = 10;

    private Retrofit retrofit;
    private ApiService apiService;
    private final OkHttpClient.Builder builder;

    //私有化构造方法
    private HttpMethods() {
        //手动创建一个OkHttpCicent并设置超时时间
        builder = new OkHttpClient.Builder()
                //添加token过期处理
                //打印log
                .addInterceptor(new CurlLoggerInterceptor())
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    //在访问HttpMethods时创建单例
    private static class singletonHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    //获取单例
    public static HttpMethods getInstance() {
        return singletonHolder.INSTANCE;
    }

    public static ApiService createService() {
        return singletonHolder.INSTANCE.apiService;
    }
}
