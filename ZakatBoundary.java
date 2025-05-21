/**
 * UI layer responsible for interacting with the user regarding Zakat-related features.
 */
public class ZakatBoundary {
    /**
     * Displays a placeholder message for Zakat calculation output.
     * This method is intended to be expanded to show actual calculation results in the UI.
     */
    void displayCalculation()
    {
        System.out.println("This is the zakat calculation ");
    }

    /**
     * Submits the user's financial details and asset data to the controller for Zakat calculation.
     *
     * @param inputDetails a string containing user's input information (e.g., user ID, date, or filters)
     * @param assets an array of the user's financial assets
     */
    void submitDetails(String inputDetails, Asset[] assets){
        ZakatController zakatController = new ZakatController();
        zakatController.calculateZakat(inputDetails);
    }

/*
    /*
    /**
     * Displays the generated PDF report for the Zakat calculation.
     * This method is currently commented out and under development.
     *
     * @param userId the ID of the user whose Zakat report should be displayed
     */
    /*
    void displayPdf(String user123){
        ZakatController zakatController = new ZakatController();
        System.out.println("This is the zakat pdf ");
        zakatController.toPDF();

    }
*/
}