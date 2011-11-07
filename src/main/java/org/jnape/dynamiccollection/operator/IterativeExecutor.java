package org.jnape.dynamiccollection.operator;

import org.jnape.dynamiccollection.lambda.Procedure;

import java.util.Collection;

public class IterativeExecutor {

    public <Element> void iterativelyExecute(Collection<Element> collection, Procedure<Element> procedure) {
        for (Element element : collection)
            procedure.execute(element);
    }
}
