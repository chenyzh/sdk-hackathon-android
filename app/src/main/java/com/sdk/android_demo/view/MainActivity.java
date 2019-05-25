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

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sdk.android_demo.R;
import com.sdk.android_demo.model.Product;
import com.sdk.android_demo.model.Section;
import com.sdk.android_demo.utils.MockItemProvider;

import java.util.Collection;

import static com.sdk.android_demo.model.Section.NEW_ARRIVAL;
import static com.sdk.android_demo.model.Section.RECOMMENDATION;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView newArrivalView = (RecyclerView) findViewById(R.id.new_arrivals_view);
        renderProducts(NEW_ARRIVAL, newArrivalView);
        RecyclerView recommendView = (RecyclerView) findViewById(R.id.recommendation_view);
        renderProducts(RECOMMENDATION, recommendView);
    }

    private void renderProducts(Section section, RecyclerView view) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        view.setLayoutManager(layoutManager);
        Collection<Product> products = MockItemProvider.generateProductList(section);
        ProductListAdapter adapter = new ProductListAdapter(products, R.layout.product_as_icons);
        view.setAdapter(adapter);
    }

    public void cartBtnClick(View v) {
        navToCart();
    }

    public void recommendClick(View v) {
        navToSectionPage(RECOMMENDATION);
    }

    public void newArrClick(View v) {
        navToSectionPage(NEW_ARRIVAL);
    }
}

