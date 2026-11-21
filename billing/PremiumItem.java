package billing;

public class PremiumItem extends Product {
    public PremiumItem(String code, String name, long unitPricePaise) {
        super(code, name, unitPricePaise);
    }

    @Override
    public int getTaxRatePercent() {
        return 12;
    }
}
