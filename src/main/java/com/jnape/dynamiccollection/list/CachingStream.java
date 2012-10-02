package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.lambda.Function;

import java.util.ArrayList;
import java.util.Collection;

import static com.jnape.dynamiccollection.list.DynamicArrayList.list;

public class CachingStream<Element> implements Stream<Element> {

    private final Function<DynamicList<Element>, Element> generator;
    private final DynamicList<Element>                    cache;

    public CachingStream(Collection<Element> elements, Function<DynamicList<Element>, Element> generator) {
        this.cache = list(elements);
        this.generator = generator;
    }

    public CachingStream(Function<DynamicList<Element>, Element> generator) {
        this(new ArrayList<Element>(), generator);
    }

    @Override
    public DynamicList<Element> take(int elements) {
        ensureCapacity(elements);
        return cache.subList(0, elements);
    }

    private void ensureCapacity(int elements) {
        while (elements > cache.size()) {
            int size = cache.size();
            int newSize = size + size * 3 / 4;
            while (cache.size() <= newSize)
                cache.add(generator.apply(cache));
        }
    }
}
