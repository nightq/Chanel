package com.nightq.freedom.chanel.base.serverServices.userServices;

import com.nightq.freedom.chanel.baseModels.UserModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 */
public interface UserApiService {

    @FormUrlEncoded
    @POST("/users/login")
    Observable<UserModel> login(
            @Field("name") String name,
            @Field("password") String password
    );

    @GET("/users/{username}")
    Observable<UserModel> getUser(
            @Path("id") String id
    );

}
