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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sdk.android_demo.R;
import com.sdk.android_demo.model.Product;
import com.sdk.android_demo.model.Section;
import com.sdk.android_demo.utils.MockItemProvider;

import java.util.Collection;
import java.util.List;

public class SectionActivity extends BaseActivity {

    //TODO 在您活动的顶部声明 com.google.firebase.analytics.FirebaseAnalytics 对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);

        //TODO 在 onCreate() 方法中对FirebaseAnalytics进行初始化
        TextView title = findViewById(R.id.title);

        Intent intent = getIntent();
        Uri data = intent.getData();
        String sectionStr = null;
        if (data != null) {
            //handle Deep links: extract section name from the last segment of the URI
            List<String> segments = data.getPathSegments();
            sectionStr = segments.get(segments.size() - 1).toUpperCase();
        } else {
            sectionStr = intent.getStringExtra(SECTION_NAME).toUpperCase();
        }
        try {
            Section section = Section.valueOf(sectionStr);
            title.setText(section.getTitle());
            RecyclerView view = (RecyclerView) findViewById(R.id.section_product_list);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                    LinearLayoutManager.VERTICAL, false);
            view.setLayoutManager(layoutManager);
            Collection<Product> products = MockItemProvider.generateProductList(section);
            ProductListAdapter adapter = new ProductListAdapter(products, R.layout.product_as_list);
            view.setAdapter(adapter);
            //TODO 打点推荐事件 FirebaseAnalytics.Event.VIEW_ITEM_LIST

        } catch (IllegalArgumentException | NullPointerException e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.page_not_found)
                    .setPositiveButton(R.string.continue_shop, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            navToHome();
                        }
                    });
            builder.create().show();
        }
    }

    public void cartBtnClick(View v) {
        navToCart();
    }
}
