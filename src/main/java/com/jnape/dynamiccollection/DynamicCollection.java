package com.jnape.dynamiccollection;

import com.jnape.dynamiccollection.datatype.Partition;
import com.jnape.dynamiccollection.lambda.Function;
import com.jnape.dynamiccollection.lambda.IndexedProcedure;
import com.jnape.dynamiccollection.lambda.Predicate;
import com.jnape.dynamiccollection.lambda.Procedure;

import java.util.Collection;

public interface DynamicCollection<Element> extends Collection<Element> {

    DynamicCollection<Element> concat(Collection<Element> collection);

    DynamicCollection<Element> concat(Element... elements);

    DynamicCollection<Element> each(IndexedProcedure<? super Element> indexedProcedure);

    DynamicCollection<Element> forEach(Procedure<? super Element> procedure);

    DynamicCollection<Element> filter(Predicate<? super Element> filterer);

    DynamicCollection<Element> reject(Predicate<? super Element> rejector);

    <Output> DynamicCollection<Output> map(Function<? super Element, Output> mapper);

    <Output> DynamicCollection<Output> mapWhile(Function<? super Element, Output> mapper, Predicate<? super Output> predicate);

    DynamicCollection<Element> without(Collection<? super Element> exclusions);

    Partition<Element> partition(Predicate<? super Element> partitioner);

    DynamicCollection<Element> unique();

    DynamicCollection<Element> unique(Function<? super Element, ?> mapper);

    DynamicCollection<Element> duplicates();

    DynamicCollection<? extends DynamicCollection<Element>> group();

    DynamicCollection<? extends DynamicCollection<Element>> group(Function<? super Element, ?> mapper);

    boolean any(Predicate<? super Element> matcher);

    boolean anyWhile(Predicate<? super Element> matcher, Predicate<? super Element> predicate);

    boolean all(Predicate<? super Element> matcher);

    boolean none(Predicate<? super Element> matcher);
}
