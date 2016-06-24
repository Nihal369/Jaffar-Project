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
            String passedMobileNumber=getIntent().getExtras().getString("lostMobileNumber");

        


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
            dataObject.put("MobileNumber",passedMobileNumber);
            ParseACL parseACL=new ParseACL();
            parseACL.setPublicReadAccess(true);
            dataObject.setACL(parseACL);
            dataObject.saveInBackground();

        //Lost Query
        final ListView listViewFind = (ListView) findViewById(R.id.viewListItemFound);
        final ListView listViewLost = (ListView) findViewById(R.id.viewListItemLost);

        final ArrayList<String> itemListLost = new ArrayList<String>();
        final ArrayList<String> addressListLost = new ArrayList<String>();
        final ArrayList<String> colourListLost = new ArrayList<String>();
        final ArrayList<String> companyListLost = new ArrayList<String>();
        final ArrayList<String> userListLost = new ArrayList<String>();
        final ArrayList<String> typeListLost = new ArrayList<String>();
        final ArrayList<String> spinnerListLost = new ArrayList<String>();
        final ArrayList<String> mobileNumberListLost = new ArrayList<String>();
        final ArrayAdapter<String> viewAdapterLost = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemListLost);

        ParseQuery<ParseObject> lostParseQuery = ParseQuery.getQuery("Lost");
        lostParseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    Log.i("Mava", "Grabbed " + objects.size() + " objects");
                    if (objects.size() > 0) {
                        for (ParseObject parseObject : objects) {
                            itemListLost.add(String.valueOf(parseObject.get("Spinner") + " Lost"));
                            addressListLost.add(String.valueOf(parseObject.get("Address")));
                            colourListLost.add(String.valueOf(parseObject.get("Color")));
                            userListLost.add(String.valueOf(parseObject.get("User")));
                            spinnerListLost.add(String.valueOf(parseObject.get("Spinner")));
                            typeListLost.add(String.valueOf(parseObject.get("Type")));
                            companyListLost.add(String.valueOf(parseObject.get("Company")));
                            mobileNumberListLost.add(String.valueOf(parseObject.get("MobileNumber")));
                        }
                    }
                    listViewLost.setAdapter(viewAdapterLost);
                }

            }
        });

        //Find Query
        final ArrayList<String> itemListFind = new ArrayList<String>();
        final ArrayList<String>addressListFind=new ArrayList<String>();
        final ArrayList<String>colourListFind=new ArrayList<String>();
        final ArrayList<String>companyListFind=new ArrayList<String>();
        final ArrayList<String>userListFind=new ArrayList<String>();
        final ArrayList<String>typeListFind=new ArrayList<String>();
        final ArrayList<String>spinnerListFind=new ArrayList<String>();
        final ArrayList<String>mobileNumberListFind=new ArrayList<String>();
        final ArrayAdapter<String> viewAdapterFind = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemListFind);

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
                            itemListFind.add(String.valueOf(parseObject.get("Spinner")+" Found"));
                            addressListFind.add(String.valueOf(parseObject.get("Address")));
                            colourListFind.add(String.valueOf(parseObject.get("Color")));
                            userListFind.add(String.valueOf(parseObject.get("User")));
                            spinnerListFind.add(String.valueOf(parseObject.get("Spinner")));
                            typeListFind.add(String.valueOf(parseObject.get("Type")));
                            companyListFind.add(String.valueOf(parseObject.get("Company")));
                            mobileNumberListFind.add(String.valueOf(parseObject.get("MobileNumber")));
                        }
                    }
                    listViewFind.setAdapter(viewAdapterFind);
                }
            }
        });

        listViewLost.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(getApplicationContext(),DisplayInfo.class);
                i.putExtra("addressInfo",addressListLost.get(position));
                i.putExtra("colourInfo",colourListLost.get(position));
                i.putExtra("userInfo",userListLost.get(position));
                i.putExtra("spinnerInfo",spinnerListLost.get(position));
                i.putExtra("typeInfo",typeListLost.get(position));
                i.putExtra("companyInfo",companyListLost.get(position));
                i.putExtra("mobileInfo",mobileNumberListLost.get(position));
                i.putExtra("positionInfo",position);
                startActivity(i);
            }
        });

        listViewFind.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(getApplicationContext(),DisplayInfo.class);
                i.putExtra("addressInfo",addressListFind.get(position));
                i.putExtra("colourInfo",colourListFind.get(position));
                i.putExtra("userInfo",userListFind.get(position));
                i.putExtra("spinnerInfo",spinnerListFind.get(position));
                i.putExtra("typeInfo",typeListFind.get(position));
                i.putExtra("companyInfo",companyListFind.get(position));
                i.putExtra("mobileInfo",mobileNumberListFind.get(position));
                i.putExtra("positionInfo",position);
                startActivity(i);
            }
        });

        }

}