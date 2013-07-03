package com.jnape.dynamiccollection.operation;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.min;

public class Zip {

    public static <Element> List<List<Element>> zip(List<? extends Element> heads, List<? extends Element>... tails) {
        List<List<Element>> zipped = new ArrayList<List<Element>>();

        for (int i = 0; i < smallestListSize(heads, tails); i++) {
            List<Element> entry = new ArrayList<Element>();
            entry.add(heads.get(i));
            for (List<? extends Element> tail : tails)
                entry.add(tail.get(i));
            zipped.add(entry);
        }

        return zipped;
    }

    private static int smallestListSize(List heads, List... tails) {
        int smallestListSize = heads.size();

        for (List tail : tails)
            smallestListSize = min(smallestListSize, tail.size());

        return smallestListSize;
    }
}
