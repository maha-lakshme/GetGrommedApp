package in.nandhu.getgrommedapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import in.nandhu.getgrommedapp.Models.MakeUpApiProductResponse;
import in.nandhu.getgrommedapp.Models.onFetchDataListener;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class BrandDetails extends AppCompatActivity {
    MakeUpApiProductResponse selectedMakeupBrand;
    RecyclerView recyclerViewProduct;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_details);
        progressDialog = new ProgressDialog(BrandDetails.this);
        recyclerViewProduct =findViewById(R.id.recycle_view_product);
        selectedMakeupBrand = (MakeUpApiProductResponse) getIntent().getSerializableExtra("data");
        Log.d("Selected Product", selectedMakeupBrand.getName());

        ApiRequestManager requestManager = new ApiRequestManager(BrandDetails.this);
        requestManager.getProductList(listener,selectedMakeupBrand.getBrand());
        progressDialog.setTitle("Loading");
        progressDialog.show();
    }


       private final onFetchDataListener listener = new onFetchDataListener() {
           @Override
           public void onFetchData(ArrayList<MakeUpApiProductResponse> responseArrayList, String message) {
               showProductList(responseArrayList);
               progressDialog.dismiss();
           }

           @Override
           public void onError(String message) {
               Toast.makeText(BrandDetails.this,"Error occured"+message,Toast.LENGTH_LONG).show();
               progressDialog.dismiss();
           }
       };

    private void showProductList(ArrayList<MakeUpApiProductResponse> responseArrayList) {
        Log.d("Response List ****",responseArrayList.get(0).toString());
         BrandDetailaAdapter adapter = new BrandDetailaAdapter(BrandDetails.this,responseArrayList);
        recyclerViewProduct.setLayoutManager(new GridLayoutManager(this,2));
         recyclerViewProduct.setAdapter(adapter);


    }

}
