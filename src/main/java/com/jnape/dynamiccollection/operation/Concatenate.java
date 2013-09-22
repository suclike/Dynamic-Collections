package com.jnape.dynamiccollection.operation;

import java.util.ArrayList;
import java.util.Collection;

public class Concatenate {

    public static <Element> Collection<Element> concatenate(Collection<Element> collection1,
                                                            Collection<Element> collection2) {
        Collection<Element> concatenation = new ArrayList<Element>();

        concatenation.addAll(collection1);
        concatenation.addAll(collection2);

        return concatenation;
    }
}
