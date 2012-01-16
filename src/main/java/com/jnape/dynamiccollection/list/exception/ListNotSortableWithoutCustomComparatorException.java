package com.jnape.dynamiccollection.list.exception;

import java.util.List;

public class ListNotSortableWithoutCustomComparatorException extends RuntimeException {

    public ListNotSortableWithoutCustomComparatorException(List<?> list) {
        super("List cannot be sorted without custom comparator: <" + list + ">");
    }
}
