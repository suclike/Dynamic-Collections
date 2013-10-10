package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static com.jnape.dynamiccollection.lambda.monadic.builtin.Identity.id;

public class Group {

    public static <Element> List<List<Element>> group(Collection<Element> elements) {
        return groupBy(elements, id());
    }

    public static <Element, Index> List<List<Element>> groupBy(Collection<Element> elements,
                                                               final MonadicFunction<? super Element, Index> mapper) {
        java.util.Map<Index, List<Element>> groups = new HashMap<Index, List<Element>>();

        for (Element element : elements) {
            Index index = mapper.apply(element);

            List<Element> group = groups.get(index);
            if (group == null)
                group = new ArrayList<Element>();

            group.add(element);
            groups.put(index, group);
        }

        return new ArrayList<List<Element>>(groups.values());
    }
}
