package com.jnape.dynamiccollection.operation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Take {

    public static <Element> Collection<Element> take(int n, Iterable<Element> iterable) {
        Collection<Element> taken = new ArrayList<Element>(n);

        Iterator<Element> iterator = iterable.iterator();
        int i = 0;
        while (iterator.hasNext() && i++ < n)
            taken.add(iterator.next());

        return taken;
    }
}
