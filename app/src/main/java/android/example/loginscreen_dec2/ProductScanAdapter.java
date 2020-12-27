package android.example.loginscreen_dec2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductScanAdapter  extends RecyclerView.Adapter<ProductScanAdapter.ViewHolder>{

    private List<ProductListItem> listItems;
    private Context context;

    public ProductScanAdapter(List<ProductListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }


    @NonNull
    @Override
    public ProductScanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_scan_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductScanAdapter.ViewHolder holder, int position) {
        ProductListItem listItem = listItems.get(position);

        holder.txtProduct.setText(listItem.getProductname());
        holder.txtprice.setText("Price : " + listItem.getprice());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtProduct;
        public TextView txtprice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtProduct = (TextView) itemView.findViewById(R.id.txtProduct);
            txtprice = (TextView) itemView.findViewById(R.id.txtPrice);
        }
    }
}
