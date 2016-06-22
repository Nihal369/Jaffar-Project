package me.nihalismail.picpo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import me.nihalismail.picpo.starter.R;

public class DisplayInfo extends AppCompatActivity {

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

        String addressString=getIntent().getExtras().getString("addressInfo");
        String colourString=getIntent().getExtras().getString("colourInfo");
        String companyString=getIntent().getExtras().getString("companyInfo");
        String typeString=getIntent().getExtras().getString("typeInfo");
        String userString=getIntent().getExtras().getString("userInfo");
        String spinnerString=getIntent().getExtras().getString("spinnerInfo");

        addressText.setText("Address:"+addressString);
        colourText.setText("Colour:"+colourString);
        companyText.setText("Company:"+companyString);
        typeText.setText(typeString);
        userText.setText("Username:"+userString);
        spinnerText.setText("Type:"+spinnerString);
    }
}
