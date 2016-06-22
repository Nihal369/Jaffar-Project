package me.nihalismail.picpo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import me.nihalismail.picpo.starter.R;
import me.nihalismail.picpo.starter.UsersList;

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
