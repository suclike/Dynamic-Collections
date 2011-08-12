package org.jnape.dynamiccollection;

import org.jnape.dynamiccollection.datatype.Partition;
import org.jnape.dynamiccollection.lambda.Function;
import org.jnape.dynamiccollection.lambda.Procedure;
import org.jnape.dynamiccollection.list.DynamicList;

import java.util.Collection;

public interface DynamicCollection<Element> extends Collection<Element> {

    DynamicCollection<Element> concat(Collection<Element> collection);

    DynamicCollection<Element> each(Procedure<Element> procedure);

    DynamicCollection<Element> collect(Function<Element, Boolean> collector);

    <Transformation> DynamicCollection<Transformation> transform(Function<Element, Transformation> transformer);

    DynamicList<Element> without(Element... subtractions);

    Partition<Element> partition(Function<Element, Boolean> sieve);

    DynamicCollection<Element> unique();
}
