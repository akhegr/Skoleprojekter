package model;
import java.util.Map;

import exceptions.*;
import java.util.HashMap;

public class ProductContainer
{
    // instance variables - replace the example below with your own
    private Map<Integer, Product> productContainer;
    private static ProductContainer instance; 
    /**
     * Constructor for objects of class ProductContainer
     */
    private ProductContainer()
    {
        // initialize instance variables
        productContainer = new HashMap<Integer, Product>();
    }
    
    /**
     * getInstance()
     */
    public static ProductContainer getInstance()
    {
        if(instance == null)
        {
            instance = new ProductContainer();
        }
        
        return instance;
    }
    
    /**
     * Create product
     *
     * @param barcode Product's barcode
     * @param category Product's category
     * @param name Product's name
     * @param price Product's price
     * @param amount Amount of products
     */
    public void createProduct(int barcode, String category, String name, double price, int amount)
    {
        Product product = new Product(barcode, category, name, price, amount);
        productContainer.put(barcode, product);
    }
    
    /**
     * Find product
     *
     * @param barcode Product's barcode
     * @return Product The sought product
     */
    public Product findProduct(int barcode)
          throws exceptions.ProductNotExistException
    {
        Product foundProduct = productContainer.get(barcode);
        if(foundProduct == null)
        {
            throw new exceptions.ProductNotExistException(barcode);
        }
        
        return foundProduct;
    }
    
    /**
     * Update product
     *
     * @param barcode Product's barcode
     * @param price Product's new price
     */
    public void updateProduct(int barcode, double price)
          throws exceptions.ProductNotExistException
    {
        Product product = findProduct(barcode);
        product.setPrice(price);
        productContainer.put(barcode, product);
    }
    
    /**
     * Update stock
     *
     * @param barcode Product's barcode
     * @param amount Amount of products
     */
    public void updateAmount(int barcode, int amount)
          throws exceptions.ProductNotExistException
    {
        Product product = findProduct(barcode);
        product.setAmount(amount);
        productContainer.put(barcode, product);
    }
    
    /**
     * Delete amount from stock
     *
     * @param barcode Product's barcode
     * @param amount Amount of product
     * @throws ProductNotExistException 
     * @throws ProductNotOnStockException 
     */
    public void retrieveAmount(int barcode, int deleteAmount) throws ProductNotExistException, ProductNotOnStockException
    {
        Product foundProduct = findProduct(barcode);
        
        if(foundProduct == null)
        {
            throw new ProductNotExistException(barcode);
        }
        
        int newAmount = foundProduct.getAmount() - deleteAmount;
        
        if(newAmount < 0)
        {
        	throw new ProductNotOnStockException(barcode);
        }
        
        foundProduct.setAmount(newAmount);
    	productContainer.put(barcode, foundProduct);
    }
}
