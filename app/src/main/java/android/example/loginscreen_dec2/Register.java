package android.example.loginscreen_dec2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText Username,Password,Retypepassword;
    Button Register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Username = findViewById(R.id.Username);
        Password = findViewById(R.id.TypePassword);
        Retypepassword = findViewById(R.id.RetypePassword);
        Register = findViewById(R.id.Register);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creating User entity
                final UserEntity userEntity = new UserEntity();
                userEntity.setUsername(Username.getText().toString());
                userEntity.setPassword(Password.getText().toString());
                if(validateInput(userEntity)) {
                    // Doing insert operation
                    if (!Password.getText().toString().equals(Retypepassword.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Password not matching", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    final UserDao userDao = userDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            userDao.registerUser(userEntity);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Registration successful", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).start();
                }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Fill all fields",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private Boolean validateInput(UserEntity userEntity){
        if(userEntity.getUsername().isEmpty() || userEntity.getPassword().isEmpty())
        {
            return false;
        }
//        else if (! Password.getText().toString() . equals( Retypepassword.getText().toString()))
//        {
//            return false;
//        }
        return true;
    }
}