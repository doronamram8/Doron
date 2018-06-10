package com.example.madaim.doron;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity implements SharedPreferences.OnSharedPreferenceChangeListener{
    private final int MY_CALL_REQUEST=1;
    private EditText name;
    private EditText numpho;
    private Button btn;
    private ListView list;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;
    View item;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        String input = settings.getString("edit_text_preference_1", "guest");
        name = (EditText) findViewById(R.id.name);
        numpho = (EditText) findViewById(R.id.numphone);
        //btn = (Button) findViewById(R.id.button);
         list=(ListView)findViewById(R.id.lv);
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.activity_main, arrayList);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (checkSelfPermission( Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED)
                {
                    EditText ed = (EditText) findViewById(R.id.numphone);
                    String number = ed.getText().toString();
                    CallPhone(number);
                }
                else
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE},MY_CALL_REQUEST);


            }
        });
       // btn.setOnClickListener(new View.OnClickListener() {
           // @Override
         //   public void onClick(View view) {

            //     arrayList.add(name.getText().toString());
            //    arrayList.add(numpho.getText().toString());
              //  adapter.notifyDataSetChanged();
         //   }
   //     });

    }

            public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.new1:

                MyDialog.newInstance(MyDialog.ADDING_DIALOG).show(getFragmentManager(), "precisin");

                return true;

        }
        return super.onOptionsItemSelected(item);
     }
    @SuppressWarnings({"MissingPermission"})
    private void CallPhone(String Phonenumber){
        String number =  Phonenumber ;
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" +number));
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case MY_CALL_REQUEST:{
                if(grantResults.length>0&&
                        permissions[0].equals(Manifest.permission.CALL_PHONE)&&
                        grantResults[0]==PackageManager.PERMISSION_GRANTED) {

                }

                break;
            }
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        String key = s;
        if (key.equals("Savelist")) {
            SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
            if (!settings.getBoolean("Savelist", false)) {
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("Added phone numbers", "");
                editor.commit();

            }

        }

    }
}
