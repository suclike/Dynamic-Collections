package testsupport;

import static org.apache.commons.lang.builder.ToStringBuilder.reflectionToString;

public class Item {

    private final String label;

    public Item(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return reflectionToString(this);
    }
}
