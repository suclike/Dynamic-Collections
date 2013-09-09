package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.datatype.option.Option;

public interface OptionalDynamicList<Element> extends DynamicList<Option<Element>> {

    DynamicList<Element> flatten();

}
