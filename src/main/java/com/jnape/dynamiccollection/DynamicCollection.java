package com.jnape.dynamiccollection;

import com.jnape.dynamiccollection.datatype.Partition;
import com.jnape.dynamiccollection.lambda.Function;
import com.jnape.dynamiccollection.lambda.Procedure;

import java.util.Collection;

public interface DynamicCollection<Element> extends Collection<Element> {

    DynamicCollection<Element> concat(Collection<Element> collection);

    DynamicCollection<Element> each(Procedure<Element> procedure);

    DynamicCollection<Element> collect(Function<Element, Boolean> collector);

    DynamicCollection<Element> reject(Function<Element, Boolean> rejector);

    <Transformation> DynamicCollection<Transformation> transform(Function<Element, Transformation> transformer);

    DynamicCollection<Element> without(Element... exclusions);

    Partition<Element> partition(Function<Element, Boolean> partitioner);

    DynamicCollection<Element> unique();

    Boolean any(Function<Element, Boolean> matcher);
}
