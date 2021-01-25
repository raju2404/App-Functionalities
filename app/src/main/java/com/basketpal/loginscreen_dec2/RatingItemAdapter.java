package com.basketpal.loginscreen_dec2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RatingItemAdapter extends RecyclerView.Adapter<RatingItemAdapter.ViewHolder>  {

    private List<RatingItem> listItems;
    private Context context;

    public RatingItemAdapter(List<RatingItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public RatingItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_rating_item,parent,false);
        return new RatingItemAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final RatingItemAdapter.ViewHolder holder, int position) {
        final RatingItem listItem = listItems.get(position);

        //holder.txtrowid.setText(String.valueOf(listItem.getRow_id()) );
        holder.product_tv.setText(String.valueOf(listItem.getProductname() ));
        //holder.id_tv.setText(String.valueOf(listItem.getid() ));

           }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{
        public TextView id_tv;
        public TextView product_tv;
       // public RatingBar ratingbar;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id_tv = (TextView) itemView.findViewById(R.id.id_tv);
            product_tv = (TextView) itemView.findViewById(R.id.product_tv);
           // ratingbar = (RatingBar) itemView.findViewById(R.id.ratingbar);

        }
    }
}
