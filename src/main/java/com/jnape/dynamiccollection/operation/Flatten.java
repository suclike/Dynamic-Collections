package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.datatype.option.Option;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Flatten {
    public static <Element> List<Element> flatten(Collection<Option<Element>> options) {
        List<Element> flattened = new ArrayList<Element>();

        for (Option<Element> option : options)
            if (!option.isNone())
                flattened.add(option.get());

        return flattened;
    }
}
