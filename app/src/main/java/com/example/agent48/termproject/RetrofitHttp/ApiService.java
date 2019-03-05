package com.example.agent48.termproject.RetrofitHttp;


import com.example.agent48.termproject.Object.Msg;
import com.example.agent48.termproject.Object.Product;
import com.example.agent48.termproject.Object.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Agent47 on 31.07.2017.
 */

public interface ApiService {


    @FormUrlEncoded
    @POST("Webservice directory/Login.php")
    Call<Msg> userLogin(@Field("KULLANICI_ADI") String username,
                        @Field("SIFRE") String password);

    @FormUrlEncoded
    @POST("Webservice directory/Login.php/GetStock.php")
    Call<List<Product>> getStock(@Field("placeholder") String placeholder);

    @FormUrlEncoded
    @POST("Webservice directory/Login.php/AddProduct.php")
    Call<Msg> addProduct(@Field("URUN_KODU") String productCode,
                         @Field("URUN_ADI") String productName,
                         @Field("URETIM_TARIHI") String productionDate,
                         @Field("SON_TUKETIM_TARIHI") String consumeDate,
                         @Field("ADET") String quantity,
                         @Field("BIRIM_FIYAT") String price);

    @FormUrlEncoded
    @POST("Webservice directory/Login.php/DeleteProduct.php")
    Call<Msg> deleteProduct(@Field("ID") String productCode);

    @FormUrlEncoded
    @POST("Webservice directory/Login.php/GetUpdateProductInfo.php")
    Call<List<Product>> getUpdateProductInfo(@Field("URUN_KODU") String productCode);

    @FormUrlEncoded
    @POST("Webservice directory/Login.php/UpdateProduct.php")
    Call<Msg> updateProduct(@Field("URUN_KODU") String productCode,
                            @Field("URUN_ADI") String productName,
                            @Field("URETIM_TARIHI") String productionDate,
                            @Field("SON_TUKETIM_TARIHI") String consumeDate,
                            @Field("ADET") String quantity,
                            @Field("BIRIM_FIYAT") String price);

    @FormUrlEncoded
    @POST("Webservice directory/Login.php/GetDecreasing.php")
    Call<List<Product>> getDecreasing(@Field("placeholder") String placeholder);

    @FormUrlEncoded
    @POST("Webservice directory/Login.php/OrderProduct.php")
    Call<Msg> orderProduct(@Field("URUN_KODU") String productCode,
                           @Field("URUN_ADI") String productName,
                           @Field("ADET") String quantity,
                           @Field("BIRIM_FIYAT") String price,
                           @Field("DURUM") String state);

    @FormUrlEncoded
    @POST("Webservice directory/Login.php/GetOrders.php")
    Call<List<Product>> getOrders(@Field("placeholder") String placeholder);

    @FormUrlEncoded
    @POST("Webservice directory/Login.php/DeleteOrder.php")
    Call<Msg> deleteOrder(@Field("ID") String id);

    @FormUrlEncoded
    @POST("Webservice directory/Login.php/GetLogs.php")
    Call<List<Product>> getLogs(@Field("placeholder") String placeholer);

    @FormUrlEncoded
    @POST("Webservice directory/Login.php/GetRequests.php")
    Call<List<User>> getUsers(@Field("placeholder") String placeholder);

    @FormUrlEncoded
    @POST("Webservice directory/Login.php/AcceptRequest.php")
    Call<Msg> acceptRequest(@Field("SIRKET_ADI") String companyName,
                            @Field("AD") String name,
                            @Field("SOYAD") String surname,
                            @Field("E_POSTA") String email,
                            @Field("KULLANICI_ADI") String username,
                            @Field("SIFRE") String password);

    @FormUrlEncoded
    @POST("Webservice directory/Login.php/DeclineRequest.php")
    Call<Msg> declineRequest(@Field("ID") String id);

}
