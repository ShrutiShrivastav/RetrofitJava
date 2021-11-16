package com.example.retrofitexample;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi
{
    //we define the URl end point like (genre_list ) here while the base URl is defined in main class
    @GET("?action=genre_list")
    //just defining this method getpost() in interface will provide the implementation later
    Call<Post> getPost();



    //this is third API
    @Headers({"Content-Type: application/json", "X-Auth: shopazomobile", "X-Auth-Hash: 2CB1253E243C49F4"})
    @POST("RESTv6/")
    Call<List<Model>>getModel(@Body JsonObject jsonObject);

    //bill payment API
    @Headers({"Content-Type: application/json", "access-token: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InRlc2dAdGVzdGluZy5jb20iLCJuYW1lIjoiVXlpb3NhIiwiaWQiOjU3LCJpYXQiOjE2MzQ5OTczMTQsImV4cCI6MTYzNTYwMjExNH0.CrAbGx35cwG32vXC6jRHebt8LYocz6rQFriGvirSPjM"})
    @POST("add-bill-payments-history/")
    Call<BillPaymentModel> getBillData(@Body JSONObject jsonObject);
}
