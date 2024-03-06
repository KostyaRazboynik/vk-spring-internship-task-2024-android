package com.kostyarazboynik.productlist.dagger

interface ComponentProvider<C> {
    val component: C
}
