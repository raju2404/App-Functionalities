package android.example.loginscreen_dec2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateShoppingList extends AppCompatActivity {

    ArrayAdapter adapter;
    ArrayList<String> Productlist;
    String[] ListElements = new String[] {

    };

    public String LISTVIEW="listview1";
    public String THONGTIN="thongtin";

    ListView listview;
    Button Addbutton,Savebutton,Clearbutton;
    EditText GetValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_create_shopping_list);


        listview = (ListView) findViewById(R.id.listView1);
        Addbutton = (Button) findViewById(R.id.AddItems);
        GetValue = (EditText) findViewById(R.id.editText1);
        Savebutton = (Button) findViewById(R.id.SaveItems);
        Clearbutton = (Button) findViewById(R.id.ClearItems);
        getList();
        //populating listitems

        final List< String > ListElementsArrayList = new ArrayList< String >
                (Arrays.asList(ListElements));


        final ArrayAdapter< String > adapter = new ArrayAdapter < String >
                (CreateShoppingList.this, R.layout.simple_list_item,
                        Productlist);
        listview.setAdapter(adapter);

        Addbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Productlist.add(GetValue.getText().toString());
                Toast.makeText(CreateShoppingList.this, GetValue.getText().toString() + " Added to your shopping list" , Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                GetValue.setText("");
            }
        });
        Savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savelist();

            }

        });



        Clearbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Productlist.clear();
                Toast.makeText(CreateShoppingList.this, "Shopping List cleared", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();

            }
        });



    }

    private void savelist() {
        SharedPreferences sharedPreferences = getSharedPreferences(LISTVIEW,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson= new Gson();
        String json= gson.toJson(Productlist);
        editor.putString(THONGTIN,json);
        editor.apply();
        Toast.makeText(CreateShoppingList.this, "Shopping List saved", Toast.LENGTH_SHORT).show();
    }

    private void getList() {
        SharedPreferences sharedPreferences = getSharedPreferences(LISTVIEW,MODE_PRIVATE);
        Gson gson= new Gson();
        String json=sharedPreferences.getString(THONGTIN,null);
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        Productlist=gson.fromJson(json,type);
        if (Productlist == null ){
            Productlist = new ArrayList<String>();
            Productlist.add("ITEM LIST : ");
        }
    }


}