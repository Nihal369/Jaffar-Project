package me.nihalismail.picpo.starter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class UserFeed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feed);
        final LinearLayout linearLayout=(LinearLayout)findViewById(R.id.linearLayout);
        Intent i=getIntent();
        String activeUserName=i.getStringExtra("username");
        Log.i("Status", activeUserName);
        setTitle(activeUserName + "'s Feed");
        ParseQuery<ParseObject>parseQuery=new ParseQuery<ParseObject>("Images");
        parseQuery.whereEqualTo("username",activeUserName);
        parseQuery.orderByDescending("createdAt");
        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null) {
                    if (objects.size() > 0) {
                        for (ParseObject object : objects) {
                            ParseFile file = (ParseFile) object.get("image");
                            file.getDataInBackground(new GetDataCallback() {
                                @Override
                                public void done(byte[] data, ParseException e) {
                                    if (e == null) {
                                        Bitmap image = BitmapFactory.decodeByteArray(data, 0, data.length);
                                        ImageView imageView = null;
                                        imageView = new ImageView(getApplicationContext());
                                        imageView.setImageBitmap(image);
                                        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                                                ViewGroup.LayoutParams.MATCH_PARENT,
                                                ViewGroup.LayoutParams.WRAP_CONTENT
                                        ));
                                        linearLayout.addView(imageView);
                                    }
                                }
                            });
                        }
                    }
                }else {
                        e.printStackTrace();
                    }
            }
        });
    }
}
