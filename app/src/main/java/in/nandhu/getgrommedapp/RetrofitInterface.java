package in.nandhu.getgrommedapp;

import java.util.ArrayList;
import java.util.List;

import in.nandhu.getgrommedapp.Models.MakeUpApiProductResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {

   // @GET("v1/products.json?brand_name=colourpop")
    @GET("v1/products.json")
    Call<ArrayList<MakeUpApiProductResponse>> callMakeUp(
            @Query("brand") String brand
    );
}
