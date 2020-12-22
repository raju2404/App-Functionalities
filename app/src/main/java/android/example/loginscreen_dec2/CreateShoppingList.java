package android.example.loginscreen_dec2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateShoppingList extends AppCompatActivity {

    ListView listview;
    Button Addbutton,Clearbutton;
    EditText GetValue;
    String[] ListElements = new String[] {

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_shopping_list);

        listview = (ListView) findViewById(R.id.listView1);
        Addbutton = (Button) findViewById(R.id.AddItems);
        GetValue = (EditText) findViewById(R.id.editText1);
        Clearbutton = (Button) findViewById(R.id.ClearItems);
        //populating listitems
        final List< String > ListElementsArrayList = new ArrayList< String >
                (Arrays.asList(ListElements));


        final ArrayAdapter< String > adapter = new ArrayAdapter < String >
                (CreateShoppingList.this, android.R.layout.simple_list_item_1,
                        ListElementsArrayList);

        listview.setAdapter(adapter);

        Addbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ListElementsArrayList.add(GetValue.getText().toString());
                adapter.notifyDataSetChanged();
                GetValue.setText("");
            }
        });
        Clearbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListElementsArrayList.clear();
                adapter.notifyDataSetChanged();
            }
        });



    }
}