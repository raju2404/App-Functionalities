package android.example.loginscreen_dec2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class ScannedResult extends AppCompatActivity {
    TextView scanresulttext;
    // private static final String url= "http://10.0.2.2:5000/Product";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private ProductScanAdapter mAdapter;
    //EditText searchtext;
    EditText txtQuantity;
    private List<ProductListItem> listitems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanned_result);

        txtQuantity= findViewById(R.id.txtQuantity);

        scanresulttext = findViewById(R.id.scanresulttext);
        final String resultcode=getIntent().getStringExtra("resultcode");
        scanresulttext.setText(resultcode);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listitems = new ArrayList<>();
        loadRecyclerviewData(resultcode.toString());
    }
    private void loadRecyclerviewData(String resultcode) {
        //String resultcode="8901058138054";

        String url= "http://430e7b401f39.ngrok.io/Product/" .concat(resultcode);
        Toast.makeText(ScannedResult.this, url, Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray array= jsonObject.getJSONArray("myCollection");

                            for (int i= 0; i<array.length() ; i++) {
                                JSONObject o = array.getJSONObject(i);
                                ProductListItem item = new ProductListItem(
                                        o.getString("productname"),
                                        o.getInt("price")
                                );
                                listitems.add(item);

                            }
                            adapter = new ProductScanAdapter(listitems, getApplicationContext());
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e){
                            e.printStackTrace();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //Toast.makeText(getApplicationContext(),)

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(ScannedResult.this);
        requestQueue.add(stringRequest);

    }
}