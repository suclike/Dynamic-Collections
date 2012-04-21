package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.list.DynamicArrayList;
import com.jnape.dynamiccollection.list.DynamicList;

import java.util.Collections;
import java.util.List;

public class Reverse {

    public static <Element> DynamicList<Element> reverse(List<Element> list) {
        DynamicList<Element> reversed = new DynamicArrayList<Element>(list);
        Collections.reverse(reversed);
        return reversed;
    }
}
