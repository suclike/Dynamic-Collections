package com.jnape.dynamiccollection.lambda;

public interface Procedure<Element> {
    public void execute(Element element);
}
