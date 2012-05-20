package com.jnape.dynamiccollection.list;

public interface ComparableDynamicList<Element extends Comparable<Element>> extends DynamicList<Element> {

    public Element min();

    public Element max();

    public ComparableDynamicList<Element> sort();
}
