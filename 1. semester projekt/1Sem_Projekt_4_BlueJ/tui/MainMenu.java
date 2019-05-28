package tui;

import java.util.Scanner;

public class MainMenu
{
    //Instance variables
    private SubMenuEmployee employeeSubMenu;
    private SubMenuCustomer customerSubMenu;
    private SubMenuProduct productSubMenu;
    private SubMenuOrder orderSubMenu;
    private Scanner keyboard;
    
    public MainMenu()
    {
        // initialise instance variables
        keyboard = new Scanner(System.in);
        employeeSubMenu = new SubMenuEmployee();
        customerSubMenu = new SubMenuCustomer();
        productSubMenu = new SubMenuProduct();
        orderSubMenu = new SubMenuOrder();
    }
    
    public void start() throws Exception
    {
        boolean finish = false;
        while(!finish)
        {
            System.out.println("*** Hovedmenu ***");
            System.out.println("1. Medarbejdere");
            System.out.println("2. Kunde");
            System.out.println("3. Produkt");
            System.out.println("4. Ordre");
            System.out.println("5. Luk program\n");
            System.out.println("Indtast et tal mellem 1 og 5");
            
            keyboard.reset();
            String input = keyboard.nextLine();
            if(input.equals("1"))
            {
                employeeSubMenu.start();
            }

            else if(input.equals("2"))
            {
                customerSubMenu.start();
            }
            else if(input.equals("3"))
            {
                productSubMenu.start();
            }
            else if(input.equals("4"))
            {
                orderSubMenu.start();
            }
            else if (input.equals("5"))
            {
                System.out.println("På gensyn");
                finish = true;
            }
            else
            {
                System.out.println("Prøv igen...");
            }
        }
    }
}
