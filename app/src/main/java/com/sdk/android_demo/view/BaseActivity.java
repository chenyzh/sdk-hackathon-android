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

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sdk.android_demo.model.Section;

/**
 * This is a base class for all activites
 *
 */

public class BaseActivity extends AppCompatActivity {

    static final String PRODUCT_ID = "product_id";
    static final String SECTION_NAME = "section";

    void navToHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    void navToCart() {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }

    void navToSectionPage(Section section) {
        Intent intent = new Intent(this, SectionActivity.class);
        intent.putExtra(SECTION_NAME, section.toString());
        startActivity(intent);
    }

    public void navToProductPage(int productId) {
        Intent intent = new Intent(this, ProductActivity.class);
        intent.putExtra(PRODUCT_ID, productId);
        startActivity(intent);
    }

    public void cartBtnClick(View v) {
        navToCart();
    }
}
