package controller;
import model.*;
import exceptions.*;

public class ProductCtrl
{
    private ProductContainer productContainer;

    public ProductCtrl()
    {
        productContainer = ProductContainer.getInstance();
    }
    
    /**
     * Opret product
     *
     * @param barcode Product's barcode
     * @param category Product's category
     * @param name Product's name
     * @param price Product's price
     * @param amount Amount of products
     */
    public void createProduct(int barcode, String category, String name, double price, int amount)
    {
        productContainer.createProduct(barcode, category, name, price, amount);
    }
    
    /**
     * Find product
     *
     * @param barcode Product's barcode
     * @return Product The sought product
     */
    public Product findProduct(int barcode)
          throws ProductNotExistException
    {
        return productContainer.findProduct(barcode);
    }
    
    /**
     * Update product
     *
     * @param barcode Product's barcode
     * @param price Product's price
     */
    public void updateProduct(int barcode, double price)
                 throws ProductNotExistException
    {
        productContainer.updateProduct(barcode, price);
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
        productContainer.updateAmount(barcode, amount);
    }
    
    /**
     * Delete amount from stock
     *
     * @param barcode Product's barcode
     * @param amount Amount of products
     */
    public void retrieveAmount(int barcode, int deleteAmount)
          throws ProductNotExistException, ProductNotOnStockException
    {
        productContainer.retrieveAmount(barcode, deleteAmount);
    }
    
    /**
     * Find Product's category
     *
     * @param barcode Product's barcode
     * @return String Product's category
     */
    public String findCategory(int barcode)
          throws ProductNotExistException
    {
        Product foundProduct = findProduct(barcode);
        return foundProduct.getCategory();
    }
    
    /**
     * Find Product's name
     *
     * @param barcode Product's barcode
     * @return String Product's name
     */
    public String findName(int barcode)
          throws ProductNotExistException
    {
        Product foundProduct = findProduct(barcode);
        return foundProduct.getName();
    }
    
    /**
     * Find Product's price
     *
     * @param barcode Product's barcode
     * @return double Product's price
     */
    public double findPrice(int barcode)
          throws ProductNotExistException
    {
        Product foundProduct = findProduct(barcode);
        return foundProduct.getPrice();
    }    
}