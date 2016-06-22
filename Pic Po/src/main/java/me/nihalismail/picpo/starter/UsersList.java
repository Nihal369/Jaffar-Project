package me.nihalismail.picpo.starter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UsersList extends AppCompatActivity {
    ArrayList<String>userNames;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        userNames=new ArrayList<String>();
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,userNames);
        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(getApplicationContext(),UserFeed.class);
                i.putExtra("username",userNames.get(position));
                startActivity(i);
            }
        });
        ParseQuery<ParseUser> parseQuery=ParseUser.getQuery();
        parseQuery.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());
        parseQuery.addAscendingOrder("username");
        parseQuery.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if(e==null) {
                    Log.i("Status", "Grabbed " + objects.size() + " objects");
                    if (objects.size() > 0) {
                        for (ParseUser parseUser : objects) {
                            userNames.add(String.valueOf(parseUser.get("username")));
                        }
                    }
                    listView.setAdapter(adapter);
                }

            }
        });

    }
    public void uploadImage(View view)
    {
        Intent i=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, 1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null)
        {
            Uri selectedImage=data.getData();
            try {
                Bitmap bitmap=MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedImage);
                Log.i("Status", "Image uploaded");
                ByteArrayOutputStream stream=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                byte[] byteArray=stream.toByteArray();
                ParseFile file=new ParseFile("image.png",byteArray);
                ParseObject object=new ParseObject("Images");
                object.put("username",ParseUser.getCurrentUser().getUsername());
                object.put("image", file);
                ParseACL parseACL=new ParseACL();
                parseACL.setPublicReadAccess(true);
                object.setACL(parseACL);
                object.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null)
                        {
                            Toast.makeText(getApplication().getBaseContext(), "Image Uploaded :)", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getApplication().getBaseContext(), "Error,Please Try again :(", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void logout(View view)
    {
        ParseUser.logOut();
        Intent i=new Intent(getApplicationContext(),LoginActivity.class);
       startActivity(i);
    }
}
