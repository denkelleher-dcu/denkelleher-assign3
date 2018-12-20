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

/**
 * {@link AndroidFlavor} represents a single Android platform release.<br>
 * For Assignment 3 this is a Product for a Shop.<br>
 * Each object has 3 properties: name, version number, and image resource ID.<br>
 * For Assignment 3 this is a product name, price and image.
 */
public class AndroidFlavor {

    /**
     * Represents the Name of the Android version (e.g. Gingerbread, Honeycomb, Ice Cream Sandwich)<br>
     *for Assignment 3 this becomes product name (eg Shirt, Tie, etc)
     */
    private String mVersionName;

    /**
     * Represents Android version number (e.g. 2.3-2.7, 3.0-3.2.6, 4.0-4.0.4)<br>
     *for Assignment 3 this is the product price
     */
    private String mVersionNumber;

    /**
     * Represents Drawable resource ID for the icon to be displayed
     */
    private int mImageResourceId;




    /**
    * Create a new AndroidFlavor object.
    *
    * @param vName is the name of the Android version (e.g. Gingerbread)
    * @param vNumber is the corresponding Android version number (e.g. 2.3-2.7)
    * @param imageResourceId is drawable reference ID that corresponds to the Android version<br>
    * <br>
    * For Assignment 3 vName is the Product Name (eg Shirt)<br>
    * For Assignment 3 vNumber is the Product Price (eg €10.99)<br>
    * For Assignment 3 image is a sample image of the Product in .png form<br>
    *
    * Define the instance when creating an AndroidFlavour with vName, vNumber and imageResourceId
    * */
    public AndroidFlavor(String vName, String vNumber, int imageResourceId)
    {
        mVersionName = vName;
        mVersionNumber = vNumber;
        mImageResourceId = imageResourceId;
    }




    /**
     * Get method for the name of the Android version
     * @return String representing the name of Android version (oreo, lollipop etc)<br>
     * For Assignment 3 this will be the product name (shirt, shorts etc)
     */
    public String getVersionName(){
        return mVersionName;
    }

    /**
     * Get method for the Android version number
     * @return String representing Android version (2.3, 2.5 etc)<br>
     * For Assignment 3 this will be the product price (€10.00, €5.00 etc)
     */
    public String getVersionNumber() {
        return mVersionNumber;
    }

    /**
     * Get the image resource ID
     * @return reference ID for the icon that corresponds to the Android version<br>
     * For Assignment 3 this is the icon that corresponds to the product
     */
    public int getImageResourceId() {
        return mImageResourceId;
    }


}
