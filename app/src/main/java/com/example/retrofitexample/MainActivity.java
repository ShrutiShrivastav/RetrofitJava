package com.example.retrofitexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
TextView tv_response;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_response = findViewById(R.id.tv_response);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://copyclikker.framework.infowindtech.biz/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
         Call<Post>call = jsonPlaceHolderApi.getPost();

         

         call.enqueue(new Callback<Post>() {
             @Override
             public void onResponse(Call<Post> call, Response<Post> response) {

                 Post post = response.body();

                 Log.d("POST", " " + response.isSuccessful());
                 Log.d("POST", " " + response.body().getStatus()+"   "+response.body().getMessage());

               if(response.isSuccessful()){
                     tv_response.setText("Code" +response.body().getStatus());
                     return;
                 }

               //  List<Post> posts = (List<Post>) response.body();
                 //    Log.e("Rest response", String.valueOf(posts));

           //   for( Post post :posts){
            //        String content = "";
            //        content += "ID:"+ post.getStatus() += "GENRE:"+ post.getGenre()+"\n\n";

             //       tv_response.append(content);
                // }
             }

             @Override
             public void onFailure(Call<Post> call, Throwable t) {
                tv_response.setText(t.getMessage());
             }
         });


    }
}