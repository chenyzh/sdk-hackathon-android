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

public enum Section {
    NEW_ARRIVAL("NEW ARRIVALS"),
    RECOMMENDATION("JUST FOR YOU"),
    BEST_SELLER("BEST SELLERS");

    private final String title;

    private Section(String name) {
        title = name;
    }

    public String getTitle() {
        return title;
    }
}
