package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.Function;

import java.util.Collection;

public class Any {


    private static final Function<Object, Boolean> TRUE = new Function<Object, Boolean>() {
        @Override
        public Boolean apply(Object anything) {
            return true;
        }
    };

    public static <Element> boolean any(Collection<Element> elements, Function<? super Element, Boolean> matcher) {
        return anyWhile(elements, matcher, TRUE);
    }

    public static <Element> boolean anyWhile(Collection<Element> elements, Function<? super Element, Boolean> matcher, Function<? super Element, Boolean> predicate) {
        for (Element element : elements) {
            if (!predicate.apply(element))
                return false;

            if (matcher.apply(element))
                return true;
        }

        return false;
    }
}
