package model;
import java.util.Map;
import java.util.HashMap;
import exceptions.*;

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
    
    // Get og set metoder
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
     * Beregning af den samlede pris for OrderLineItem
     *
     * @param amount Mængden af det produktet
     * @param product Det specifikke produkt
     * @return Den samlede pris for produktet
     */
    public double productPrice(int amount, Product product)
    {
        return product.getPrice() * amount;
    }
    
    /**
     * Tilføje et OrderLineItem til ordren
     *
     * @param amount Mængden af det produktet
     * @param product Det specifikke produkt
     */
    public void addOli(int amount, Product product)
    {
        OrderLineItem oli = new OrderLineItem(amount, product);
        orderLineItems.put(oliId, oli);
        oliId++;
        total += productPrice(amount, product);
    }
    
}
