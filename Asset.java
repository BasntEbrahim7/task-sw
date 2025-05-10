import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

// Asset.java (Abstract Base Class)
public abstract class Asset {
    private String id;
    private String userId;
    private AssetType type;
    private BigDecimal initialValue;
    private BigDecimal currentValue;
    private LocalDate purchaseDate;

    public Asset(String userId, AssetType type, BigDecimal initialValue, LocalDate purchaseDate) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.type = type;
        this.initialValue = initialValue;
        this.currentValue = initialValue;
        this.purchaseDate = purchaseDate;
    }

    public abstract void updateMarketValue();

    // Getters and Setters
    public String getId() { return id; }
    public String getUserId() { return userId; }
    public AssetType getType() { return type; }
    public BigDecimal getInitialValue() { return initialValue; }
    public BigDecimal getCurrentValue() { return currentValue; }
    public LocalDate getPurchaseDate() { return purchaseDate; }
    protected void setCurrentValue(BigDecimal value) { this.currentValue = value; }
    public void setInitialValue(BigDecimal initialValue) { this.initialValue = initialValue; }
    public void setPurchaseDate(LocalDate purchaseDate) { this.purchaseDate = purchaseDate; }
}
