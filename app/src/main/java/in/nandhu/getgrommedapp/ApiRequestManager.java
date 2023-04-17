package in.nandhu.getgrommedapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.recyclerview.widget.GridLayoutManager;
import in.nandhu.getgrommedapp.Models.MakeUpApiProductResponse;
import in.nandhu.getgrommedapp.Models.onFetchDataListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRequestManager {
    Context context;
    Retrofit retrofit =new Retrofit.Builder().baseUrl("https://makeup-api.herokuapp.com/api/").
            addConverterFactory(GsonConverterFactory.create()).build();

    public ApiRequestManager(Context context) {
        this.context = context;
    }

    public void getProductList(onFetchDataListener listener, String query) {
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<ArrayList<MakeUpApiProductResponse>> makeupApiResponse = retrofitInterface.callMakeUp(query);
        Log.e("**** URL ****", makeupApiResponse.request().url().toString());
        try {
            makeupApiResponse.enqueue(new Callback<ArrayList<MakeUpApiProductResponse>>() {
                @Override
                public void onResponse(Call<ArrayList<MakeUpApiProductResponse>> call, Response<ArrayList<MakeUpApiProductResponse>> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(context, "Error!", Toast.LENGTH_SHORT);
                    }
                    listener.onFetchData(response.body(), "success");

                }

                @Override
                public void onFailure(Call<ArrayList<MakeUpApiProductResponse>> call, Throwable t) {
                    Log.e("*** API ResponseError ***", t.getMessage().toString());
                    listener.onError( t.getMessage().toString());


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    }
