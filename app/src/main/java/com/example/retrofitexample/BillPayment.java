package com.example.retrofitexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BillPayment extends AppCompatActivity {
    TextView tv_response;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_payment);

        tv_response = findViewById(R.id.tv_response);
        try {
            postData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    private void postData() throws JSONException {

        //creating retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://app.shopazo.com:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //creating object of our interface
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        //creating object of model class
        JSONObject data = new JSONObject();
        data.put("user_id",1);
        data.put("mobile_id",2);
        data.put("type",1);
        data.put("total", 1);
        data.put("response_detail",1);
        data.put("creditToken",1);
        data.put("reference",1);


        //creating call object and passing data in model
        Call<BillPaymentModel> call = jsonPlaceHolderApi.getBillData(data);

        call.enqueue(new Callback<BillPaymentModel>() {
            @Override
            public void onResponse(Call<BillPaymentModel> call, Response<BillPaymentModel> response) {
                if (!response.isSuccessful()) {
                  //  tv_response.setText("Code" + response.code());
                    BillPaymentModel models = response.body();

                    //     for (BillPaymentModel model : models) {
                    String content = "";
                    content += "" + models.getStatus() + "\n";
                    content += "" + models.getMessage() + "\n\n";

                    tv_response.setText(content);
                }
            }

            @Override
            public void onFailure(Call<BillPaymentModel> call, Throwable t) {
                tv_response.setText(t.getMessage());
            }
        });


    }



}