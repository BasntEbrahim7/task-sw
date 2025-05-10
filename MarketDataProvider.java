import java.math.BigDecimal;

public interface MarketDataProvider {
    BigDecimal getStockPrice(String tickerSymbol);
    BigDecimal getCryptoPrice(String coinSymbol);
    BigDecimal getGoldPrice(String purity);
    BigDecimal estimateRealEstateValue(BigDecimal initialValue);
}