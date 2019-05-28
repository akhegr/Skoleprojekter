package model;
import java.util.Map;
import java.util.HashMap;
import exceptions.*;

public class OrderContainer
{
    // instance variables - replace the example below with your own
    private Map<Integer, Order> orderContainer;
    private static OrderContainer instance; 
    private int id;
    /**
     * Constructor for objects of class OrderContainer
     */
    private OrderContainer()
    {
        // initialise instance variables
        orderContainer = new HashMap<Integer, Order>();
        id = 0;
    }
    
    /**
     * getInstance()
     */
    public static OrderContainer getInstance()
    {
        if(instance == null)
        {
            instance = new OrderContainer();
        }
        
        return instance;
    }
    
    /**
     * Opret ordre
     *
     * @param date Datoen for købet
     * @param employee Medarbejderen ved kassen
     * @return int ordrenummer
     */
    public int createOrder(String date, Employee employee)
    {
        Order order = new Order(date, employee);
        orderContainer.put(id, order);
        int tempId = id;
        id++;
        return tempId;
    }
    
    /**
     * Find en ordre ved hjælp af dens id
     *
     * @param id Ordrens id
     * @return Order Den samlede ordre
     */
    public Order findOrder(int id) throws exceptions.OrderNotExistException
    {
        Order foundOrder = orderContainer.get(id);
        if(foundOrder == null)
        {
            throw new exceptions.OrderNotExistException(id);
        }
        
        return foundOrder;
    }
    
    /**
     * Fuldfør ordren ved at betale, hvis der er penge nok på kontoen
     *
     * @param orderId Ordrens nummer
     * @return boolean Status for betalingen
     */
    public boolean finishOrder(int orderId) throws exceptions.OrderNotExistException
    {   
        Order order = findOrder(orderId);
        boolean status = false;
        
        if(order.getCustomer() == null)
        {
            status = true;
            order.setIsPayed(true);
        }
        else
        {
            Customer customer = order.getCustomer();
            double total = order.getTotal();
            double account = customer.getAccount();
            
            if(account >= total)
            {
                status = true;
                order.setIsPayed(true);
            }
        }
        
        return status;
    }
}
