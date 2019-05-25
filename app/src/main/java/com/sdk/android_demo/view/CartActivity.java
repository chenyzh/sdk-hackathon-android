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
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sdk.android_demo.R;
import com.sdk.android_demo.model.Cart;

public class CartActivity extends BaseActivity {

    //TODO 在您活动的顶部声明 com.google.firebase.analytics.FirebaseAnalytics 对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //TODO 在 onCreate() 方法中对FirebaseAnalytics进行初始化

        Cart cart = Cart.getInstance();
        if (cart.isEmpty()) {
            TextView emptyCart = findViewById(R.id.emptycart);
            emptyCart.setText(R.string.cart_is_empty);
            Button checkoutBtn = (Button) findViewById(R.id.checkout_btn);
            checkoutBtn.setVisibility(View.INVISIBLE);
        } else {
            RecyclerView view = (RecyclerView) findViewById(R.id.item_list);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                    LinearLayoutManager.VERTICAL, false);
            view.setLayoutManager(layoutManager);
            CartItemAdapter adapter = new CartItemAdapter();
            view.setAdapter(adapter);
        }
    }


    public void checkout(View v) {
        Cart cart = Cart.getInstance();
        //track conversion for purchase
        logPurchase(cart.getSubTotal());
        cart.pay();
        Intent intent = new Intent(this, Paid.class);
        startActivity(intent);
    }

    // Track the conversion with the purchase subtotal
    private void logPurchase(double subtotal) {
        //TODO 打点推荐事件 FirebaseAnalytics.Event.ECOMMERCE_PURCHASE
    }
}
