/**
 * Controller class that coordinates Zakat-related operations between UI and services.
 */
public class ZakatController {

    /**
     * Initiates the Zakat calculation process for a given user.
     * It retrieves the user's assets and passes them to the ZakatService for processing.
     *
     * @param userId the ID of the user whose Zakat should be calculated
     */
    void calculateZakat(String userId) {
        AssetService assetService = new AssetService();
        Asset[] assets = assetService.getUserAssets(userId);

        ZakatService zakatService = new ZakatService();
        zakatService.calculateZakatObligation(assets);
    }

/*
    /**
     * Generates a Zakat report for the user and exports it as a PDF file.
     * This method is currently commented out and under development.
     *
     * @param userId the ID of the user whose report should be generated
     */
    /*
    public void toPDF() {
        ZakatService service = new ZakatService();
        ZakatReport report = service.generateReport("user123");
        report.toPDF();
    }
*/
}
