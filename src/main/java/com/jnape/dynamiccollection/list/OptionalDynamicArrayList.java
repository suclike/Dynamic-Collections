package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.datatype.option.Option;
import com.jnape.dynamiccollection.operation.Flatten;

import java.util.Collection;
import java.util.Iterator;

public class OptionalDynamicArrayList<Element> extends DynamicArrayList<Option<Element>> implements OptionalDynamicList<Element> {

    public OptionalDynamicArrayList() {
    }

    public OptionalDynamicArrayList(Option<Element>[] options) {
        super(options);
    }

    public OptionalDynamicArrayList(Option<Element> elementOption,
                                    Option<Element>... options) {
        super(elementOption, options);
    }

    public OptionalDynamicArrayList(Iterator<? extends Option<Element>> iterator) {
        super(iterator);
    }

    public OptionalDynamicArrayList(
            Collection<? extends Option<Element>> options) {
        super(options);
    }

    @Override
    public DynamicArrayList<Element> flatten() {
        return new DynamicArrayList<Element>(Flatten.flatten(this));
    }
}
