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
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * A simple android application for displaying a product listing and ordering t-shirts.<br>
 * This is the required output for DCU 2018/19 SDA Assignment 3
 * @author <a href="mailto:denis.kelleher29@mail.dcu.ie">Denis Kelleher</a>
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity
{
    static private final String TAG = "Assign 3";

    /** On startup of the application (or re-creation of this activity) this is the
     * main activity which calls the resource <i>activity_main.xml</i>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Launches the FlavourActivity, to display data in list format
     *
     * @param view EditText
     */
    public void callList(View view)
    {
        Intent intent = new Intent(this, FlavorActivity.class);
        if (intent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(intent);
        }
    }

    /**
     * Launches the OrderActivity, to capture Order Information
     *
     * @param view EditText
     */
    public void callOrder(View view)
    {
        Intent intent = new Intent(this, OrderActivity.class);
        if (intent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(intent);
        }
    }


}

