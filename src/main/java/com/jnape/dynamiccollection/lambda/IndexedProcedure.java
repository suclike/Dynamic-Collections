package com.jnape.dynamiccollection.lambda;

public interface IndexedProcedure<Element> {
    void execute(Element element, Integer integer);
}
