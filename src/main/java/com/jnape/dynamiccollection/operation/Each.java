package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.IndexedProcedure;

import java.util.Collection;

public class Each {

    public static <Element> void each(Collection<Element> collection,
                                      IndexedProcedure<? super Element> indexedProcedure) {
        int i = 0;
        for (Element element : collection)
            indexedProcedure.execute(i++, element);
    }
}
