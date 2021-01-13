package android.example.loginscreen_dec2;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
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
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class SelfBilling extends AppCompatActivity {

    Button ScanProduct;
    private ZXingScannerView scannerView;
    TextView textview;
    ImageView img_viewcart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_self_billing);
        ScanProduct = findViewById(R.id.Scan);

        textview = findViewById(R.id.Displaytext);
        img_viewcart = findViewById(R.id.img_viewcart);
        img_viewcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(SelfBilling.this,ViewCart.class);
                //startActivity(intent);

            }
        });


    }

    public void ScanCode(View view) {

        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(SelfBilling.this,"Permission granted",Toast.LENGTH_SHORT).show();
                scannerView = new ZXingScannerView(SelfBilling.this);
                scannerView.setResultHandler(new ZXingScannerResultHandler());
                setContentView(scannerView);
                scannerView.startCamera();
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Toast.makeText(SelfBilling.this,"Permission not granted",Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.with(SelfBilling.this)
                .setPermissionListener(permissionListener)
                .setPermissions(Manifest.permission.CAMERA,Manifest.permission.INTERNET)
                .check();
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

            Toast.makeText(SelfBilling.this,resultCode,Toast.LENGTH_SHORT).show();
                        //setContentView(R.layout.activity_self_billing);
            setContentView(R.layout.activity_self_billing);
            scannerView.stopCamera();

            startActivity(new Intent(SelfBilling.this,ScannedResult.class)

                    .putExtra("resultcode",resultCode));

        }
    }
}