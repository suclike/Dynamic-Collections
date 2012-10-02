package com.jnape.dynamiccollection.list;

import java.util.List;

public interface Stream<Element> {

    List<Element> take(int elements);
}
