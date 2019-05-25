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

import java.util.Objects;

public class Product {

    private final int id;
    private final String name;
    private final Category category;
    private final double price;
    private final int imageId;

    public Product(int id, String name, Category category, double price,
                   int imageResourceId) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.imageId = imageResourceId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category.name();
    }

    public double getPrice() {
        return price;
    }

    public int getImageId() {
        return imageId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Product)) {
            return false;
        }

        Product p = (Product) o;
        return p.getId() == this.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, price);
    }
}
