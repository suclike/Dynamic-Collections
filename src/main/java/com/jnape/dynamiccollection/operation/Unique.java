package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.Function;
import com.jnape.dynamiccollection.lambda.Predicate;

import java.util.ArrayList;
import java.util.Collection;

import static com.jnape.dynamiccollection.operation.Any.any;

public class Unique {

    public static <Element> Collection<Element> unique(Collection<Element> collection, final Function<? super Element, ?> mapper) {
        Collection<Element> unique = new ArrayList<Element>();

        for (final Element element : collection) {
            Predicate<Element> haveEqualMappedOutputs = new Predicate<Element>() {
                @Override
                public Boolean apply(Element uniqueElement) {
                    return mapper.apply(element).equals(mapper.apply(uniqueElement));
                }
            };

            if (!any(unique, haveEqualMappedOutputs))
                unique.add(element);
        }

        return unique;
    }

    public static <Element> Collection<Element> unique(Collection<Element> collection) {
        Collection<Element> unique = new ArrayList<Element>();

        for (Element element : collection)
            if (!unique.contains(element))
                unique.add(element);

        return unique;
    }
}
