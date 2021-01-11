package android.example.loginscreen_dec2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
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

public class ViewCart extends AppCompatActivity {



    private static final String url= "https://scanifyapi.herokuapp.com/getCartItems/" ;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    TextView txtTotalamount;
    private RequestQueue requestQueue;
    Button btn_checkout;

    // private CartItemsAdapter adapter;

    private List<CartListItems> listitems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_view_cart);
        GlobalClass globalClass = (GlobalClass) getApplicationContext();
        final String UserIDName= globalClass.getUserID();
        txtTotalamount= findViewById(R.id.txtTotalamount);
        btn_checkout = findViewById(R.id.btn_checkout);
        try {
            int rowid = getIntent().getIntExtra("row_id", 0);
            //Toast.makeText(ViewCart.this, String.valueOf(rowid), Toast.LENGTH_LONG).show();
            int Qty = getIntent().getIntExtra("Quantity", 0);
            //Toast.makeText(ViewCart.this, String.valueOf(Qty), Toast.LENGTH_LONG).show();
            String purchased = getIntent().getStringExtra("purchased");
            //Toast.makeText(ViewCart.this, purchased, Toast.LENGTH_LONG).show();
            btn_checkout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ViewCart.this, Payment.class)
                            .putExtra("Totalamount", txtTotalamount.getText().toString());
                    startActivity(intent);
                }
            });
            if (rowid != 0 && purchased != "1") {
                DeleteCartItem(rowid, purchased);
            }
            if (rowid != 0 && Qty != 0) {
                UpdatecartItem(rowid, Qty);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        listitems = new ArrayList<>();
        loadRecyclerviewData(UserIDName);



    }

    private void DeleteCartItem(int row_id, String purchased) {
        try{
            requestQueue = Volley.newRequestQueue(getApplicationContext());
            String updateURL="https://scanifyapi.herokuapp.com/updatePurchased";
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("row_id", row_id);
            jsonBody.put("purchased", purchased);
            final String requestBody = jsonBody.toString();
            StringRequest stringRequest = new StringRequest(Request.Method.PUT, updateURL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i("VOLLEY", response);
                            Toast.makeText(ViewCart.this, "Item discarded from the Cart successfully", Toast.LENGTH_LONG).show();
                            //loadRecyclerviewData();

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

    }

    private void UpdatecartItem(int row_id, int Quantity) {
        try{
            requestQueue = Volley.newRequestQueue(getApplicationContext());
            String updateURL="https://scanifyapi.herokuapp.com/updateQty";
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("row_id", row_id);
            jsonBody.put("Quantity", Quantity);
            final String requestBody = jsonBody.toString();
            StringRequest stringRequest = new StringRequest(Request.Method.PUT, updateURL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i("VOLLEY", response);
                            Toast.makeText(ViewCart.this, "Quantity updated successfully", Toast.LENGTH_LONG).show();
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

    }
    private void loadRecyclerviewData(String UserIDName) {
        final ProgressDialog progressDialog = new ProgressDialog(ViewCart.this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();
        String urlnew = url .concat(UserIDName);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlnew,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray array= jsonObject.getJSONArray("myCollection");

                            for (int i= 0; i<array.length() ; i++) {
                                JSONObject o = array.getJSONObject(i);
                                CartListItems item = new CartListItems(
                                        o.getInt("row_id"),
                                        o.getString("Productname"),
                                        o.getInt("Quantity"),
                                        o.getInt("price"),
                                        o.getInt("purchased")

                                );
                                listitems.add(item);
                                int total = 0;
                                for(int  k = 0; k < listitems.size(); k++){

                                    total = total + (listitems.get(k).getprice() * listitems.get(k).getQuantity() );
                                }
                                txtTotalamount.setText( String.valueOf( total));

                            }


                            adapter = new CartItemsAdapter(listitems, getApplicationContext());
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
        RequestQueue requestQueue = Volley.newRequestQueue(ViewCart.this);
        requestQueue.add(stringRequest);

    }


}