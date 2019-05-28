package exceptions;

public class OrderNotExistException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int orderNo;
    
    public OrderNotExistException(int orderNo)
    {
        this.orderNo = orderNo;
    }

    public int getOrderNo()
    {
        return orderNo;
    }
    
    public String toString()
    {
        return "Ordren med nummeret " + getOrderNo() + " eksisterer ikke";
    }

}
