package tui;

import controller.*;
import java.util.Scanner;

public class SubMenuEmployee
{
    //Instance variables
    private EmployeeCtrl eCtrl;
    private Scanner keyboard;
    
    public SubMenuEmployee()
    {
        // initialise instance variables
        keyboard = new Scanner(System.in);
        eCtrl = new EmployeeCtrl();
    }
    
    public void start()
    {
        boolean finish = false;
        while(!finish)
        {
            System.out.println("*** Medarbejder menu ***");
            System.out.println("1. Opret medarbejder");
            System.out.println("2. Find medarbejder");
            System.out.println("3. Opdater medarbejder");
            System.out.println("4. Tilbage til hovedmenu\n");
            System.out.println("Indtast et tal mellem 1 og 5");
            
            keyboard.reset();
            String input = keyboard.nextLine();
            if(input.equals("1"))
            {
                addEmployee();
            }
            else if(input.equals("2"))
            {
                findEmployee();
            }
            else if(input.equals("3"))
            {
                updateEmployee();
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
    
    public void addEmployee()
    {
        keyboard.reset();
        System.out.println("*** Tilføj medarbejder ***");
        System.out.print("Indtast medarbejderens navn: ");
        String name = keyboard.nextLine();
        
        System.out.print("Indtast medarbejderens adresse: ");
        String address = keyboard.nextLine();
        
        System.out.print("Indtast medarbejderens email: ");
        String email = keyboard.nextLine();
        
        System.out.println("Indtast medarbejderens telefonnummer: ");
        int phone = keyboard.nextInt();
        
        keyboard.reset(); //nulstiller keyboard
        System.out.print("Indtast medarbejderens rangnr.: ");
        String position = keyboard.nextLine();
        
        eCtrl.createEmployee(address, email, name, phone, position);
    }
    
    public void findEmployee()
    {
        keyboard.reset();
        System.out.println("*** Find medarbejder ***");
        System.out.println("Indtast medarbejderens telefonnummer: ");
        int phone = keyboard.nextInt();
        
        try
        {
           System.out.println("Medarbejderens navn er: " + eCtrl.findName(phone));
           System.out.println("Medarbejderens adresse er: " + eCtrl.findAddress(phone));
           System.out.println("Medarbejderens email er: " + eCtrl.findEmail(phone));
           System.out.println("Medarbejderens rangnr. er: " + eCtrl.findPosition(phone) + "\n");
        }
        catch(exceptions.PersonNotExistException e)
        {
            System.out.println(e.toString());
        }
    }
    
    public void updateEmployee()
    {
        keyboard.reset();
        System.out.println("*** Opdater medarbejder ***");
        System.out.println("Indtast medarbejderens gamle telefonnummer: ");
        int oldPhone = keyboard.nextInt();
        keyboard.reset(); //nulstiller keyboard
        
        System.out.print("Indtast medarbejderens navn: ");
        String name = keyboard.nextLine();
        
        System.out.print("Indtast medarbejderens adresse: ");
        String address = keyboard.nextLine();
        
        System.out.print("Indtast medarbejderens email: ");
        String email = keyboard.nextLine();
        
        //System.out.println("Indtast medarbejderens telefonnummer: ");
        //int phone = keyboard.nextInt();
        int phone = oldPhone;
        
        System.out.print("Indtast medarbejderens rangnr.: ");
        String position = keyboard.nextLine();
        
        try
        {
            eCtrl.updateEmployee(oldPhone, address, email, name, phone, position);
        }
        catch(exceptions.PersonNotExistException e)
        {
            System.out.println(e.toString());
        }
    }
}
