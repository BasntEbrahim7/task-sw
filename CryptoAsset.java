import java.math.BigDecimal;
import java.time.LocalDate;

// CryptoAsset.java
public class CryptoAsset extends Asset {
    private final String coinSymbol;
    private final BigDecimal quantity;
    private final MarketDataProvider dataProvider;

    public CryptoAsset(String userId, BigDecimal initialValue, LocalDate purchaseDate,
                       String coinSymbol, BigDecimal quantity, MarketDataProvider dataProvider) {
        super(userId, AssetType.CRYPTO, initialValue, purchaseDate);
        this.coinSymbol = coinSymbol;
        this.quantity = quantity;
        this.dataProvider = dataProvider;
    }

    public void updateMarketValue() {
        BigDecimal currentPrice = dataProvider.getCryptoPrice(coinSymbol);
        setCurrentValue(currentPrice.multiply(quantity));
    }

    public String getCoinSymbol() { return coinSymbol; }
    public BigDecimal getQuantity() { return quantity; }
}
