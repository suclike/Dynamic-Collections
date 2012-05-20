package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.operation.exception.OperationRequiresAtLeastOneElementException;

import java.util.Collection;
import java.util.Iterator;

//TODO: terrible names across the board; just pure garbage
public class Bound {

    public static <Element extends Comparable<Element>> Element max(Collection<Element> elements) {
        return getElementWithUltimateComparisonResult(elements, -1);
    }

    public static <Element extends Comparable<Element>> Element min(Collection<Element> elements) {
        return getElementWithUltimateComparisonResult(elements, 1);
    }

    private static <Element extends Comparable<Element>> Element getElementWithUltimateComparisonResult(Collection<Element> elements, int comparisonResult) {
        ensureNotEmpty(elements);
        Iterator<Element> iterator = elements.iterator();

        Element elementWithUltimateComparisonResult = iterator.next();
        while (iterator.hasNext())
            elementWithUltimateComparisonResult = getElementWithComparisonResult(elementWithUltimateComparisonResult, iterator.next(), comparisonResult);

        return elementWithUltimateComparisonResult;
    }

    private static <Element extends Comparable<Element>> void ensureNotEmpty(Collection<Element> elements) {
        if (elements.isEmpty())
            throw new OperationRequiresAtLeastOneElementException();
    }


    private static <Element extends Comparable<Element>> Element getElementWithComparisonResult(Element element1, Element element2, int n) {
        return element1.compareTo(element2) == n
                ? element2
                : element1;
    }

}
