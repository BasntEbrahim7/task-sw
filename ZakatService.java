/**
 * Service class responsible for calculating Zakat and generating reports.
 * Implements the IZakatService interface.
 */
public class ZakatService implements IZakatService {

    /**
     * Calculates the Zakat obligation for the given assets.
     * <p>
     * It checks if each asset has been owned for at least one year,
     * updates its market value, and includes its value in the total.
     * Then calculates 2.5% (0.025) of the total eligible asset value.
     * </p>
     *
     * @param assets an array of Asset objects to be evaluated
     * @return the calculated Zakat amount
     */
    @Override
    public float calculateZakatObligation(Asset[] assets) {
        double totalValue = 0;

        for (Asset asset : assets) {
            if (asset.oneYearPassed()) {
                asset.updateMarketValue(); // مهم علشان القيمة السوقية تكون محدثة
                totalValue += asset.calculateValue(); // لازم calculateValue ترجّع currentValue مثلاً
            } else {
                System.out.println("Asset " + asset + " not eligible: Less than 1 year.");
            }
        }

        double zakatAmount = totalValue * 0.025;
        System.out.println("Zakat amount: " + zakatAmount);
        return (float) zakatAmount;
    }

    /**
     * Generates a basic Zakat report for a user.
     * This is currently a mock implementation that prints a message.
     *
     * @param userId the ID of the user for whom the report is generated
     * @return a new ZakatReport instance
     */
    @Override
    public ZakatReport generateReport(String userId) {
        System.out.println("Generating zakat report for user: " + userId);
        return new ZakatReport();
    }
}
