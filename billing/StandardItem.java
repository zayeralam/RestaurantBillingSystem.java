package billing;

public class StandardItem extends Product {
    public StandardItem(String code, String name, long unitPricePaise) {
        super(code, name, unitPricePaise);
    }

    @Override
    public int getTaxRatePercent() {
        return 5;
    }
}
