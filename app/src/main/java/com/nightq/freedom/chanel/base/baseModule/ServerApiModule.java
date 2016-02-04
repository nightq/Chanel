package com.nightq.freedom.chanel.base.baseModule;

import android.app.Application;
import android.content.SharedPreferences;

import com.nightq.freedom.chanel.BuildConfig;
import com.nightq.freedom.chanel.R;
import com.nightq.freedom.chanel.base.serverServices.userServices.UserApiService;
import com.nightq.freedom.chanel.base.utils.Constants;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

/**
 * Created by Miroslaw Stanek on 22.04.15.
 */
@Module
public class ServerApiModule {

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }

        builder.connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(60 * 1000, TimeUnit.MILLISECONDS);

        return builder.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRestAdapter(
            Application application,
            SharedPreferences sharedPreferences,
            OkHttpClient okHttpClient) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(okHttpClient)
                .baseUrl(
                        sharedPreferences.getString(Constants.APP_CONFIG.SERVER_API_KEY,
                        application.getString(R.string.endpoint)))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
        return builder.build();
    }

}
