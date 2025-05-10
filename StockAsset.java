import java.math.BigDecimal;
import java.time.LocalDate;

// StockAsset.java
public class StockAsset extends Asset {
    private final String tickerSymbol;
    private final int quantity;
    private final MarketDataProvider dataProvider;

    public StockAsset(String userId, BigDecimal initialValue, LocalDate purchaseDate,
                      String tickerSymbol, int quantity, MarketDataProvider dataProvider) {
        super(userId, AssetType.STOCK, initialValue, purchaseDate);
        this.tickerSymbol = tickerSymbol;
        this.quantity = quantity;
        this.dataProvider = dataProvider;
    }

    public void updateMarketValue() {
        BigDecimal currentPrice = dataProvider.getStockPrice(tickerSymbol);
        setCurrentValue(currentPrice.multiply(BigDecimal.valueOf(quantity)));
    }

    public String getTickerSymbol() { return tickerSymbol; }
    public int getQuantity() { return quantity; }
}