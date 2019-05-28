package model;
import java.util.Map;
import java.text.DecimalFormat;
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
		// Initialize instance variables
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
	 * Create order
	 *
	 * @param date Date of purchase
	 * @param employee The employee at checkout
	 * @return int Order number
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
	 * Find an order using its ID
	 *
	 * @param id Order id
	 * @return Order The total order
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
	 * Returns the order's sum with discount offset
	 * 
	 * @param orderId The oder id
	 * @return The order's total minus discount
	 * @throws exceptions.OrderNotExistException
	 */
	public double sumWithDiscount(int orderId) throws exceptions.OrderNotExistException
	{
		Order order = findOrder(orderId);
		Customer customer = order.getCustomer();
		double total = order.getTotal();
		double percent = customer.getDiscount();
		double discount = (total * (percent/100));
		
		return (total - discount);
	}
	
	
	/**
	 * Checking if the customer has enough money to pay the order
	 *
	 * @param orderId The order's number
	 * @return boolean Status of money on the account
	 */
	public boolean checkStatus(int orderId) throws OrderNotExistException
	{
		Order order = findOrder(orderId);
		boolean status = false;
		
		if(order.getCustomer() == null)
		{
			status = true;
		}
		else
		{
			Customer customer = order.getCustomer();
			double account = customer.getAccount();
			
			if(account >= sumWithDiscount(orderId))
			{
				status = true;
			}
		}
		
		return status;
	}
	
	
	/**
	 * Complete the order by paying, as checkMoney (orderId) returns correctly
	 *
	 * @param orderId The order's number
	 * @return boolean Status for payment
	 */
	public boolean finishOrder(int orderId) throws OrderNotExistException
	{
		Order order = findOrder(orderId);
		boolean status;
		if(order.getCustomer() != null)
		{
			boolean canBePayed = checkStatus(orderId);
			if(canBePayed)
			{
				order.setIsPayed(true);
			}
			status = canBePayed;
		}
		else
		{
			status = true;
			order.setIsPayed(true);
		}
		return status;
	}
	
	/**
	 * Returns default commas
	 *
	 * @param value The order's total
	 * @return String The total with real commas
	 */
	public String getCurrencyFormat(double value) {
		DecimalFormat formatter = (DecimalFormat) DecimalFormat.getInstance();
		formatter.applyPattern("#,###,##0.00");
		return formatter.format(value);
		
		//NumberFormat nf = NumberFormat.getNumberInstance(Locale.getDefault());
		//nf.setGroupingUsed(true);
		//return nf.format(value);
	}
}
