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

package com.sdk.android_demo.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static Cart instance = null;
    private final List<CartItem> products;

    public static Cart getInstance() {
        if(instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    private Cart() {
        products = new ArrayList<>();
    }

    public void add(Product product, int quantity) {
        for (CartItem item : products) {
            if (item.product.equals(product)) {
                item.update(quantity);
                return;
            }
        }
        products.add(new CartItem(product, quantity));
    }

    public double getSubTotal() {
        double total = 0;
        for (CartItem item : products) {
            total += item.product.getPrice() * item.num;
        }
        return total;
    }

    public void pay() {
        products.clear();
    }

    /**
     * Return the product instance at the specific position (starts from 0)
     * @param pos
     * @return
     */
    public Product getProduct(int pos) {
        return products.get(pos).product;
    }

    /**
     * Return the ordered quantify for product at the specific position (starts from 0)
     * @param pos
     * @return
     */
    public int getQuantity(int pos) {
        return products.get(pos).num;
    }

    /**
     * Return the total number of unique product
     * @return
     */
    public int getNumOfProducts() {
        return products.size();
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    private class CartItem {
        final Product product;
        int num;

        CartItem(Product p, int quantity) {
            product = p;
            num = quantity;
        }

        public void update(int increased) {
            num += increased;
        }
    }
}
