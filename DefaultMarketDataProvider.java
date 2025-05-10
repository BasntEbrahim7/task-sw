import java.math.BigDecimal;

public class DefaultMarketDataProvider implements MarketDataProvider {
    public BigDecimal getStockPrice(String tickerSymbol) {
        return BigDecimal.valueOf(150.75); // Mock price
    }

    public BigDecimal getCryptoPrice(String coinSymbol) {
        return BigDecimal.valueOf(45000.00); // Mock price
    }

    public BigDecimal getGoldPrice(String purity) {
        return BigDecimal.valueOf(60.50); // Mock price per gram
    }

    public BigDecimal estimateRealEstateValue(BigDecimal initialValue) {
        return initialValue.multiply(BigDecimal.valueOf(1.05)); // Mock estimation
    }
}
