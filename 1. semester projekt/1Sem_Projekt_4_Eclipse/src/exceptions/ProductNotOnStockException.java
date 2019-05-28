package exceptions;

public class ProductNotOnStockException extends Exception
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int barcode;
    
    public ProductNotOnStockException(int barcode)
    {
        this.barcode = barcode;
    }

    public int getBarcode()
    {
        return barcode;
    }
    
    public String toString()
    {
        return "Der er ikke nok produkter med stregkoden " + getBarcode() + " på lager.";
    }
}