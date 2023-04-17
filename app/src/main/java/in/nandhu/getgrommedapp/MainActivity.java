package in.nandhu.getgrommedapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import in.nandhu.getgrommedapp.Models.MakeUpApiProductResponse;
import in.nandhu.getgrommedapp.Models.onFetchDataListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

public class MainActivity extends AppCompatActivity implements OnBrandItemClickListener {
    RecyclerView recyclerView;
    ArrayList<MakeUpApiProductResponse> makeUpApiProductList;
    SwipeRefreshLayout swipe_refresh_view;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycle_view);
        swipe_refresh_view=findViewById(R.id.swipe_refresh_view);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.show();

        ApiRequestManager requestManager = new ApiRequestManager(MainActivity.this);
        requestManager.getProductList(listener,null);
        //SendRequestAndResponse();
        swipe_refresh_view.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               // SendRequestAndResponse();
                requestManager.getProductList(listener,null);
            }
        });

    }

   private final onFetchDataListener listener = new onFetchDataListener() {
       @Override
       public void onFetchData(ArrayList<MakeUpApiProductResponse> responseArrayList, String message) {
           if(responseArrayList==null){
               Toast.makeText(MainActivity.this,"No Data Found",Toast.LENGTH_LONG).show();
               Log.e("No Result Found","List empty");
               swipe_refresh_view.setRefreshing(false);
               progressDialog.dismiss();

           }else {
               ArrayList<MakeUpApiProductResponse> uniqueBrandList = removeDuplicates(responseArrayList);
               BrandRecycleView adapter=new BrandRecycleView(MainActivity.this, uniqueBrandList);

               // setting grid layout manager to implement grid view.
               // in this method '2' represents number of columns to be displayed in grid view.
               GridLayoutManager layoutManager=new GridLayoutManager(MainActivity.this,2);

               // at last set adapter to recycler view.
               recyclerView.setLayoutManager(layoutManager);
               recyclerView.setAdapter(adapter);
               adapter.setClickListener(MainActivity.this::onBrancClicked);
               swipe_refresh_view.setRefreshing(false);
               progressDialog.dismiss();
           }
       }

       @Override
       public void onError(String message) {
           Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
           Log.e("Mainactivity error",message);
           swipe_refresh_view.setRefreshing(false);
           progressDialog.dismiss();
       }
   };
    private void SendRequestAndResponse() {
        Retrofit retrofit =new Retrofit.Builder().baseUrl("https://makeup-api.herokuapp.com/api/").
                addConverterFactory(GsonConverterFactory.create()).build();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<ArrayList<MakeUpApiProductResponse>> makeupApiResponse =retrofitInterface.callMakeUp(null);
        Log.e("**** URL ****",makeupApiResponse.request().url().toString());

        makeupApiResponse.enqueue(new Callback<ArrayList<MakeUpApiProductResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<MakeUpApiProductResponse>> call, Response<ArrayList<MakeUpApiProductResponse>>response) {

                //Log.e("*** API Response ***",response.body().toString());

               ArrayList<MakeUpApiProductResponse> uniqueBrandList = removeDuplicates(response.body());
//                for (int i=0;i<response.body().size();i++){
//                    if(response.body().get(i).getBrand()!=null) {
//                        Log.d("*** BRAND ****", response.body().get(i).getBrand());
//                    }
//                }

              //  List <MakeUpApiProductResponse> makeUpApiProductResponsesList = removeDuplicates((ArrayList<MakeUpApiProductResponse>) response.body());
//                for (int i=0;i<makeUpApiProductResponsesList.size();i++){
//                    Log.d("*** BRAND ****",makeUpApiProductResponsesList.get(i).getBrand());
//                }
                 //  List <MakeUpApiProductResponse> makeUpApiProductResponsesList = response.body();
                // added data from arraylist to adapter class.

                BrandRecycleView adapter=new BrandRecycleView(MainActivity.this, uniqueBrandList);

                // setting grid layout manager to implement grid view.
                // in this method '2' represents number of columns to be displayed in grid view.
                GridLayoutManager layoutManager=new GridLayoutManager(MainActivity.this,2);

                // at last set adapter to recycler view.
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
                adapter.setClickListener(MainActivity.this::onBrancClicked);
                swipe_refresh_view.setRefreshing(false);
                progressDialog.dismiss();
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(Call<ArrayList<MakeUpApiProductResponse>> call, Throwable t) {
                Log.e("*** API ResponseError ***",t.getMessage().toString());
                Toast.makeText(MainActivity.this, "Error: "+t.getMessage().toString(),Toast.LENGTH_SHORT).show();
                swipe_refresh_view.setRefreshing(false);
                progressDialog.dismiss();

            }
        });

    }

    public ArrayList<MakeUpApiProductResponse> removeDuplicates(ArrayList<MakeUpApiProductResponse> list){
       Log.d("*** list ****",list.get(0).toString());

        // Java 8 - Collector.toCollection()
        Set<MakeUpApiProductResponse> uniqueBrandList = list
                .stream() // get stream for original list
                .collect(Collectors.toCollection(//distinct elements stored into new SET
                        () -> new TreeSet<>(Comparator.comparing(MakeUpApiProductResponse::getBrand,Comparator.nullsFirst(Comparator.naturalOrder())))
                ));
        final ArrayList<MakeUpApiProductResponse> newList = new ArrayList(uniqueBrandList);

        for (int i=0;i<newList.size();i++){
            if(newList.get(i).getBrand()!=null) {
                 Log.d("*** BRAND ****", newList.get(i).getBrand().toString());
            }
        }

//        for (int i=0;i<list.size();i++){
//            if(list.get(i).getBrand()==null) {
//                Log.e("*** BRAND ****", list.get(i).toString());
//            }
//        }
//        Set<MakeUpApiProductResponse> set = new TreeSet(new Comparator<MakeUpApiProductResponse>() {
//
//            @Override
//            public int compare(MakeUpApiProductResponse o1, MakeUpApiProductResponse o2) {
//
//
//                    if  (o1.getBrand()!=null && o1.getBrand().equals(o2.getBrand()!=null)) {
//                        return 0;
//                    }
//                    return 1;
//
//                }
//
//        });
//        set.addAll(list);

        for (int i=0;i<newList.size();i++) {
            if (newList.get(i).getBrand() == null) {
//                Log.e("*** BRAND ****", list.get(i).toString());
                newList.remove(i);
            }
        }
        makeUpApiProductList=newList;
        return newList;
    }

    @Override
    public void onBrancClicked(MakeUpApiProductResponse makeUpApiProductResponse) {

        Log.e("*** Clicked Brand Details ***",makeUpApiProductResponse.getBrand());
        Toast.makeText(MainActivity.this, makeUpApiProductResponse.getBrand(),Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this,BrandDetails.class).putExtra("data",makeUpApiProductResponse));
    }
}