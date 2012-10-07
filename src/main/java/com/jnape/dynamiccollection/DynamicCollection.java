package com.jnape.dynamiccollection;

import com.jnape.dynamiccollection.datatype.Partition;
import com.jnape.dynamiccollection.lambda.Function;
import com.jnape.dynamiccollection.lambda.IndexedProcedure;
import com.jnape.dynamiccollection.lambda.Procedure;

import java.util.Collection;

public interface DynamicCollection<Element> extends Collection<Element> {

    DynamicCollection<Element> concat(Collection<Element> collection);

    DynamicCollection<Element> concat(Element... elements);

    DynamicCollection<Element> each(IndexedProcedure<? super Element> indexedProcedure);

    DynamicCollection<Element> forEach(Procedure<? super Element> procedure);

    DynamicCollection<Element> filter(Function<? super Element, Boolean> filterer);

    DynamicCollection<Element> reject(Function<? super Element, Boolean> rejector);

    <Output> DynamicCollection<Output> map(Function<? super Element, Output> mapper);

    <Output> DynamicCollection<Output> mapWhile(Function<? super Element, Output> mapper, Function<? super Output, Boolean> predicate);

    DynamicCollection<Element> without(Collection<? super Element> exclusions);

    Partition<Element> partition(Function<? super Element, Boolean> partitioner);

    DynamicCollection<Element> unique();

    DynamicCollection<Element> unique(Function<? super Element, ?> mapper);

    DynamicCollection<Element> duplicates();

    boolean any(Function<? super Element, Boolean> matcher);

    boolean all(Function<? super Element, Boolean> matcher);
}
