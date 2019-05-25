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

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdk.android_demo.R;
import com.sdk.android_demo.model.Cart;
import com.sdk.android_demo.model.Product;
import com.sdk.android_demo.utils.Util;

public class CartItemAdapter extends RecyclerView.Adapter {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.cart_item_list, parent, false);
        return new CartItemHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CartItemHolder itemHolder = (CartItemHolder) holder;
        Cart cart  = Cart.getInstance();
        final Product product = cart.getProduct(position);
        itemHolder.image.setImageResource(product.getImageId());
        itemHolder.name.setText(product.getName());
        itemHolder.price.setText(Util.formatPrice(product.getPrice()));
        itemHolder.quantity.setText("Quantity: " + cart.getQuantity(position));
    }

    @Override
    public int getItemCount() {
        return Cart.getInstance().getNumOfProducts();
    }

    private class CartItemHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, price;
        TextView quantity;

        public CartItemHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.thumbnail);
            name = (TextView) view.findViewById(R.id.product_name);
            price = (TextView) view.findViewById(R.id.price);
            quantity = (TextView) view.findViewById(R.id.quantity);
        }
    }
}