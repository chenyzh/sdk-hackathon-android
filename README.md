# SDK Hackathon Android Demo

This is a demo Android app that is used for SDK Hackathon.

We suggest to track the following in-app events via Firebase:

| Event                 |  Triggered...                             |  Parameters                                                                             |
| --------------------- | ----------------------------------------- | --------------------------------------------------------------------------------------- |  
| ecommerce_purchase    |  when a user completes a purchase         |  currency, value                                                                        |
| view_item             |  when a user views an item                |  item_id, item_name, item_category, currency, price                   |
| view_item_list        |  when a user views a list of items        |  item_category                                                                          |   
| add_to_cart           |  when a user adds an item to cart         |  item_id, item_name, item_category, currency, price, quantity, value  |  

## Disclaimer

This is a personal project.

## License

The software is released under [Apache 2.0 License](https://www.apache.org/licenses/LICENSE-2.0); see the `LICENSE` file for more information.
```
Copyright 2019 wentaoli

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
