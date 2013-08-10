package com.jnape.dynamiccollection.operation;

import java.util.ArrayList;
import java.util.List;

public class AllSequencesOf {
    public static <Element> List<List<Element>> allSequencesOf(int n, List<Element> list) {
        List<List<Element>> sequences = new ArrayList<List<Element>>();

        for (int i = 0, numberOfSequences = (list.size() - n) + 1; i < numberOfSequences; i++)
            sequences.add(list.subList(i, i + n));

        return sequences;
    }
}
