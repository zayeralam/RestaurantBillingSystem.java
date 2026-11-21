package billing;

public class Bill {
    private static final int MAX_LINES = 50;
    private static int nextBillNumber = 1001;

    private final int billNumber;
    private final OrderLine[] lines = new OrderLine[MAX_LINES];
    private int lineCount = 0;
    private int discountPercent = 0;

    public Bill() {
        billNumber = nextBillNumber;
        nextBillNumber++;
    }

    public int getBillNumber() {
        return billNumber;
    }

    public int getLineCount() {
        return lineCount;
    }

    public OrderLine getLine(int index) {
        return lines[index];
    }

    public void addLine(Product product, int quantity) throws InvalidQuantityException {
        if (lineCount == MAX_LINES) {
            throw new IllegalStateException("Bill is full");
        }
        lines[lineCount] = new OrderLine(product, quantity);
        lineCount++;
    }

    public void setDiscountPercent(int percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("Discount must be between 0 and 100");
        }
        discountPercent = percent;
    }

    public long getSubtotalPaise() {
        long sum = 0;
        for (int i = 0; i < lineCount; i++) {
            sum += lines[i].getSubtotalPaise();
        }
        return sum;
    }

    public long getTaxPaise() {
        long sum = 0;
        for (int i = 0; i < lineCount; i++) {
            sum += lines[i].getTaxPaise();
        }
        return sum;
    }

    public long getGrossPaise() {
        return getSubtotalPaise() + getTaxPaise();
    }

    public long getDiscountPaise() {
        return (getGrossPaise() * discountPercent + 50) / 100;
    }

    public long getGrandTotalPaise() {
        return getGrossPaise() - getDiscountPaise();
    }
}
