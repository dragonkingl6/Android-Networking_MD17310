package com.example.android_networking_md17310.buoi4;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceSelect {
    @GET("get_all_product.php")
    Call<ServerResS> selectData();
}
