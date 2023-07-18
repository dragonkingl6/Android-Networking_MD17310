package com.example.android_networking_md17310.buoi4;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterfaceInsert {
    @FormUrlEncoded
    @POST("create_product.php")
    Call<ServerResI> insertData(
            @Field("name") String name,
            @Field("price") String price,
            @Field("description") String description
    );
}
