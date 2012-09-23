package testsupport;

import static java.lang.String.format;

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
        return format("{Item %s}", label);
    }
}
