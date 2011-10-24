package org.jnape.dynamiccollection.list.exception;

import java.util.List;

public class ListNotSortableWithoutCustomComparatorException extends RuntimeException {

    public ListNotSortableWithoutCustomComparatorException(List<?> list) {
        super("Could not infer comparator for list <" + list + ">");
    }
}
