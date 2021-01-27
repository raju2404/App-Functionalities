package com.basketpal.loginscreen_dec2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
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

public class ExitPass extends AppCompatActivity {
    private RequestQueue requestQueue;
    private static final String url= "https://scanifyapi.herokuapp.com/getRatingItems/" ;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    TextView Itemscount,Billnumber;
    private List<InvoiceItem> listitems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_exit_pass);
        GlobalClass globalClass = (GlobalClass) getApplicationContext();
        final String UserIDName= globalClass.getUserID();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listitems = new ArrayList<>();
        int paid =1 ;
        CreateInvoice(UserIDName, paid );
        loadRecyclerviewData(UserIDName);
        Itemscount = (TextView) findViewById(R.id.countofitems);
        Billnumber = (TextView) findViewById(R.id.Billnumber);
    }

    private void loadRecyclerviewData(String UserIDName) {
        final ProgressDialog progressDialog = new ProgressDialog(ExitPass.this);
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
                                InvoiceItem item = new InvoiceItem(
                                        o.getInt("row_id"),
                                        o.getString("Productname"),
                                        o.getInt("Quantity"),
                                        o.getInt("price"),
                                        o.getInt("InvoiceNumber")
                                );
                                listitems.add(item);
                                Itemscount.setText( "Number of Items purchased : " + String.valueOf( listitems.size()));
                                Billnumber.setText("Invoice number : " + String.valueOf(item.getInvoiceNumber() ) ) ;
                            }
                            adapter = new InvoiceItemAdapter(listitems, getApplicationContext());
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
        RequestQueue requestQueue = Volley.newRequestQueue(ExitPass.this);
        requestQueue.add(stringRequest);
    }

    private void CreateInvoice(String UserIDName , int paid ) {
        try {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
            String updatepaidURL="https://scanifyapi.herokuapp.com//updatePaid";
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("Username", UserIDName);
            jsonBody.put("paid", paid );
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.PUT, updatepaidURL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i("VOLLEY", response);
                            Toast.makeText(ExitPass.this, "Item purchased auccessfully", Toast.LENGTH_LONG).show();
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

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}