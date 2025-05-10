import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class AssetManager {
    private Map<String, Asset> assetMap = new HashMap<>();

    public void addAsset(Asset asset) {
        assetMap.put(asset.getId(), asset);
    }

    public Collection<Asset> getAllAssets() {
        return assetMap.values();
    }

    public Asset getAssetById(String id) {
        return assetMap.get(id);
    }

    public boolean removeAsset(String id) {
        return assetMap.remove(id) != null;
    }

    public boolean editAsset(String id, BigDecimal newValue, LocalDate newDate) {
        Asset asset = assetMap.get(id);
        if (asset == null) return false;
        asset.setInitialValue(newValue);
        asset.setPurchaseDate(newDate);
        asset.updateMarketValue();
        return true;
    }
}
