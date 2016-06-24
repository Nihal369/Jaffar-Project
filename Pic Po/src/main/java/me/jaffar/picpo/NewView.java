package me.jaffar.picpo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import me.jaffar.picpo.starter.R;

public class NewView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_view);
        final ListView listView = (ListView) findViewById(R.id.newViewList);
        final ArrayList<String> itemList = new ArrayList<String>();
        final ArrayList<String>addressList=new ArrayList<String>();
        final ArrayList<String>colourList=new ArrayList<String>();
        final ArrayList<String>companyList=new ArrayList<String>();
        final ArrayList<String>userList=new ArrayList<String>();
        final ArrayList<String>typeList=new ArrayList<String>();
        final ArrayList<String>spinnerList=new ArrayList<String>();
        final ArrayList<String>mobileNumberList=new ArrayList<String>();
        final ArrayAdapter<String> viewAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemList);
        ParseQuery<ParseObject>findParseQuery=ParseQuery.getQuery("Find");
        findParseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null)
                {
                    Log.i("Mava","Grabbed "+objects.size()+" object");
                    if(objects.size()>0)
                    {
                        for(ParseObject parseObject:objects)
                        {
                            itemList.add(String.valueOf(parseObject.get("Spinner")+" Found"));
                            addressList.add(String.valueOf(parseObject.get("Address")));
                            colourList.add(String.valueOf(parseObject.get("Color")));
                            userList.add(String.valueOf(parseObject.get("User")));
                            spinnerList.add(String.valueOf(parseObject.get("Spinner")));
                            typeList.add(String.valueOf(parseObject.get("Type")));
                            companyList.add(String.valueOf(parseObject.get("Company")));
                            mobileNumberList.add(String.valueOf(parseObject.get("MobileNumber")));
                        }
                    }
                    listView.setAdapter(viewAdapter);
                }
            }
        });

        ParseQuery<ParseObject> lostParseQuery = ParseQuery.getQuery("Lost");
        lostParseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    Log.i("Mava", "Grabbed " + objects.size() + " objects");
                    if (objects.size() > 0) {
                        for (ParseObject parseObject : objects) {
                            itemList.add(String.valueOf(parseObject.get("Spinner") + " Lost"));
                            addressList.add(String.valueOf(parseObject.get("Address")));
                            colourList.add(String.valueOf(parseObject.get("Color")));
                            userList.add(String.valueOf(parseObject.get("User")));
                            spinnerList.add(String.valueOf(parseObject.get("Spinner")));
                            typeList.add(String.valueOf(parseObject.get("Type")));
                            companyList.add(String.valueOf(parseObject.get("Company")));
                            mobileNumberList.add(String.valueOf(parseObject.get("MobileNumber")));
                        }
                    }
                    listView.setAdapter(viewAdapter);
                }

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(getApplicationContext(),DisplayInfo.class);
                i.putExtra("addressInfo",addressList.get(position));
                i.putExtra("colourInfo",colourList.get(position));
                i.putExtra("userInfo",userList.get(position));
                i.putExtra("spinnerInfo",spinnerList.get(position));
                i.putExtra("typeInfo",typeList.get(position));
                i.putExtra("companyInfo",companyList.get(position));
                i.putExtra("mobileInfo",mobileNumberList.get(position));
                startActivity(i);
            }
        });
    }
}
