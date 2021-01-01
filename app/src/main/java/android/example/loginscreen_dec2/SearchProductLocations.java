package android.example.loginscreen_dec2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchProductLocations extends AppCompatActivity {

    private static final String url= "http://5ddc3df3db88.ngrok.io/Product";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private ProductListAdapter mAdapter;
    EditText searchtext;
    private List<ListItem> listitems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product_locations);
        searchtext=findViewById(R.id.Searchtext);
        searchtext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

                recyclerView.setAdapter(mAdapter);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        listitems = new ArrayList<>();
        loadRecyclerviewData();
    }

    private void filter(String text) {
        mAdapter = new ProductListAdapter(listitems,getApplicationContext());
        ArrayList<ListItem> filteredlist=new ArrayList<>();

        for(ListItem item:listitems){
            if(item.getProductname().toLowerCase().contains(text.toLowerCase())){
                filteredlist.add(item);
            }
        }

        mAdapter.filterList(filteredlist);
    }
    private void loadRecyclerviewData() {
        final ProgressDialog progressDialog = new ProgressDialog(SearchProductLocations.this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray array= jsonObject.getJSONArray("myCollection");

                            for (int i= 0; i<array.length() ; i++) {
                                JSONObject o = array.getJSONObject(i);
                                ListItem item = new ListItem(
                                        o.getString("productname"),
                                        o.getString("Location")
                                );
                                listitems.add(item);

                            }
                            adapter = new ProductListAdapter(listitems, getApplicationContext());
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e){
                            e.printStackTrace();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        //Toast.makeText(getApplicationContext(),)

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(SearchProductLocations.this);
        requestQueue.add(stringRequest);

    }

}