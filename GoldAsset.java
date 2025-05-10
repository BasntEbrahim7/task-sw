import java.math.BigDecimal;
import java.time.LocalDate;

// GoldAsset.java
public class GoldAsset extends Asset {
    private final double weightInGrams;
    private final String purity;
    private final MarketDataProvider dataProvider;

    public GoldAsset(String userId, BigDecimal initialValue, LocalDate purchaseDate,
                     double weightInGrams, String purity, MarketDataProvider dataProvider) {
        super(userId, AssetType.GOLD, initialValue, purchaseDate);
        this.weightInGrams = weightInGrams;
        this.purity = purity;
        this.dataProvider = dataProvider;
    }

    public void updateMarketValue() {
        BigDecimal pricePerGram = dataProvider.getGoldPrice(purity);
        setCurrentValue(pricePerGram.multiply(BigDecimal.valueOf(weightInGrams)));
    }

    public double getWeightInGrams() { return weightInGrams; }
    public String getPurity() { return purity; }
}