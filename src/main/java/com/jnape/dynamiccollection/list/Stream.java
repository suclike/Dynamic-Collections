package com.jnape.dynamiccollection.list;

public interface Stream<Element> {

    DynamicList<Element> take(int elements);
}
