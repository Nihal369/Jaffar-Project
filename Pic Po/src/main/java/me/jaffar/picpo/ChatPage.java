package me.jaffar.picpo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import me.jaffar.picpo.starter.R;

public class ChatPage extends AppCompatActivity {

    public void sendMessage(View view)
    {
        String loggedInUser= ParseUser.getCurrentUser().getUsername();
        String currentUser=getIntent().getStringExtra("username");
        EditText userInput=(EditText)findViewById(R.id.chatInput);
        String chatMessage=userInput.getText().toString();
        Log.i("Adipoli",loggedInUser);
        Log.i("Adipoli",currentUser);
        Log.i("Adipoli",chatMessage);
        ParseObject dataObject = new ParseObject("Chat");
        dataObject.put("Sender",loggedInUser);
        dataObject.put("Reciever",currentUser);
        dataObject.put("Message",chatMessage);
        ParseACL parseACL=new ParseACL();
        parseACL.setPublicReadAccess(true);
        dataObject.setACL(parseACL);
        dataObject.saveInBackground();
        final ArrayList<String> messagesArray = new ArrayList<String>();
        final ListView listView=(ListView)findViewById(R.id.chatListView);
        final ArrayAdapter<String>arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,messagesArray);
        ParseQuery<ParseObject> parseQuery1 = new ParseQuery<ParseObject>("Chat");
        parseQuery1.whereEqualTo("Sender",loggedInUser);
        parseQuery1.whereEqualTo("Reciever",currentUser);
        ParseQuery<ParseObject>parseQuery2=new ParseQuery<ParseObject>("Chat");
        parseQuery2.whereEqualTo("Sender",currentUser);
        parseQuery2.whereEqualTo("Reciever",loggedInUser);
        List<ParseQuery<ParseObject>> queries = new ArrayList<ParseQuery<ParseObject>>();
        queries.add(parseQuery1);
        queries.add(parseQuery2);
        ParseQuery<ParseObject>parseQuery=ParseQuery.or(queries);
        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    Log.i("Status", "Grabbed " + objects.size() + " Message");
                    if (objects.size() > 0) {
                        for (ParseObject parseObject : objects) {
                            messagesArray.add(String.valueOf(parseObject.get("Sender") + ":" + parseObject.get("Message")));
                        }
                    }
                    listView.setAdapter(arrayAdapter);
                }
            }
        });
        userInput.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_page);
        Intent i = getIntent();
        String activeUserName = i.getStringExtra("username");
        Log.i("Status", activeUserName);
        setTitle("Chat with " + activeUserName);
        final String loggedInUser = ParseUser.getCurrentUser().getUsername();
        final String currentUser = getIntent().getStringExtra("username");
        final ArrayList<String> messagesArray = new ArrayList<String>();
        final ListView listView=(ListView)findViewById(R.id.chatListView);
        final ArrayAdapter<String>arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,messagesArray);
        ParseQuery<ParseObject> parseQuery1 = new ParseQuery<ParseObject>("Chat");
        parseQuery1.whereEqualTo("Sender",loggedInUser);
        parseQuery1.whereEqualTo("Reciever",currentUser);
        ParseQuery<ParseObject>parseQuery2=new ParseQuery<ParseObject>("Chat");
        parseQuery2.whereEqualTo("Sender",currentUser);
        parseQuery2.whereEqualTo("Reciever",loggedInUser);
        List<ParseQuery<ParseObject>> queries = new ArrayList<ParseQuery<ParseObject>>();
        queries.add(parseQuery1);
        queries.add(parseQuery2);
        ParseQuery<ParseObject>parseQuery=ParseQuery.or(queries);
        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    Log.i("Status", "Grabbed " + objects.size() + " Message");
                    if (objects.size() > 0) {
                        for (ParseObject parseObject : objects) {
                            messagesArray.add(String.valueOf(parseObject.get("Sender") + ":" + parseObject.get("Message")));
                        }
                    }
                    listView.setAdapter(arrayAdapter);
                }
            }
        });
    }
}
