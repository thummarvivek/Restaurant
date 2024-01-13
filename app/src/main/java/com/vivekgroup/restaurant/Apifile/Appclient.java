package com.vivekgroup.restaurant.Apifile;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Appclient {

    public static final String BASEURL ="http://vivekgroup.whf.bz/Android_site/";

    public static Retrofit getclient()
    {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit;
    }
}
