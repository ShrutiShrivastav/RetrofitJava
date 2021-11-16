package com.example.retrofitexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SecondActivity extends AppCompatActivity {
    TextView tv_response;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tv_response = findViewById(R.id.tv_response);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.puenacnt.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        //GETTING RESPONSE THROUGH JSON OBJECT
        JsonObject jsonObject= new JsonObject();
        try {
            jsonObject.addProperty("endpoint", "serviceplans");
            jsonObject.addProperty("type", "SMILE");

        } catch (JsonIOException e) {
            e.printStackTrace();
        }

        Call<List<Model>> call = jsonPlaceHolderApi.getModel(jsonObject);


        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {

                if(!response.isSuccessful()){
                    tv_response.setText("Code" +response.code());
                    return;
                }
               // Log.e("Rest response",post.status);
                  List<Model> models =response.body();
                //    Log.e("Rest response", String.valueOf(posts));

             for( Model model :models){
                    String content = "";
                    content += ""+ model.getName()+"\n";
                    content += ""+ model.getAmount()+"\n";
                 content += ""+ model.getCode()+"\n";
                 content += ""+ model.getType()+"\n\n";
                    tv_response.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                tv_response.setText(t.getMessage());
            }
        });

    }
}