/*
 * Copyright 2019 wentaoli
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sdk.android_demo.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.sdk.android_demo.R;
import com.sdk.android_demo.model.Cart;
import com.sdk.android_demo.model.Product;
import com.sdk.android_demo.utils.MockItemProvider;
import com.sdk.android_demo.utils.Util;

public class ProductActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    private Product product;
    private int quantity;

    //TODO 在您活动的顶部声明 com.google.firebase.analytics.FirebaseAnalytics 对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);

        //TODO 在 onCreate() 方法中对FirebaseAnalytics进行初始化

        TextView name = findViewById(R.id.product_name);
        TextView price = findViewById(R.id.product_price);
        ImageView image = findViewById(R.id.product_image);

        Spinner spinner = (Spinner) findViewById(R.id.quantity_selector);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.quantity_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        product = MockItemProvider.getProductById(getProductId());
        if (product != null) {
            image.setImageResource(product.getImageId());
            name.setText(product.getName());
            price.setText(Util.formatPrice(product.getPrice()));
            //TODO 打点推荐事件 FirebaseAnalytics.Event.VIEW_ITEM
        } else {
            //wrong product id
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.item_not_found)
                    .setPositiveButton(R.string.continue_shop, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            navToHome();
                        }
                    });
            builder.create().show();
        }
    }

    private int getProductId() {
        Intent intent = getIntent();
        Uri data = intent.getData();
        if (data != null) {
            //handle Deep links: extract product id from the "id" parameter of the URI
            return Integer.parseInt(data.getQueryParameter("id"));
        } else {
            return intent.getIntExtra(PRODUCT_ID, -1);
        }
    }

    public void addToCart(View v) {
        Cart.getInstance().add(product, quantity);
        //TODO 打点推荐事件 FirebaseAnalytics.Event.ADD_TO_CART

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.dialog_added_cart)
                .setPositiveButton(R.string.view_cart, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        navToCart();
                    }
                })
                .setNegativeButton(R.string.continue_shop, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        navToHome();
                    }
                });
        builder.create().show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        quantity = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        quantity = 1;
    }
}
