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
import com.sdk.android_demo.model.Product;
import com.sdk.android_demo.utils.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProductListAdapter
        extends RecyclerView.Adapter
{
    private final List<Product> products;
    private final int layout;

    public ProductListAdapter(Collection<Product> productList, int layoutResource) {
        products = new ArrayList<>(productList);
        layout = layoutResource;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(layout, parent, false);
        return new ProductHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProductHolder productHolder = (ProductHolder) holder;
        final Product product = products.get(position);
        productHolder.image.setImageResource(product.getImageId());
        productHolder.name.setText(product.getName());
        productHolder.price.setText(Util.formatPrice(product.getPrice()));
        productHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity activity = (BaseActivity)v.getContext();
                activity.navToProductPage(product.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ProductHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, price;

        public ProductHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.thumbnail);
            name = (TextView) view.findViewById(R.id.product_name);
            price = (TextView) view.findViewById(R.id.price);
        }
    }
}
