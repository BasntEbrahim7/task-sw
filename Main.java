import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        MarketDataProvider provider = new DefaultMarketDataProvider();
        AssetManager manager = new AssetManager();

        StockAsset stock = new StockAsset("user1", BigDecimal.valueOf(10000),
                LocalDate.of(2022, 5, 20), "AAPL", 50, provider);
        RealEstateAsset realEstate = new RealEstateAsset("user2", BigDecimal.valueOf(500000),
                LocalDate.of(2020, 3, 15), "123 Street", 120, provider);

        manager.addAsset(stock);
        manager.addAsset(realEstate);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Asset Management ===");
            System.out.println("1. View Assets\n2. Edit Asset\n3. Remove Asset\n4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    manager.getAllAssets().forEach(asset ->
                            System.out.println(asset.getId() + " - " + asset.getType() +
                                    " | Value: " + asset.getCurrentValue()));
                    break;
                case 2:
                    System.out.print("Enter Asset ID to edit: ");
                    String editId = scanner.nextLine();
                    Asset toEdit = manager.getAssetById(editId);
                    if (toEdit == null) {
                        System.out.println("Asset not found.");
                        break;
                    }
                    System.out.print("Enter new initial value: ");
                    BigDecimal newVal = scanner.nextBigDecimal();
                    System.out.print("Enter new purchase date (yyyy-mm-dd): ");
                    LocalDate newDate = LocalDate.parse(scanner.next());
                    if (manager.editAsset(editId, newVal, newDate)) {
                        System.out.println("Asset updated.");
                    } else {
                        System.out.println("Action not completed.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Asset ID to remove: ");
                    String removeId = scanner.nextLine();
                    System.out.print("Are you sure? (yes/no): ");
                    String confirm = scanner.nextLine();
                    if ("yes".equalsIgnoreCase(confirm) && manager.removeAsset(removeId)) {
                        System.out.println("Asset removed.");
                    } else {
                        System.out.println("Action not completed.");
                    }
                    break;
                case 4:
                    return;
            }
        }
    }
}