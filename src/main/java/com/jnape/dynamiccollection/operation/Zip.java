package com.jnape.dynamiccollection.operation;

import java.util.ArrayList;
import java.util.List;

public class Zip {

    public static <Element> List<List<Element>> zip(List<? extends Element> heads, List<? extends Element>... tails) {
        List<List<Element>> zipped = new ArrayList<List<Element>>();

        for (int i = 0; i < heads.size(); i++) {
            List<Element> entry = new ArrayList<Element>();
            entry.add(heads.get(i));
            for (List<? extends Element> tail : tails)
                entry.add(tail.size() > i ? tail.get(i) : null);
            zipped.add(entry);
        }

        return zipped;
    }
}
