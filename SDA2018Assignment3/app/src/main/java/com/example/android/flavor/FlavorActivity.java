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


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import android.widget.ListView;

import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * {@link FlavorActivity} shows a list of Android platform releases.<br>
 * For Assignment 3 this will be a list of Shop Products.<br>
 * For each Product <i>(previously Release)</i>, display the Product Name <i>(previously name)</i>, Product Cost <i>(previously version number)</i>, and image.
 */
public class FlavorActivity extends AppCompatActivity {

    /** Create an {@link AndroidFlavorAdapter}, whose data source is a list of
     *{@link AndroidFlavor}'s. The adapter knows how to create list item views for each item
     *  in the list.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flavor);

        // Create an ArrayList of AndroidFlavour objects to represent the Shop products
        // for Assignment 3 the vName and vNumbers have been adapted for the Shop context rather than the Android Flavours
        final ArrayList<AndroidFlavor> androidFlavors = new ArrayList<AndroidFlavor>();
        androidFlavors.add(new AndroidFlavor("T-Shirt", "€0.50", R.drawable.tshirt));
        androidFlavors.add(new AndroidFlavor("Shorts", "€1.50", R.drawable.shorts));
        androidFlavors.add(new AndroidFlavor("Vest", "€3.00", R.drawable.vest));
        androidFlavors.add(new AndroidFlavor("Jeans", "€4:50", R.drawable.trousers));
        androidFlavors.add(new AndroidFlavor("Caps", "€5.00", R.drawable.cap));
        androidFlavors.add(new AndroidFlavor("Hoodie", "€6.50", R.drawable.hoodie));
        androidFlavors.add(new AndroidFlavor("Tie", "€7.50", R.drawable.tie));
        androidFlavors.add(new AndroidFlavor("Shoes", "€9.00", R.drawable.boots));
        androidFlavors.add(new AndroidFlavor("Shirt", "€10.99", R.drawable.shirt));
        androidFlavors.add(new AndroidFlavor("Belt", "€10.99", R.drawable.belt));
        androidFlavors.add(new AndroidFlavor("Runners", "€10.99", R.drawable.sneakers));

        /* Create an AndroidFlavorAdapter, whose data source is a list of
        AndroidFlavor's. The adapter knows how to create list item views for each item
        in the list.
        */
        final AndroidFlavorAdapter flavorAdapter = new AndroidFlavorAdapter(this, androidFlavors);

        // Get a reference to the ListView, and attach the adapter to the listView.
        final ListView listView = (ListView) findViewById(R.id.listview_flavor);
        listView.setAdapter(flavorAdapter);

        // Set the toast
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
            // here I have the position of the element in the arraylist 'position'
             // declare a AndroidFlavour instance called product value and set it to the appropriate AndroidFlavour at the position in the arraylist.
            AndroidFlavor productValue = androidFlavors.get(position);
                Toast.makeText(getApplicationContext(),
                        productValue.getVersionName(), Toast.LENGTH_LONG)
                        .show();
            }
        });

    }

}

