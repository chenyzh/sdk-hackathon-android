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

package com.sdk.android_demo.utils;

import com.sdk.android_demo.R;
import com.sdk.android_demo.model.Category;
import com.sdk.android_demo.model.Product;
import com.sdk.android_demo.model.Section;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sdk.android_demo.model.Category.ACCESSORIES;
import static com.sdk.android_demo.model.Category.APPAREL;
import static com.sdk.android_demo.model.Category.HEADWEAR;
import static com.sdk.android_demo.model.Category.OFFICE;
import static com.sdk.android_demo.model.Section.BEST_SELLER;
import static com.sdk.android_demo.model.Section.NEW_ARRIVAL;
import static com.sdk.android_demo.model.Section.RECOMMENDATION;

/**
 * This is a mock class that render hard-coded products
 */

public class MockItemProvider {
    //All products mapped by product ids
    private static final Map<Integer, Product> PRODUCT_MAP = new HashMap<>();
    //Product ids grouped by section (new arrivals, recommended, etc.)
    private static final Map<Section, List<Integer>> ID_BY_SECTION = new HashMap<>();

    static {
        addProduct(1, "Tee Green", APPAREL, 21.99, R.drawable.greentee);
        addProduct(2, "Tee Red", APPAREL, 21.99, R.drawable.redtee);
        addProduct(3, "Tee Yellow", APPAREL, 21.99, R.drawable.yellowtee);
        addProduct(4, "Zip Hoodie", APPAREL, 59.99, R.drawable.hoodie);
        addProduct(5, "Sweatershirt", APPAREL, 51.99, R.drawable.sweater);
        addProduct(6, "Notebook", OFFICE, 12.99, R.drawable.notebook);
        addProduct(7, "Sticker Sheet", ACCESSORIES, 2.99, R.drawable.sticker);
        addProduct(8, "Pom Beanie", HEADWEAR, 19.99, R.drawable.pombeanie);
        addProduct(9, "Black Cap", HEADWEAR, 17.99, R.drawable.blackhat);

        ID_BY_SECTION.put(NEW_ARRIVAL, Arrays.asList(1, 2, 3, 4, 5));
        ID_BY_SECTION.put(RECOMMENDATION, Arrays.asList(6, 7, 8, 9));
        ID_BY_SECTION.put(BEST_SELLER, Arrays.asList(4, 6, 8, 9));
    }

    public static Collection<Product> generateProductList(Section section) {
        List<Product> products = new ArrayList<>();
        for (int id : ID_BY_SECTION.get(section)) {
            products.add(getProductById(id));
        }
        return products;
    }

    public static Product getProductById(int id) {
        return PRODUCT_MAP.get(id);
    }

    private static void addProduct(int id, String name, Category category,
                                   double price, int imageId) {
        Product product = new Product(id, name, category, price, imageId);
        PRODUCT_MAP.put(product.getId(), product);
    }
}
