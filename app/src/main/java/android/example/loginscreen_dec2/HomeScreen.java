package android.example.loginscreen_dec2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toast;
public class HomeScreen extends AppCompatActivity {

    TextView tname;
    Button ShoppingList,SearchProducts,SelfBill,Notify,Feedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        tname = findViewById(R.id.name);
        final String name=getIntent().getStringExtra("name");
        tname.setText(name);

        ShoppingList = findViewById(R.id.ShoppingList);
        SearchProducts = findViewById(R.id.SearchProducts);
        SelfBill= findViewById(R.id.SelfBill);
        Notify= findViewById(R.id.Notify);
        Feedback= findViewById(R.id.Feedback);

        Notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                String[] to={"raju.imp25@gmail.com"};
                intent.putExtra(Intent.EXTRA_EMAIL,to);
                intent.putExtra(Intent.EXTRA_SUBJECT,"Requisition from User : " + name);
                intent.putExtra(Intent.EXTRA_TEXT,"Type the Items you want us to procure in the below: ");
                intent.setType("message/rfc822");
                Intent chooser=Intent.createChooser(intent,"Send Email");
                startActivity(chooser);
                //Toast.makeText(getApplicationContext(),"Fill all the fields",Toast.LENGTH_SHORT).show();

            }
        });

    }
}