/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.flavor;

import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.os.Environment;
        import android.provider.MediaStore;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.text.InputType;
        import android.util.Log;
        import android.view.View;
        import android.view.inputmethod.EditorInfo;
        import android.widget.ArrayAdapter;
        import android.widget.EditText;
        import android.widget.Spinner;
        import android.widget.Toast;

        import java.io.File;
        import java.text.SimpleDateFormat;
        import java.util.Date;


/**
 * OrderActivity provides the user with a simple 'Take Photograph' and attach to email procedure<br>
 * @author Adapted from code written by Colette Kirwan. DCU Open Education
 */
public class OrderActivity extends AppCompatActivity
{
    /**
     * Uri to track pictures taken with camera
     */
    Uri mPhotoURI;
    /**
     * Spinner for 'Delivery/Collection in' field
     */
    Spinner mSpinner;
    /**
     * Customer name variable
     */
    EditText mCustomerName;
    /**
     * Optional text variable that can be included in email
     */
    EditText meditOptional;
    /**
     * Static variable for request image code for intent results retrieval
     */
    static final int REQUEST_IMAGE_CAPTURE = 1;
    /**
     * Static variable for request take photo code for intent results retrieval
     */
    static final int REQUEST_TAKE_PHOTO = 2;
    /**
     * string variable set to 'Assign3' for logcat use
     */
    private static final String TAG = "Assign3";


    /**
     * On creation of this page save the instance state,
     * setup the page resource <i>activity_order</i> and populate the screen with the adapter
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        meditOptional = (EditText) findViewById(R.id.editOptional);

        meditOptional.setImeOptions(EditorInfo.IME_ACTION_DONE);
        meditOptional.setRawInputType(InputType.TYPE_CLASS_TEXT);
        //initialise spinner using the integer array
        mSpinner = (Spinner) findViewById(R.id.spinner);
        mCustomerName = (EditText) findViewById(R.id.editCustomer);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ui_time_entries, R.layout.spinner_days);
        mSpinner.setAdapter(adapter);

    }





    /**
     * Launches the Camera to take a picture for the T-shirt<br>
     * Creates a unique file name for the picture to be taken based on the date/time
     * and assigns the file name to the mPhotoURI and sets the return code to 'REQUEST_TAKE_PHOTO' static variable
     */
    public void dispatchTakePictureIntent(View v)
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String imageFileName = "my_tshirt_image_" + timeStamp + ".jpg";

        Log.i(TAG, "imagefile");

        File file = new File(Environment.getExternalStorageDirectory(), imageFileName);

        mPhotoURI = Uri.fromFile(file);
        Log.i(TAG, mPhotoURI.toString());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoURI);
        startActivityForResult(intent, REQUEST_TAKE_PHOTO);
        //incase of caching if it comes from the activity stack, just a precaution
        intent.removeExtra(MediaStore.EXTRA_OUTPUT);

    }

    /**
     * Retrieve the picture taken for the T-shirt and let the user know success via toast and alert dialog
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        //also can give user a message that everything went ok
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK)
        {
            //let user know that image saved
            //I have strings in strings.xml but have hardcoded here to copy/paste to students if needed
            CharSequence text = "Image Taken successfully";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this, text, duration);
            toast.show();

            //or perhaps do a dialog should only use one method i.e. toast or dialog, but have both code here for demo purposes
            //also I have strings in strings.xml but have hardcoded here to copy/paste to students if needed

            //Removed this notofocation as a result of testing. Toast is sufficient.
           /* AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Notification!").setMessage("Image saved successfully.").setPositiveButton("OK", null).show();*/
        }


    }

    /**
     * Returns the Email Body Message.<br>
     * Email body message is created used prescription related data inputed from user
     * @return Email Body Message
     */
    private String createOrderSummary()
    {

        String orderMessage = getString(R.string.customer_name) + " " + mCustomerName.getText().toString();
        orderMessage += "\n" + "\n" + getString(R.string.order_message_1);
        String optionalInstructions = meditOptional.getText().toString();

        orderMessage += "\n" + getString(R.string.order_message_collect) + ((CharSequence) mSpinner.getSelectedItem()).toString() + " days";
        orderMessage += "\n" + getString(R.string.order_message_end) + "\n" + mCustomerName.getText().toString();

        // add simple use of optionalInstruction for Assignment 3
        orderMessage += "\n" + "\n" + "Optional Instrutions: " + optionalInstructions;

        return orderMessage;

        //update screen
    }

    /**
     * Open Email application an prepopulate with data from OrderActivity screen & picture retrieved
     */
    public void sendEmail(View v)
    {
        //check that Name is not empty, and ask do they want to continue
        String customerName = mCustomerName.getText().toString();
        if (customerName.matches(""))
        {
            Toast.makeText(this, getString(R.string.customer_name_blank), Toast.LENGTH_SHORT).show();
            // we can also use a dialog
             //AlertDialog.Builder builder = new AlertDialog.Builder(this);
             //builder.setTitle("Notification!").setMessage("Customer Name not set.").setPositiveButton("OK", null).show();
             //
        } else
        {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("*/*");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.to_email)});
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject));
            intent.putExtra(Intent.EXTRA_STREAM, mPhotoURI);
            intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary());
            if (intent.resolveActivity(getPackageManager()) != null)
            {
                startActivity(intent);
            }
        }
    }


}
