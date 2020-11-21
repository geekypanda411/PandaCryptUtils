package com.geekypanda.helloworld;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {
    final String[] options = { "Base64 Encode", "Base64 Decode", "MD5","SHA-1","SHA-256","SHA-384","SHA-512","Whirlpool"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);
        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,options);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
        //TODO :: Add Ability to AES128/256 encrypt strings

    }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(),options[position] , Toast.LENGTH_LONG).show();
        EditText mEdit;
        TextView mText;
        mEdit   = findViewById(R.id.editTextTextMultiLine);
        mText = findViewById(R.id.resultView);
        if (options[position].equals("Base64 Encode")){
            //Execute base64 encode
            String encodedText = b64encode(mEdit.getText().toString());
            mText = findViewById(R.id.resultView);
            mText.setText(encodedText);
        } else if (options[position].equals("Base64 Decode")) {
            //Execute base64 decode
            String decodedText = b64decode(mEdit.getText().toString());
            mText.setText(decodedText);
        } else if (options[position].equals("MD5")){
            //execute md5
            String md5Out = hasher(mEdit.getText().toString(),"MD5");
            if (!md5Out.equals("No$Such$Algo")){
                //Successfully Hashed
                mText.setText(md5Out);
            }
        } else if (options[position].equals("SHA-1")){
            //Execute SHA-256
            String sha1Out = hasher(mEdit.getText().toString(),"SHA-1");
            if (!sha1Out.equals("No$Such$Algo")){
                //Successfully Hashed
                mText.setText(sha1Out);
            }
        }  else if (options[position].equals("SHA-256")){
            //Execute SHA-256
            String sha256Out = hasher(mEdit.getText().toString(),"SHA-256");
            if (!sha256Out.equals("No$Such$Algo")){
                //Successfully Hashed
                mText.setText(sha256Out);
            }
        }  else if (options[position].equals("SHA-384")){
            //Execute SHA-256
            String sha384Out = hasher(mEdit.getText().toString(),"SHA-384");
            if (!sha384Out.equals("No$Such$Algo")){
                //Successfully Hashed
                mText.setText(sha384Out);
            }
        } else if (options[position].equals("SHA-512")){
            //Execute SHA-256
            String sha512Out = hasher(mEdit.getText().toString(),"SHA-512");
            if (!sha512Out.equals("No$Such$Algo")){
                //Successfully Hashed
                mText.setText(sha512Out);
            }
        }  else if (options[position].equals("Whirlpool")){
            //Execute Whirlpool
            mText.setText("Comming Soon!");
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public String b64encode(String clearText){
        return Base64.getEncoder().encodeToString(clearText.getBytes());
    }

    public String b64decode(String encodedText){
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(encodedText);
            return new String(decodedBytes);
        } catch (Exception e){
            return "Not a Valid Base64 String!";
        }

    }

    //Code from Kospol
    public static String hasher(final String clearText, String algorithm) {
        try {
            // Create a message digest object with md5 as the digest type
            MessageDigest digest = java.security.MessageDigest.getInstance(algorithm);
            // Update the digest with bytes from input string
            digest.update(clearText.getBytes());
            //Actually perform a digest operation and put output bytes into a byte array
            byte[] messageDigest = digest.digest();

            // Create a string builder object
            StringBuilder hexString = new StringBuilder();
            //Convert from a byte array to a hex string by appending components
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            //Convert the hex string to string
            return hexString.toString();
        //In case no such algo is found
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "No$Such$Algo";
        }
    }
}