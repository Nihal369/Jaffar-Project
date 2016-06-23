package me.jaffar.picpo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import me.jaffar.picpo.starter.UsersList;
import me.jaffar.picpo.starter.R;

public class LostnFound extends AppCompatActivity {

    public void emergencyCall(View view)
    {
        Uri number = Uri.parse("tel:+919995795321");
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        startActivity(callIntent);
    }

    public void emergencyEmail(View view)
    {
        Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "ashish.shajan@gmail.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Lost and found");
        intent.putExtra(Intent.EXTRA_TEXT, "");
        startActivity(intent);
    }

    public void goToLostActivity(View view)
    {
        Intent toLost=new Intent(getApplicationContext(),lostActivity.class);
        startActivity(toLost);
    }
    public void gotToViewActivity(View view)
    {
        Intent toLost=new Intent(getApplicationContext(),NewView.class);
        startActivity(toLost);
    }
    public void goToFound(View view)
    {
        Intent toLost=new Intent(getApplicationContext(),findActivity.class);
        startActivity(toLost);
    }
    public void goToPost(View view)
    {
        Intent toLost=new Intent(getApplicationContext(),UsersList.class);
        startActivity(toLost);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lostn_found);
    }
}
