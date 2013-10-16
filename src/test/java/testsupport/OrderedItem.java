package testsupport;

public class OrderedItem extends Item {

    private final int order;

    public OrderedItem(String label, int order) {
        super(label);
        this.order = order;
    }

    public int getOrder() {
        return order;
    }
}
