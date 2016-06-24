package me.jaffar.picpo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import me.jaffar.picpo.starter.R;

public class findActivity extends AppCompatActivity {

    String colourFind,companyFind,addressFind,activeFindUser,selectedInputOfFindSpinner;

    public void getFindDetails(View view)
    {
        EditText enteredColourFind=(EditText)findViewById(R.id.colorInputFound);
        EditText enteredCompanyFind=(EditText)findViewById(R.id.companyInputFound);
        EditText enteredAddressFind=(EditText)findViewById(R.id.addressInputFound);
        colourFind=enteredColourFind.getText().toString();
        companyFind=enteredCompanyFind.getText().toString();
        addressFind=enteredAddressFind.getText().toString();
        Intent findIntent=new Intent(this,FindsView.class);
        findIntent.putExtra("findColour",colourFind);
        findIntent.putExtra("findCompany",companyFind);
        findIntent.putExtra("findAdddress",addressFind);
        Spinner findSpinner=(Spinner)findViewById(R.id.spinnerFound);
        selectedInputOfFindSpinner=findSpinner.getSelectedItem().toString();
        findIntent.putExtra("findSpinnerData",selectedInputOfFindSpinner);
        findIntent.putExtra("TypeFind","Find");
        activeFindUser= ParseUser.getCurrentUser().getUsername();
        findIntent.putExtra("findUser",activeFindUser);
        String mobileNumberFind=ParseUser.getCurrentUser().get("MobileNumber").toString();
        findIntent.putExtra("findMobileNumber",mobileNumberFind);
        startActivity(findIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        ArrayList<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("Electronics");
        spinnerArray.add("Wallet");
        spinnerArray.add("Vehicle");
        Spinner spinner =(Spinner)findViewById(R.id.spinnerFound);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        spinner.setAdapter(spinnerArrayAdapter);
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
                object.put("image", file);
                object.put("type","find");
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
}
