package com.t4pita00.exercise1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> carList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getCars();

        final ListView myListView = (ListView)findViewById(R.id.listView);
        final ArrayAdapter<String> aa;
        aa = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,carList);
        myListView.setAdapter(aa);


        //Add button
        final Button addCar = (Button) findViewById(R.id.button_add);
        final EditText carTextBox = (EditText) findViewById(R.id.editText);

        addCar.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View arg0) {
                String car = carTextBox.getText().toString();
                carList.add(car);
                myListView.setAdapter(aa);
                carTextBox.setText("");
            }
        });

        //Edit button
        Button editCar = (Button) findViewById(R.id.button_edit);
        editCar.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0) {
                myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String selectedCar = carList.get(position);
                        Toast.makeText(getApplicationContext(),"Car selected : " + selectedCar, Toast.LENGTH_LONG).show();
                        carTextBox.setText(selectedCar);
                    }
                });
            }
        });

        //Remove button
        Button removeCar = (Button) findViewById(R.id.button_remove);
        removeCar.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0) {
                myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String selectedCar = carList.get(position);
                        carList.remove(position);
                        myListView.setAdapter(aa);
                        Toast.makeText(getApplicationContext(),"Removed car : " + selectedCar, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        //Activity2 button
        Button secondActivity = (Button)findViewById(R.id.button_2_activity);
        secondActivity.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(),"Second Activity Selected !", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                startActivity(intent);
            }
        });
    }

    private static void getCars(){
        carList.add("AlfaRomeo");
        carList.add("BMW");
        carList.add("Corvette");
    }
}
