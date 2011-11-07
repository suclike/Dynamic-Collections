package org.jnape.dynamiccollection;

import org.jnape.dynamiccollection.datatype.Partition;
import org.jnape.dynamiccollection.lambda.Function;
import org.jnape.dynamiccollection.lambda.Procedure;

import java.util.Collection;

public interface DynamicCollection<Element> extends Collection<Element> {

    DynamicCollection<Element> concat(Collection<Element> collection);

    DynamicCollection<Element> each(Procedure<Element> iterativeProcedure);

    DynamicCollection<Element> collect(Function<Element, Boolean> collectionFunction);

    DynamicCollection<Element> reduce(Function<Element, Boolean> reductionFunction);

    <Transformation> DynamicCollection<Transformation> transform(Function<Element, Transformation> transformationFunction);

    DynamicCollection<Element> without(Element... exclusions);

    Partition<Element> partition(Function<Element, Boolean> sieve);

    DynamicCollection<Element> unique();
}
