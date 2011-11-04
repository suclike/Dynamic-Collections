package org.jnape.dynamiccollection.list.operation;

import org.jnape.dynamiccollection.DynamicCollection;
import org.jnape.dynamiccollection.lambda.Procedure;

public class Each {

    public <Element> void iterativelyApply(DynamicCollection<Element> dynamicCollection, Procedure<Element> procedure) {
        for (Element element : dynamicCollection)
            procedure.execute(element);
    }
}
