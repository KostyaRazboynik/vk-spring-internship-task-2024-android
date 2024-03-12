# Product List

## Description

A small application that makes requests to [dummy json](https://dummyjson.com/products) which is a list of products and displays the contents in the recycler view list.

The request sends 20 products each and fills the recycler view with them, when one of the last items in the list is reached, a new request is made for the next 20 items, after which the displayed list increases.

You can click on the product card to view all its parameters

## Technologies used

- Retrofit + OkHttp3 + Gson for network requests and parsing of received data
- [Dagger2](https://github.com/google/dagger) for dependency injection
- [Fresco image loader](https://frescolib.org/)
- ViewBinding by [ViewBindingPropertyDelegate](https://github.com/androidbroadcast/ViewBindingPropertyDelegate)
- [other used libs](https://github.com/KostyaRazboynik/vk-spring-internship-task-2024-android/blob/main/gradle/libs.versions.toml)


