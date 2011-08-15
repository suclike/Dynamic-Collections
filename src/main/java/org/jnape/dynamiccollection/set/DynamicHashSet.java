package org.jnape.dynamiccollection.set;

import org.jnape.dynamiccollection.datatype.Partition;
import org.jnape.dynamiccollection.lambda.Function;
import org.jnape.dynamiccollection.lambda.Procedure;

import java.util.Collection;
import java.util.HashSet;

import static java.util.Arrays.asList;

public class DynamicHashSet<Element> extends HashSet<Element> implements DynamicSet<Element> {


    public DynamicHashSet() {
        super();
    }

    public DynamicHashSet(Collection<Element> collection) {
        super(collection);
    }

    public DynamicHashSet(Element... elements) {
        super(asList(elements));
    }

    @Override
    public DynamicSet<Element> concat(Collection<Element> collection) {
        DynamicSet<Element> combined = new DynamicHashSet<Element>();

        combined.addAll(this);
        combined.addAll(collection);

        return combined;
    }

    @Override
    public DynamicSet<? extends DynamicSet<Element>> cartesianProduct(Collection<Element> collection) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public DynamicSet<Element> each(Procedure<Element> elementProcedure) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public DynamicSet<Element> collect(Function<Element, Boolean> collector) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public <Transformation> DynamicSet<Transformation> transform(Function<Element, Transformation> transformer) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public DynamicSet<Element> without(Element... subtractions) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //TODO: Change signature to return SetPartition when the time comes
    @Override
    public Partition<Element> partition(Function<Element, Boolean> sieve) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public DynamicSet<Element> unique() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
