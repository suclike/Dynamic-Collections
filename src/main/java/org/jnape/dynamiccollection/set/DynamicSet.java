package org.jnape.dynamiccollection.set;

import org.jnape.dynamiccollection.DynamicCollection;
import org.jnape.dynamiccollection.datatype.Partition;
import org.jnape.dynamiccollection.lambda.Function;
import org.jnape.dynamiccollection.lambda.Procedure;

import java.util.Collection;
import java.util.Set;

public interface DynamicSet<Element> extends Set<Element>, DynamicCollection<Element> {

    @Override
    DynamicSet<Element> concat(Collection<Element> collection);

    @Override
    DynamicSet<? extends DynamicSet<Element>> cartesianProduct(Collection<Element> collection);

    @Override
    DynamicSet<Element> each(Procedure<Element> procedure);

    @Override
    DynamicSet<Element> collect(Function<Element, Boolean> collector);

    @Override
    <Transformation> DynamicSet<Transformation> transform(Function<Element, Transformation> transformer);

    @Override
    DynamicSet<Element> without(Element... subtractions);

    //TODO: make this SetPartition when the time comes
    @Override
    Partition<Element> partition(Function<Element, Boolean> sieve);

    @Override
    DynamicSet<Element> unique();
}
