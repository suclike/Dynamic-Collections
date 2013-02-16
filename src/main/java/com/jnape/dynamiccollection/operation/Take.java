package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.Predicate;

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

    public static <Element> Collection<Element> takeWhile(Predicate<? super Element> predicate, Iterable<Element> iterable) {
        Collection<Element> taken = new ArrayList<Element>();

        Iterator<Element> iterator = iterable.iterator();
        Element next;
        while (iterator.hasNext() && predicate.apply(next = iterator.next()))
            taken.add(next);

        return taken;
    }
}
