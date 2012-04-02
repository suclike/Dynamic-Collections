package com.jnape.dynamiccollection.lambda;

public interface Procedure<Element> {
    void execute(Element element);
}
