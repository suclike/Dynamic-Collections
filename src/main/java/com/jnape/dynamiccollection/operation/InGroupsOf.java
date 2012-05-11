package com.jnape.dynamiccollection.operation;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.min;

public class InGroupsOf {

    public static <Element> List<List<Element>> inGroupsOf(List<Element> elements, int elementsPerGroup) {
        ensureAtLeastOne(elementsPerGroup);

        List<Element> defensiveCopy = new ArrayList<Element>(elements);
        List<List<Element>> groups = new ArrayList<List<Element>>();

        int index = -elementsPerGroup;
        int numberOfElements = defensiveCopy.size();
        while ((index += elementsPerGroup) < numberOfElements) {
            int endIndex = min((index + elementsPerGroup), numberOfElements);
            groups.add(defensiveCopy.subList(index, endIndex));
        }

        return groups;
    }

    private static void ensureAtLeastOne(int elementsPerGroup) {
        if (elementsPerGroup < 1)
            throw new IllegalArgumentException("<" + elementsPerGroup + "> elements per group doesn't make any sense. Try at least <1>");
    }
}
