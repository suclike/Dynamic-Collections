package org.jnape.dynamiccollection.list.exception;

import java.util.List;

public class CouldNotInferComparatorException extends RuntimeException {

    public CouldNotInferComparatorException(List<?> list) {
        super("Could not infer comparator for list <" + list + ">");
    }
}
