package billing;

public abstract class Product implements Taxable {
    private final String code;
    private final String name;
    private final long unitPricePaise;

    public Product(String code, String name, long unitPricePaise) {
        this.code = code;
        this.name = name;
        this.unitPricePaise = unitPricePaise;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public long getUnitPricePaise() {
        return unitPricePaise;
    }

    @Override
    public abstract int getTaxRatePercent();
}
