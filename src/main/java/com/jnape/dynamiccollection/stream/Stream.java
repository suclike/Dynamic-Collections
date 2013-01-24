package com.jnape.dynamiccollection.stream;

import com.jnape.dynamiccollection.list.DynamicList;

public interface Stream<Element> {

    DynamicList<Element> take(int elements);
}
