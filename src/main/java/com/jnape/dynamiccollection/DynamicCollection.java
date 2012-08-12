package com.jnape.dynamiccollection;

import com.jnape.dynamiccollection.datatype.Partition;
import com.jnape.dynamiccollection.lambda.Function;
import com.jnape.dynamiccollection.lambda.IndexedProcedure;
import com.jnape.dynamiccollection.lambda.Procedure;

import java.util.Collection;

public interface DynamicCollection<Element> extends Collection<Element> {

    DynamicCollection<Element> concat(Collection<Element> collection);

    DynamicCollection<Element> each(IndexedProcedure<? super Element> indexedProcedure);

    DynamicCollection<Element> forEach(Procedure<? super Element> procedure);

    DynamicCollection<Element> filter(Function<? super Element, Boolean> filterer);

    DynamicCollection<Element> reject(Function<? super Element, Boolean> rejector);

    <Output> DynamicCollection<Output> map(Function<? super Element, Output> mapper);

    DynamicCollection<Element> without(Element... exclusions);

    Partition<Element> partition(Function<? super Element, Boolean> partitioner);

    DynamicCollection<Element> unique();

    Boolean any(Function<? super Element, Boolean> matcher);

    Boolean all(Function<? super Element, Boolean> matcher);
}
