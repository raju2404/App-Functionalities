package android.example.loginscreen_dec2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HomeScreen extends AppCompatActivity {

    TextView tname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        tname = findViewById(R.id.name);
        String name=getIntent().getStringExtra("name");
        tname.setText(name);

    }
}