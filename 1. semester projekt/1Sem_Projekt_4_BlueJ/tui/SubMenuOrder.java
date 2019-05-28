package tui;

import controller.*;
import java.util.Scanner;
import exceptions.*;

public class SubMenuOrder
{
    //Instance variables
    private OrderCtrl oCtrl;
    private Scanner keyboard;
    
    public SubMenuOrder()
    {
        // initialise instance variables
        keyboard = new Scanner(System.in);
        oCtrl = new OrderCtrl();   
    }
    
    public void start()
    {
        boolean finish = false;
        while(!finish)
        {
            System.out.println("*** Ordremenu ***");
            System.out.println("1. Opret ordre");
            System.out.println("2. Tilføj produkt til ordre");
            System.out.println("3. Tilføj kunde til ordre");
            System.out.println("4. Fuldfør ordre");
            System.out.println("5. Tilbage til hovedmenu\n");
            System.out.println("Indtast et tal mellem 1 og 5");

            keyboard.reset();
            String input = keyboard.nextLine();
            if(input.equals("1"))
            {
                createOrder();
            }
            else if(input.equals("2"))
            {
                addProduct();
            }
            else if(input.equals("3"))
            {
                addCustomer();
            }
            else if(input.equals("4"))
            {
                finishOrder();
            }
            else if (input.equals("5"))
            {
                finish = true;
            }
            else
            {
                System.out.println("Prøv igen...");
            }
        }   
    }
    
    public void createOrder()
    {
        System.out.println("*** Opret ordre ***");
        System.out.print("Indtast medarbejderens telefonnummer: ");
        int phone = keyboard.nextInt();
        
        System.out.print("Indtast dagens dato: ");
        String date = keyboard.nextLine();
        
        try
        {
           System.out.println("Ordrens id er: " + oCtrl.createOrder(date, phone));
        }
        catch(exceptions.PersonNotExistException e)
        {
            System.out.println(e.toString());
        }
    }
    
    public void addProduct()
    {
        System.out.println("*** Tilføj produkt til ordre ***");
        System.out.println("Indtast ordrens nummer: ");
        int orderId = keyboard.nextInt();
        
        System.out.println("Indtast produktets stregkode: ");
        int barcode = keyboard.nextInt();
        
        System.out.println("Indtast mængden af produktet: ");
        int amount = keyboard.nextInt();
        
        try
        {
           System.out.println("Produktet koster i alt: " + oCtrl.addOli(amount, barcode, orderId));
        }
        catch(exceptions.ProductNotExistException e)
        {
            System.out.println(e.toString());
        }
        catch(exceptions.OrderNotExistException e)
        {
            System.out.println(e.toString());   
        }
    }
    
    public void addCustomer()
    {
        System.out.println("*** Tilføj kunde til ordre ***");
        System.out.print("Indtast ordrens nummer: ");
        int orderId = keyboard.nextInt();
        
        System.out.print("Indtast kundens telefonnummer: ");
        int phone = keyboard.nextInt();
        
        try
        {
           oCtrl.addCustomer(phone, orderId);
        }
        catch(exceptions.PersonNotExistException e)
        {
            System.out.println(e.toString());
        }
        catch(exceptions.OrderNotExistException e)
        {
            System.out.println(e.toString());
        }
    }
    
    public void finishOrder()
    {
        System.out.println("*** Find ordre ***");
        System.out.print("Indtast ordrens id: ");
        int orderId = keyboard.nextInt();
        
        try
        {
           boolean status = oCtrl.finishOrder(orderId);
            if(!status)
            {
                System.out.println("Du har ikke penge nok på kontoen!");
            }
            else
            {
                System.out.println("Ordren er nu betalt!");
            }
        }
        catch(exceptions.OrderNotExistException e)
        {
            System.out.println(e.toString());
        }
    }
}