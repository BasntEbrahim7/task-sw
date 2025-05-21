/**
 * Interface that defines the contract for Zakat-related operations.
 */
public interface IZakatService {

    /**
     * Calculates the total Zakat obligation based on the user's assets.
     *
     * @param assets an array of Asset objects to be evaluated
     * @return the calculated Zakat amount
     */
    float calculateZakatObligation(Asset[] assets);

    /**
     * Generates a Zakat report for the specified user.
     *
     * @param userId the ID of the user for whom the report is to be generated
     * @return a ZakatReport containing details of the user's Zakat obligation
     */
    ZakatReport generateReport(String userId);
}
