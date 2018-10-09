package com.example.himanshu.locationidentifier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView InfoList;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;
    TextView check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InfoList = (ListView)findViewById(R.id.InfoList);
        arrayList = new ArrayList<>();
        adapter = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_expandable_list_item_1,arrayList);

    }

    public void AddInList(Intent data)
    {

        arrayList.add("Latitude: "+data.getStringExtra("Latitude")+" Longitude: "+data.getStringExtra("Longitude") + "\nAddress: " + data.getStringExtra("Add"));
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                InfoList.setAdapter(adapter);
                AddInList(data);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       int id = item.getItemId();

       if(id==R.id.map)
       {
           Intent intent = new Intent(MainActivity.this,MapsActivity.class);
           startActivityForResult(intent,1);
       }

        return super.onOptionsItemSelected(item);
    }
}
