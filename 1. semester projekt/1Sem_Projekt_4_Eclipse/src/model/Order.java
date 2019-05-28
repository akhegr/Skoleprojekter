package model;
import java.util.Map;
import java.util.HashMap;

public class Order
{
    private int oliId;
    private Map<Integer, OrderLineItem> orderLineItems;
    private String date;
    private Employee employee;
    private Customer customer;
    private boolean isPayed;
    private double total;
    private CustomerContainer customerContainer;
    
    public Order(String date, Employee employee)
    {
        customerContainer    = CustomerContainer.getInstance();
        oliId = 0;
        orderLineItems = new HashMap<Integer, OrderLineItem>();
        setDate(date);
        setEmployee(employee);
        setIsPayed(false);
        setTotal(0);
    }
    
    // Get and set methods
    public String getDate()
    {
        return date;
    }
    
    public void setDate(String date)
    {
        this.date = date;
    }
    
    public Customer getCustomer()
    {
        return customer;
    }
    
    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }
    
    public Employee getEmployee()
    {
        return employee;
    }
    
    public void setEmployee(Employee employee)
    {
        this.employee = employee;
    }
    
    public boolean getIsPayed()
    {
        return isPayed;
    }
    
    public void setIsPayed(boolean isPayed)
    {
        this.isPayed = isPayed;
    }
    
    public double getTotal()
    {
        return total;
    }
    
    public void setTotal(double total)
    {
        this.total = total;
    }
    
    public void addCustomer(int customerPhone) throws exceptions.PersonNotExistException
    {
        customer = customerContainer.findCustomer(customerPhone);
    }
    
    /**
     * Calculation of the total price for OrderLineItem
     *
     * @param amount The amount of that product
     * @param product The specific product
     * @return The total price of the product
     */
    public double productPrice(int amount, Product product)
    {
        return product.getPrice() * amount;
    }
    
    /**
     * Add an OrderLineItem to the order
     *
     * @param amount The amount of that product
     * @param product The specific product
     */
    public void addOli(int amount, Product product)
    {
        OrderLineItem oli = new OrderLineItem(amount, product);
        orderLineItems.put(oliId, oli);
        oliId++;
        total += productPrice(amount, product);
    }
    
    /**
     * Returns the purchased items
     *
     * @return Map A Map of the purchased goods
     */
    public Map<Integer, OrderLineItem> getOli()
    {
    	return orderLineItems;
    }
}
