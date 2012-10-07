package com.jnape.dynamiccollection.operation;

import java.util.ArrayList;
import java.util.Collection;

import static com.jnape.dynamiccollection.operation.Unique.unique;

public class Duplicates {

    public static <Element> Collection<Element> duplicates(Collection<Element> collection) {
        Collection<Element> discard = new ArrayList<Element>();
        Collection<Element> duplicates = new ArrayList<Element>();

        for (Element element : collection)
            (discard.contains(element) ? duplicates : discard).add(element);

        return unique(duplicates);
    }
}
