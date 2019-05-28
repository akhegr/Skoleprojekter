package tui;

import controller.*;
import java.util.Scanner;
import exceptions.*;

public class SubMenuCustomer
{
    //Instance variables
    private CustomerCtrl cCtrl;
    private Scanner keyboard;
    
    public SubMenuCustomer()
    {
        // initialise instance variables
        keyboard = new Scanner(System.in);
        cCtrl = new CustomerCtrl();
    }
    
    public void start()
    {
        boolean finish = false;
        while(!finish)
        {
            System.out.println("*** Kundemenu ***");
            System.out.println("1. Opret kunde");
            System.out.println("2. Find kunde");
            System.out.println("3. Opdater kunde");
            System.out.println("4. Tilbage til hovedmenu\n");
            System.out.println("Indtast et tal mellem 1 og 5");
            
            keyboard.reset();
            String input = keyboard.nextLine();
            if(input.equals("1"))
            {
                addCustomer();
            }
            else if(input.equals("2"))
            {
                findCustomer();
            }
            else if(input.equals("3"))
            {
                updateCustomer();
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
    
    public void addCustomer()
    {
        System.out.println("*** Tilføj kunde ***");
        System.out.print("Indtast kundens navn: ");
        String name = keyboard.nextLine();
        
        System.out.print("Indtast kundens adresse: ");
        String address = keyboard.nextLine();
        
        System.out.print("Indtast kundens email: ");
        String email = keyboard.nextLine();
        
        System.out.println("Indtast kundens telefonnummer: ");
        int phone = keyboard.nextInt();
        
        System.out.println("Indtast kundens beløb på kontoen: ");
        double account = keyboard.nextDouble();
        
        System.out.println("Indtast kundens rabatprocent: ");
        double discount = keyboard.nextDouble();
        
        System.out.println("Indtast kundens lån: ");
        double loan = keyboard.nextDouble();
        
        System.out.println("Indtast kundens kontotype: ");
        int type = keyboard.nextInt();
        
        cCtrl.createCustomer(address, email, name, phone, account, discount, loan, type);
    }
    
    public void findCustomer()
    {
        System.out.println("*** Find kunden ***");
        System.out.println("Indtast kundens telefonnummer: ");
        int phone = keyboard.nextInt();
        
        try
        {
           System.out.println("Kundens navn er: " + cCtrl.findName(phone));
           System.out.println("Kundens adresse er: " + cCtrl.findAddress(phone));
           System.out.println("Kundens email er: " + cCtrl.findEmail(phone));
           System.out.println("Kundens beløb på kontoen er: " + cCtrl.findAccount(phone));
           System.out.println("Kundens rabatprocent på kontoen er: " + cCtrl.findDiscount(phone));
           System.out.println("Kundens lån er: " + cCtrl.findLoan(phone));
           System.out.println("Kundens kontotype er: " + cCtrl.findType(phone));
        }
        catch(exceptions.PersonNotExistException e)
        {
            System.out.println(e.toString());
        }
    }
    
    public void updateCustomer()
    {
        System.out.println("*** Opdater kunde ***");
        System.out.println("Indtast kundens gamle telefonnummer: ");
        int oldPhone = keyboard.nextInt();
        
        System.out.print("Indtast kundens navn: ");
        String name = keyboard.nextLine();
        
        System.out.print("Indtast kundens adresse: ");
        String address = keyboard.nextLine();
        
        System.out.print("Indtast kundens email: ");
        String email = keyboard.nextLine();
        
        System.out.println("Indtast kundens telefonnummer: ");
        int phone = keyboard.nextInt();
        
        System.out.println("Indtast kundens beløb på kontoen: ");
        double account = keyboard.nextDouble();
        
        System.out.println("Indtast kundens rabatprocent: ");
        double discount = keyboard.nextDouble();
        
        System.out.println("Indtast kundens lån: ");
        double loan = keyboard.nextDouble();
        
        System.out.println("Indtast kundens kontotype: ");
        int type = keyboard.nextInt();
        
        try
        {
            cCtrl.updateCustomer(oldPhone, address, email, name,
                        phone, account, discount, loan, type);
        }
        catch(exceptions.PersonNotExistException e)
        {
            System.out.println(e.toString());
        }
    }
}
