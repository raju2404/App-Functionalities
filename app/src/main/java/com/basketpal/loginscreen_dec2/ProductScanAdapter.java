package com.basketpal.loginscreen_dec2;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
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
         final ProductListItem listItem = listItems.get(position);

        holder.txtProduct.setText(listItem.getProductname());
        holder.txtprice.setText("Price : " + listItem.getprice());


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pname=listItem.getProductname() ;
                //String price = listItem.getprice();


                Intent intent=new Intent(v.getContext(),ScannedResult.class)
                        .putExtra("ProductName",pname)
                        .putExtra("Price",listItem.getprice());
                v.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtProduct;
        public TextView txtprice;
        public LinearLayout linearLayout;
        //public EditText txtQuantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtProduct = (TextView) itemView.findViewById(R.id.txtProduct);
            txtprice = (TextView) itemView.findViewById(R.id.txtPrice);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
            //txtQuantity = (EditText) itemView.findViewById(R.id.txtQuantity);

        }
    }
}
