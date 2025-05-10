import java.math.BigDecimal;
import java.time.LocalDate;

// RealEstateAsset.java
public class RealEstateAsset extends Asset {
    private final String propertyAddress;
    private final double squareMeters;
    private final MarketDataProvider dataProvider;

    public RealEstateAsset(String userId, BigDecimal initialValue, LocalDate purchaseDate,
                           String propertyAddress, double squareMeters, MarketDataProvider dataProvider) {
        super(userId, AssetType.REAL_ESTATE, initialValue, purchaseDate);
        this.propertyAddress = propertyAddress;
        this.squareMeters = squareMeters;
        this.dataProvider = dataProvider;
    }

    public void updateMarketValue() {
        BigDecimal estimatedValue = dataProvider.estimateRealEstateValue(getInitialValue());
        setCurrentValue(estimatedValue);
    }

    public String getPropertyAddress() { return propertyAddress; }
    public double getSquareMeters() { return squareMeters; }
}