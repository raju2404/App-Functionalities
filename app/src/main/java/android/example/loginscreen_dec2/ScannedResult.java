package android.example.loginscreen_dec2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ScannedResult extends AppCompatActivity  {
    private RequestQueue requestQueue;
    TextView scanresulttext;
    Button Backbutton,AddtoCart,ViewCart;
    // private static final String url= "http://10.0.2.2:5000/Product";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private ProductScanAdapter mAdapter;

    //EditText txtQuantity;
    private List<ProductListItem> listitems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanned_result);
        GlobalClass globalClass = (GlobalClass) getApplicationContext();
        final String UserIDName= globalClass.getUserID();

        try {

            String productnamedisplay = getIntent().getStringExtra("ProductName");
            Toast.makeText(ScannedResult.this, productnamedisplay, Toast.LENGTH_LONG).show();
            //String pricedisplay = getIntent().getExtras().get("Price").toString();
           // int pricedisplay = (int) getIntent().getExtras().get("Price");
           int pricedisplay=getIntent().getIntExtra("Price",0);
            Toast.makeText(ScannedResult.this,String.valueOf(pricedisplay), Toast.LENGTH_LONG).show();
            //String tempvar= txtQuantity.getText().toString();
            //int Quantity = Integer.parseInt(tempvar);
            int Quantity = 100;
            final int purchased = 0;
            if(productnamedisplay!=null && pricedisplay!=0) {
                AddProducttoCart(UserIDName, productnamedisplay, Quantity, pricedisplay,purchased );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        //AddProducttoCart(UserIDName, prodcutnamedisplay, Quantity, pricedisplay,purchased );

        //txtQuantity= findViewById(R.id.txtQuantity);
        Backbutton = findViewById(R.id.Backbutton);
        AddtoCart = findViewById(R.id.AddtoCart);
        ViewCart = findViewById(R.id.ViewCart);
        scanresulttext = findViewById(R.id.scanresulttext);
        final String resultcode=getIntent().getStringExtra("resultcode");
        scanresulttext.setText(UserIDName);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listitems = new ArrayList<>();
        if(resultcode!=null) {
            loadRecyclerviewData(resultcode.toString());
        }

    }



    private void AddProducttoCart(String Username , String Productname ,int Quantity, int price, int purchased ) {
        try{

            requestQueue = Volley.newRequestQueue(getApplicationContext());
            String URL="http://cd5473ae11c5.ngrok.io/addProduct";
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("Username", Username);
            jsonBody.put("Productname", Productname);
            jsonBody.put("Quantity", String.valueOf(Quantity));
            jsonBody.put("price",String.valueOf(price));
            jsonBody.put("purchased", String.valueOf(purchased));
            final String requestBody = jsonBody.toString();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i("VOLLEY", response);
                            Toast.makeText(ScannedResult.this, "Product added to Cart successfully", Toast.LENGTH_LONG).show();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                }
            }
            )
            {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }
                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);

                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            } ;
            requestQueue.add(stringRequest);


        } catch (Exception e) {
            e.printStackTrace();
        }



        //jsonBody.put("username",globalClass.getUserID());
    }

    private void loadRecyclerviewData(String resultcode) {
        //String resultcode="8901058138054";

        String url= "http://cd5473ae11c5.ngrok.io/Product/" .concat(resultcode);
        //Toast.makeText(ScannedResult.this, url, Toast.LENGTH_SHORT).show();
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