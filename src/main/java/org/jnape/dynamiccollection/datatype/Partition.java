package org.jnape.dynamiccollection.datatype;

import org.jnape.dynamiccollection.DynamicCollection;

public interface Partition<Element> {

    DynamicCollection<Element> trues();

    DynamicCollection<Element> falses();
}
