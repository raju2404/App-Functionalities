package com.basketpal.loginscreen_dec2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InvoiceItemAdapter extends RecyclerView.Adapter<InvoiceItemAdapter.ViewHolder> {

    private List<InvoiceItem> listItems;
    private Context context;

    public InvoiceItemAdapter(List<InvoiceItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public InvoiceItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_invoice_item,parent,false);
        return new InvoiceItemAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceItemAdapter.ViewHolder holder, int position) {
        final InvoiceItem listItem = listItems.get(position);

        //holder.txtrowid.setText(String.valueOf(listItem.getRow_id()) );
        holder.product_tv.setText(String.valueOf(listItem.getProductname() ));
        holder.Quantity_tv.setText("Quantity :" + String.valueOf(listItem.getQuantity() ));
        holder.price_tv.setText("Total Price :" + String.valueOf(listItem.getprice() * listItem.getQuantity() ));
        holder.Invoice_tv.setText( "Invoice Number : " + String.valueOf(listItem.getInvoiceNumber() ));
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{
        public TextView id_tv;
        public TextView product_tv;
        public TextView Quantity_tv;
        public TextView price_tv;
        public TextView Invoice_tv;

        // public RatingBar ratingbar;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id_tv = (TextView) itemView.findViewById(R.id.id_tv);
            product_tv = (TextView) itemView.findViewById(R.id.product_tv);
            Quantity_tv = (TextView) itemView.findViewById(R.id.Quantity_tv);
            price_tv = (TextView) itemView.findViewById(R.id.price_tv);
            Invoice_tv = (TextView) itemView.findViewById(R.id.Invoice_tv);


        }
    }
}
