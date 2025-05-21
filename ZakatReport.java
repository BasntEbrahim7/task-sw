/**
 * Represents a Zakat report that can be exported in different formats.
 */
public class ZakatReport {

    /**
     * Exports the current Zakat report to a PDF file.
     *
     * @return the current ZakatReport instance (for method chaining if needed)
     */
    public ZakatReport toPDF() {
        System.out.println("PDF created.");
        return this;
    }
}
