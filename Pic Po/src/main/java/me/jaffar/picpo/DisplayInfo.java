package me.jaffar.picpo;//jaffar.

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import me.jaffar.picpo.starter.UsersList;
import me.jaffar.picpo.starter.R;

public class DisplayInfo extends AppCompatActivity {



    public void callUser(View view)
    {
        String mobileNumberString=getIntent().getExtras().getString("mobileInfo");
        Uri number = Uri.parse("tel:+91"+mobileNumberString);
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        startActivity(callIntent);
    }

    public void goToChatActivity(View view)
    {
        Intent i=new Intent(getApplicationContext(),UsersList.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_info);
        TextView addressText=(TextView)findViewById(R.id.displayAddress);
        TextView colourText=(TextView)findViewById(R.id.displayColour);
        TextView companyText=(TextView)findViewById(R.id.displayCompany);
        TextView typeText=(TextView)findViewById(R.id.displayType);
        TextView userText=(TextView)findViewById(R.id.displayUser);
        TextView spinnerText=(TextView)findViewById(R.id.displaySpinner);
        TextView mobileText=(TextView)findViewById(R.id.displayMobileNumber);

        String addressString=getIntent().getExtras().getString("addressInfo");
        String colourString=getIntent().getExtras().getString("colourInfo");
        String companyString=getIntent().getExtras().getString("companyInfo");
        String typeString=getIntent().getExtras().getString("typeInfo");
        String userString=getIntent().getExtras().getString("userInfo");
        String spinnerString=getIntent().getExtras().getString("spinnerInfo");
        String mobileNumberString=getIntent().getExtras().getString("mobileInfo");

        addressText.setText("Address:"+addressString);
        colourText.setText("Colour:"+colourString);
        companyText.setText("Company:"+companyString);
        typeText.setText(typeString);
        userText.setText("Username:"+userString);
        spinnerText.setText("Type:"+spinnerString);
        mobileText.setText("Mobile:"+mobileNumberString);
    }
}
