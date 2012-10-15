package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.Function;
import com.jnape.dynamiccollection.list.DynamicList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.jnape.dynamiccollection.list.DynamicArrayList.list;

public class Group {

    public static <Element, Output> List<List<Element>> group(Collection<Element> elements, final Function<? super Element, Output> mapper) {
        List<List<Element>> groups = new ArrayList<List<Element>>();
        DynamicList<Element> candidates = list(elements);

        while (!candidates.isEmpty()) {
            final Output matcher = mapper.apply(candidates.first());
            List<Element> group = candidates.filter(new Function<Element, Boolean>() {
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
