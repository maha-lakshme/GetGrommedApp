package in.nandhu.getgrommedapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import in.nandhu.getgrommedapp.Models.MakeUpApiProductResponse;

public class BrandDetailaAdapter extends RecyclerView.Adapter<BrandDetailaAdapter.viewHolder>  {
    Context context;
    ArrayList<MakeUpApiProductResponse> makeUpApiProducArrayList;


    public BrandDetailaAdapter(Context context, ArrayList<MakeUpApiProductResponse> makeUpApiProducArrayList) {
        this.context = context;
        this.makeUpApiProducArrayList = makeUpApiProducArrayList;

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.brand_details_list,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.text_brand.setText("category"+" "+makeUpApiProducArrayList.get(position).getCategory());
        holder.text_brand_product_name.setText(makeUpApiProducArrayList.get(position).getName());
        holder.text_price.setText("$"+ " "+makeUpApiProducArrayList.get(position).getPrice());
        Picasso.get().load(makeUpApiProducArrayList.get(position).getImage_link()).placeholder(R.drawable.makeup_placeholder4).into(holder.img_product);
    }

    @Override
    public int getItemCount() {
        return makeUpApiProducArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView text_brand,text_brand_product_name,text_price;
        ImageView img_product;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            text_brand=itemView.findViewById(R.id.text_brand_product_name);
            text_brand_product_name=itemView.findViewById(R.id.text_brand);
            text_price=itemView.findViewById(R.id.text_price);
            img_product=itemView.findViewById(R.id.img_product);
            itemView.setTag(itemView);

        }
    }
}
