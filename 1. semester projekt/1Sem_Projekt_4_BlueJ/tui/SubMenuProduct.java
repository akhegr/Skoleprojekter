package tui;

import controller.*;
import java.util.Scanner;

public class SubMenuProduct
{
    //Instance variables
    private ProductCtrl pCtrl;
    private Scanner keyboard;

    public SubMenuProduct()
    {
        // initialise instance variables
        keyboard = new Scanner(System.in);
        pCtrl = new ProductCtrl();
    }

    public void start()
    {
        boolean finish = false;
        while(!finish)
        {
            System.out.println("*** Produkt menu ***");
            System.out.println("1. Opret produkt");
            System.out.println("2. Find produkt");
            System.out.println("3. Opdater produkt");
            System.out.println("4. Tilbage til hovedmenu\n");
            System.out.println("Indtast et tal mellem 1 og 5");

            String input = keyboard.nextLine();
            if(input.equals("1"))
            {
                addProduct();
            }
            else if(input.equals("2"))
            {
                findProduct();
            }
            else if(input.equals("3"))
            {
                updateProduct();
            }
            else if (input.equals("4"))
            {
                finish = true;
            }
            else
            {
                System.out.println("Prøv igen...");
            }
        }
    }
    
    public void addProduct()
    {
        System.out.println("*** Tilføj produkt ***");
        System.out.print("Indtast produktets navn: ");
        String name = keyboard.nextLine();
        
        System.out.println("Indtast produktets stregkode: ");
        int barcode = keyboard.nextInt();
        
        System.out.print("Indtast produktets kategori: ");
        String category = keyboard.nextLine();
        
        System.out.println("Indtast produktets pris: ");
        double price = keyboard.nextDouble();
        
        pCtrl.createProduct(barcode, category, name, price);
    }

    public void findProduct()
    {
        System.out.println("*** Find produkt ***");
        System.out.println("Indtast produktets stregkode:");
        int barcode = keyboard.nextInt();
        
        try
        {
           System.out.println("Produktets navn er: " + pCtrl.findName(barcode));
           System.out.println("Produktets kategori: " + pCtrl.findCategory(barcode));
           System.out.println("Produktets pris er: " + pCtrl.findPrice(barcode));
        }
        catch(exceptions.ProductNotExistException e)
        {
            System.out.println(e.toString());
        }
    }

    public void updateProduct()
    {
        System.out.println("*** Opdater produkt ***");
        System.out.println("Indtast produktets stregkode: ");
        int barcode = keyboard.nextInt();
        
        System.out.println("Indtast produktets nye pris: ");
        double price = keyboard.nextDouble();
        
        try
        {
           pCtrl.updateProduct(barcode, price);
        }
        catch(exceptions.ProductNotExistException e)
        {
            System.out.println(e.toString());
        }
    }
}