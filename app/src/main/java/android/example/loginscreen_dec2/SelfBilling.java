package android.example.loginscreen_dec2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonIOException;
import com.google.zxing.Result;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigInteger;

import cz.msebera.android.httpclient.Header;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class SelfBilling extends AppCompatActivity {

    Button ScanProduct;
    private ZXingScannerView scannerView;
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_billing);
        ScanProduct = findViewById(R.id.Scan);

        textview = findViewById(R.id.Displaytext);
    }

    public void ScanCode(View view) {
        scannerView = new ZXingScannerView(this);
        scannerView.setResultHandler(new ZXingScannerResultHandler());
        setContentView(scannerView);
        scannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    class ZXingScannerResultHandler implements ZXingScannerView.ResultHandler {
        @Override
        public void handleResult(com.google.zxing.Result result) {
            String resultCode = result.getText().toString();
            //BigInteger numBig = new BigInteger(resultCode);

            String barcode="8901012116814";
            //10.0.2.2
           String url="http://7a053042685e.ngrok.io/Product/" .concat(barcode);
           // String url="http://7a053042685e.ngrok.io/Product/8901012116814" ;
            //String url="http://127.0.0.1:5000/emp/1" ;

            new AsyncHttpClient().get(url, new JsonHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                    try {
                        JSONArray jsonarray=response.getJSONArray("myCollection");
                        for (int i =0 ; i < jsonarray.length(); i++)
                        {
                            JSONObject obj= jsonarray.getJSONObject(i);
                            textview.setText(obj.getString("productname"));
                           Toast.makeText(SelfBilling.this, obj.getString("productname"), Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(SelfBilling.this, "Step2", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                    //String str= new String(responseBody);
                    //textview.setText(str);
                }


                public void onFailure(int statusCode, Header[] headers, JSONObject response, Throwable error) {
                    Toast.makeText(SelfBilling.this, "Error", Toast.LENGTH_SHORT).show();
                    textview.setText("Error in calling APi");
                }
            });

            //setContentView(R.layout.activity_self_billing);
            setContentView(R.layout.activity_self_billing);
            scannerView.stopCamera();

        }
    }
}