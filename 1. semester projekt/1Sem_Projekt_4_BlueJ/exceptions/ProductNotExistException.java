package exceptions;

public class ProductNotExistException extends Exception
{
    int barcode;
    
    public ProductNotExistException(int barcode)
    {
        this.barcode = barcode;
    }

    public int getBarcode()
    {
        return barcode;
    }
    
    public String toString()
    {
        return "Produktet med stregkoden " + getBarcode() + " eksisterer ikke";
    }

}
