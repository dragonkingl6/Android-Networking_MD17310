package com.example.android_networking_md17310.buoi5;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterfaceDelete {
    @FormUrlEncoded
    @POST("delete_product1.php")
    Call<ServerResDeleteData> deleteData(@Field("pid") String pid);
}
