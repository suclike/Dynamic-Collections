package org.jnape.dynamiccollection.lambda;

public abstract class Procedure<Element> implements Function<Element, Void> {

    @Override
    public final Void apply(Element element) {
        execute(element);
        return null;
    }

    public abstract void execute(Element element);
}
