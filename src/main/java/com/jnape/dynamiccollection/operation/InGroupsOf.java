package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.list.DynamicArrayList;
import com.jnape.dynamiccollection.list.DynamicList;

import java.util.List;

import static java.lang.Math.min;

public class InGroupsOf {

    public static <Element> DynamicList<DynamicList<Element>> inGroupsOf(List<Element> elements, int elementsPerGroup) {
        ensureAtLeastOne(elementsPerGroup);

        DynamicList<Element> defensiveCopy = new DynamicArrayList<Element>(elements);
        DynamicList<DynamicList<Element>> groups = new DynamicArrayList<DynamicList<Element>>();

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
