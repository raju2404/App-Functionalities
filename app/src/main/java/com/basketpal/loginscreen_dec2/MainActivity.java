package com.basketpal.loginscreen_dec2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText UserName,Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        TextView RG=(TextView) findViewById(R.id.Register);
        CardView CV = (CardView) findViewById(R.id.CardView);
        UserName= findViewById(R.id.UserName);
        Password = findViewById(R.id.Password);

        RG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });

        CV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Usernametext=UserName.getText().toString();
                final String Passwordtext= Password.getText().toString();
                if(Usernametext.isEmpty() || Passwordtext.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Fill all the fields",Toast.LENGTH_SHORT).show();
                } else {
                    //Perform query
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    final UserDao userDao= userDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                        UserEntity userEntity = userDao.login(Usernametext,Passwordtext);
                        if (userEntity == null )
                        {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(),"Invalid credentials",Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            String name=userEntity.Username;
                            startActivity(new Intent(MainActivity.this,HomeScreen.class)
                            .putExtra( "name", name ));


                        }
                        }
                    }).start();
                }


            }
        });
    }


}