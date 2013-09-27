package com.jnape.dynamiccollection.operation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shuffle {
    public static <Element> List<Element> shuffle(List<Element> elements) {
        List<Element> shuffled = new ArrayList<Element>(elements);
        Collections.shuffle(shuffled);
        return shuffled;
    }
}
