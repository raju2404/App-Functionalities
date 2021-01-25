package com.basketpal.loginscreen_dec2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartItemsAdapter extends RecyclerView.Adapter<CartItemsAdapter.ViewHolder>{
    private List<CartListItems> listItems;
    private Context context;

    public CartItemsAdapter(List<CartListItems> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public CartItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_cart_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartItemsAdapter.ViewHolder holder, int position) {
        final CartListItems listItem = listItems.get(position);

        //holder.txtrowid.setText(String.valueOf(listItem.getRow_id()) );
        holder.txtProduct.setText(listItem.getProductname());
        holder.txtQuantity.setText( String.valueOf(listItem.getQuantity()) );
        holder.txtPrice.setText("Item Price : " +  String.valueOf(listItem.getprice()) );
        holder.txtpurchased.setText("Total Price : " + String.valueOf(listItem.getQuantity() * listItem.getprice()) );
        holder.btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int rowid=listItem.getRow_id();
                int Qty = Integer.parseInt(holder.txtQuantity.getText().toString() ) ;
                Intent intent=new Intent(v.getContext(),ViewCart.class)
                        .putExtra("row_id",rowid)
                        .putExtra("Quantity",Qty);
                v.getContext().startActivity(intent);
            }
        });
        holder.btn_discard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rowid=listItem.getRow_id();
                String purchased = String.valueOf( 0 );
                Intent intent=new Intent(v.getContext(),ViewCart.class)
                        .putExtra("row_id",rowid)
                        .putExtra("purchased",purchased);

                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtrowid;
        public TextView txtProduct;
        public EditText txtQuantity;
        public TextView txtPrice;
        public TextView txtpurchased;
        public Button btn_save;
        public Button btn_discard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtrowid = (TextView) itemView.findViewById(R.id.txtrowid);
            txtProduct = (TextView) itemView.findViewById(R.id.txtProduct);
            txtQuantity = (EditText) itemView.findViewById(R.id.txtQuantity);
            txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);
            txtpurchased = (TextView) itemView.findViewById(R.id.txtpurchased);
            btn_save = (Button) itemView.findViewById(R.id.btn_save);
            btn_discard = (Button) itemView.findViewById(R.id.btn_discard);
        }
    }
}
