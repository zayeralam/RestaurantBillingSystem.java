package billing;

public class OrderLine {
    private final Product product;
    private final int quantity;

    public OrderLine(Product product, int quantity) throws InvalidQuantityException {
        if (quantity <= 0) {
            throw new InvalidQuantityException("Quantity must be positive, got " + quantity);
        }
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public long getSubtotalPaise() {
        return product.getUnitPricePaise() * quantity;
    }

    // Tax rounded half-up to the nearest paisa: (amount * rate + 50) / 100
    public long getTaxPaise() {
        return (getSubtotalPaise() * product.getTaxRatePercent() + 50) / 100;
    }

    public long getTotalPaise() {
        return getSubtotalPaise() + getTaxPaise();
    }
}
