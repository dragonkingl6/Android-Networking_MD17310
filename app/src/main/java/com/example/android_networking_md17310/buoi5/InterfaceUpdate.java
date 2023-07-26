package com.example.android_networking_md17310.buoi5;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterfaceUpdate {
    @FormUrlEncoded
    @POST("update_product1.php")
    Call<ServerResUpdataData> updateData(@Field("pid") String pid,
                                         @Field("name") String name,
                                         @Field("price") String price,
                                         @Field("description") String description);
}
