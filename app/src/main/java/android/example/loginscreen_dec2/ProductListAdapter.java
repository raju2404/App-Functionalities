package android.example.loginscreen_dec2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;

    public ProductListAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }
    @NonNull
    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ViewHolder holder, int position) {
        ListItem listItem = listItems.get(position);

        holder.txtProduct.setText(listItem.getProductname());
        holder.txtLocation.setText("Location : " + listItem.getLocation());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }
    public void filterList(ArrayList<ListItem> filteredList) {
        listItems = filteredList;
        notifyDataSetChanged();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtProduct;
        public TextView txtLocation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtProduct = (TextView) itemView.findViewById(R.id.txtProduct);
            txtLocation = (TextView) itemView.findViewById(R.id.txtLocation);
        }
    }
}
