package com.jnape.dynamiccollection.stream;

import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;
import com.jnape.dynamiccollection.list.DynamicList;
import com.jnape.dynamiccollection.operation.Take;

import java.util.ArrayList;
import java.util.Collection;

import static com.jnape.dynamiccollection.list.factory.DynamicListFactory.list;

public class CachingStream<Element> implements Stream<Element> {

    private final DynamicList<Element>                           cache;
    private final CapacityMonitor<Element, DynamicList<Element>> capacityMonitor;

    public CachingStream(Collection<Element> elements,
                         MonadicFunction<? super DynamicList<Element>, Element> generator) {
        cache = list(elements);
        capacityMonitor = new CapacityMonitor<Element, DynamicList<Element>>(cache, generator);
    }

    public CachingStream(MonadicFunction<? super DynamicList<Element>, Element> generator) {
        this(new ArrayList<Element>(), generator);
    }

    @Override
    public DynamicList<Element> take(int elements) {
        capacityMonitor.ensureCapacity(elements);
        return list(Take.take(cache, elements));
    }
}
