package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;
import com.jnape.dynamiccollection.lambda.monadic.Predicate;
import com.jnape.dynamiccollection.list.DynamicList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.jnape.dynamiccollection.list.factory.DynamicListFactory.list;

public class Group {

    public static <Element> List<List<Element>> group(Collection<Element> elements) {
        return group(elements, new MonadicFunction<Element, Object>() {
            @Override
            public Object apply(Element element) {
                return element;
            }
        });
    }

    public static <Element, Output> List<List<Element>> group(Collection<Element> elements,
                                                              final MonadicFunction<? super Element, Output> mapper) {
        List<List<Element>> groups = new ArrayList<List<Element>>();
        DynamicList<Element> candidates = list(elements);

        while (!candidates.isEmpty()) {
            final Output matcher = mapper.apply(candidates.first());
            List<Element> group = candidates.filter(new Predicate<Element>() {
                @Override
                public Boolean apply(Element candidate) {
                    return mapper.apply(candidate).equals(matcher);
                }
            });
            candidates.removeAll(group);
            groups.add(group);
        }

        return groups;
    }
}
