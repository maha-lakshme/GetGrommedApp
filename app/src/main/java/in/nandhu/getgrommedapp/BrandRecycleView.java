package in.nandhu.getgrommedapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import in.nandhu.getgrommedapp.Models.MakeUpApiProductResponse;

public class BrandRecycleView extends RecyclerView.Adapter<BrandRecycleView.BrandViewHolder> {
    Context context;
    List<MakeUpApiProductResponse> makeUpApiProductResponseList;
    OnBrandItemClickListener clickListener;

    public BrandRecycleView(Context context, List<MakeUpApiProductResponse> makeUpApiProductResponseList) {
        this.context = context;
        this.makeUpApiProductResponseList = makeUpApiProductResponseList;
    }

    @NonNull
    @Override
    public BrandViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.makeup_brand,parent,false);
        return new BrandViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandViewHolder holder, int position) {

        holder.text_brand.setText(makeUpApiProductResponseList.get(position).getBrand());
        Picasso.get().load(makeUpApiProductResponseList.get(position).getImage_link()).placeholder(R.drawable.makeup_placeholder4).into(holder.img_brand);
    }


    public void setClickListener(OnBrandItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }


    @Override
    public int getItemCount() {
        return makeUpApiProductResponseList.size();
    }

    public class BrandViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView text_brand;
        ImageView img_brand;
        public BrandViewHolder(@NonNull View itemView) {
            super(itemView);
            text_brand=itemView.findViewById(R.id.text_brand_name);
            img_brand=itemView.findViewById(R.id.img_brand);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            clickListener.onBrancClicked(makeUpApiProductResponseList.get(getAdapterPosition()));
        }
    }
}
