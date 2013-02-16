package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.Predicate;

import java.util.Collection;

public class Any {


    private static final Predicate<Object> TRUE = new Predicate<Object>() {
        @Override
        public Boolean apply(Object anything) {
            return true;
        }
    };

    public static <Element> boolean any(Collection<Element> elements, Predicate<? super Element> matcher) {
        return anyWhile(elements, matcher, TRUE);
    }

    public static <Element> boolean anyWhile(Collection<Element> elements, Predicate<? super Element> matcher, Predicate<? super Element> predicate) {
        for (Element element : elements) {
            if (!predicate.apply(element))
                return false;

            if (matcher.apply(element))
                return true;
        }

        return false;
    }
}
