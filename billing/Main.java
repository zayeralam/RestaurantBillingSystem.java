package billing;

public class Main {

    static String formatPaise(long paise) {
        long rupees = paise / 100;
        long paisa = paise % 100;
        String paisaText = (paisa < 10 ? "0" : "") + paisa;
        return "Rs." + rupees + "." + paisaText;
    }

    public static void main(String[] args) {
        Product standard = new StandardItem("S-01", "Standard Unit", 15000);
        Product premium = new PremiumItem("P-01", "Premium Unit", 42550);

        Bill bill = new Bill();
        try {
            bill.addLine(standard, 3);
            bill.addLine(premium, 2);
        } catch (InvalidQuantityException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }
        bill.setDiscountPercent(10);

        System.out.println("BILL NO: " + bill.getBillNumber());
        System.out.println("Code  Item            Qty  Amount        Tax           Total");
        for (int i = 0; i < bill.getLineCount(); i++) {
            OrderLine line = bill.getLine(i);
            Product p = line.getProduct();
            System.out.println(p.getCode() + "   " + p.getName() + "   " + line.getQuantity()
                    + "   " + formatPaise(line.getSubtotalPaise())
                    + "   " + formatPaise(line.getTaxPaise())
                    + "   " + formatPaise(line.getTotalPaise()));
        }
        System.out.println("Subtotal:    " + formatPaise(bill.getSubtotalPaise()));
        System.out.println("Tax:         " + formatPaise(bill.getTaxPaise()));
        System.out.println("Gross:       " + formatPaise(bill.getGrossPaise()));
        System.out.println("Discount:    " + formatPaise(bill.getDiscountPaise()));
        System.out.println("GRAND TOTAL: " + formatPaise(bill.getGrandTotalPaise()));

        try {
            new Bill().addLine(standard, 0);
        } catch (InvalidQuantityException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}
