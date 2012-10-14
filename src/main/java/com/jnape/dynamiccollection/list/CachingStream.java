package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.lambda.Function;

import java.util.ArrayList;
import java.util.Collection;

import static com.jnape.dynamiccollection.list.DynamicArrayList.list;

public class CachingStream<Element> implements Stream<Element> {

    private final DynamicList<Element>                           cache;
    private final CapacityMonitor<Element, DynamicList<Element>> capacityMonitor;

    public CachingStream(Collection<Element> elements, Function<DynamicList<Element>, Element> generator) {
        cache = list(elements);
        capacityMonitor = new CapacityMonitor<Element, DynamicList<Element>>(cache, generator);
    }

    public CachingStream(Function<DynamicList<Element>, Element> generator) {
        this(new ArrayList<Element>(), generator);
    }

    @Override
    public DynamicList<Element> take(int elements) {
        capacityMonitor.ensureCapacity(elements);
        return cache.subList(0, elements);
    }
}
