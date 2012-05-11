package com.jnape.dynamiccollection.operation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Reverse {

    public static <Element> List<Element> reverse(List<Element> list) {
        List<Element> reversed = new ArrayList<Element>();

        reversed.addAll(list);
        Collections.reverse(reversed);

        return reversed;
    }
}
