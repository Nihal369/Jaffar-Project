package me.nihalismail.picpo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import me.nihalismail.picpo.starter.R;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
            String passedLostColour = getIntent().getExtras().getString("lostColour");
            String passedLostCompany = getIntent().getExtras().getString("lostCompany");
            String passedLostAddress = getIntent().getExtras().getString("lostAddress");
            String passedTypeLost = getIntent().getExtras().getString("TypeLost");
            String passedLostSpinner = getIntent().getExtras().getString("lostSpinnerData");
            String passedLostUser = getIntent().getExtras().getString("lostUser");

        


        Log.i("Mavaxxx",passedLostColour);
        Log.i("Mavaxxx",passedLostCompany);
        Log.i("Mavaxxx",passedLostAddress);
        Log.i("Mavaxxx",passedTypeLost);
        Log.i("Mavaxxx",passedLostSpinner);
        Log.i("Mavaxxx",passedLostUser);

            ParseObject dataObject = new ParseObject("Lost");
            dataObject.put("Color", passedLostColour);
            dataObject.put("Company", passedLostCompany);
            dataObject.put("Type", passedTypeLost);
            dataObject.put("Address", passedLostAddress);
            dataObject.put("Spinner", passedLostSpinner);
            dataObject.put("User", passedLostUser);
            ParseACL parseACL=new ParseACL();
            parseACL.setPublicReadAccess(true);
            dataObject.setACL(parseACL);
            dataObject.saveInBackground();

            final ListView listView = (ListView) findViewById(R.id.viewItemsList);
            final ArrayList<String> itemsList = new ArrayList<String>();
            final ArrayList<String>addressList=new ArrayList<String>();
            final ArrayList<String>colourList=new ArrayList<String>();
            final ArrayList<String>companyList=new ArrayList<String>();
            final ArrayList<String>userList=new ArrayList<String>();
            final ArrayList<String>typeList=new ArrayList<String>();
            final ArrayList<String>spinnerList=new ArrayList<String>();
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
                startActivity(i);
            }
        });

        }

}