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
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import me.jaffar.picpo.starter.R;

public class FindsView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finds_view);
        String passedFindColour = getIntent().getExtras().getString("findColour");
        String passedFindCompany = getIntent().getExtras().getString("findCompany");
        String passedFindAddress = getIntent().getExtras().getString("findAdddress");
        String passedTypeFind = getIntent().getExtras().getString("TypeFind");
        String passedFindSpinner = getIntent().getExtras().getString("findSpinnerData");
        String passedFindUser = getIntent().getExtras().getString("findUser");
        String passedMobileNumber=getIntent().getExtras().getString("findMobileNumber");
        Log.i("Mavaxxx",passedFindColour);
        Log.i("Mavaxxx",passedFindCompany);
        Log.i("Mavaxxx",passedFindAddress);
        Log.i("Mavaxxx",passedTypeFind);
        Log.i("Mavaxxx",passedFindSpinner);
        Log.i("Mavaxxx",passedMobileNumber);
        ParseObject dataObject = new ParseObject("Find");
        dataObject.put("Color", passedFindColour);
        dataObject.put("Company", passedFindCompany);
        dataObject.put("Type", passedTypeFind);
        dataObject.put("Address", passedFindAddress);
        dataObject.put("Spinner", passedFindSpinner);
        dataObject.put("User", passedFindUser);
        dataObject.put("MobileNumber",passedMobileNumber);
        ParseACL parseACL=new ParseACL();
        parseACL.setPublicReadAccess(true);
        dataObject.setACL(parseACL);
        dataObject.saveInBackground();

        //Lost class query
        final ListView listView = (ListView) findViewById(R.id.findListView);
        final ArrayList<String> itemsList = new ArrayList<String>();
        final ArrayList<String>addressList=new ArrayList<String>();
        final ArrayList<String>colourList=new ArrayList<String>();
        final ArrayList<String>companyList=new ArrayList<String>();
        final ArrayList<String>userList=new ArrayList<String>();
        final ArrayList<String>typeList=new ArrayList<String>();
        final ArrayList<String>spinnerList=new ArrayList<String>();
        final ArrayList<String>mobileNumberList=new ArrayList<String>();
        final ArrayAdapter<String> viewAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemsList);
        ParseQuery<ParseObject> lostParseQuery = ParseQuery.getQuery("Lost");
        lostParseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    Log.i("Mava", "Grabbed " + objects.size() + " objects");
                    if (objects.size() > 0) {
                        for (ParseObject parseObject : objects) {
                            itemsList.add(String.valueOf(parseObject.get("Spinner") + " Lost"));
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
                            itemsList.add(String.valueOf(parseObject.get("Spinner")+" Found"));
                            addressList.add(String.valueOf(parseObject.get("Address")));
                            mobileNumberList.add(String.valueOf(parseObject.get("MobileNumber")));
                            colourList.add(String.valueOf(parseObject.get("Color")));
                            userList.add(String.valueOf(parseObject.get("User")));
                            spinnerList.add(String.valueOf(parseObject.get("Spinner")));
                            typeList.add(String.valueOf(parseObject.get("Type")));
                            companyList.add(String.valueOf(parseObject.get("Company")));
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
