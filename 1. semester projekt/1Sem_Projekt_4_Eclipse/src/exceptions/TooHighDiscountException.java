package exceptions;

public class TooHighDiscountException extends Exception
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	double discount;
    
    public TooHighDiscountException(double discount)
    {
        this.discount = discount;
    }

    public double getDiscount()
    {
        return discount;
    }
    
    public String toString()
    {
        String returnMessage = "Rabatprocenten på " + getDiscount() + "% er for høj!";
        return returnMessage;
    }

}
