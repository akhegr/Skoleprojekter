package exceptions;

public class PersonNotExistException extends Exception
{
    int personType;
    int phone;
    
    public PersonNotExistException(int personType, int phone)
    {
        this.personType = personType;
        this.phone = phone;
    }

    public int getPersonType()
    {
        return personType;
    }

    public int getPhone()
    {
        return phone;
    }
    
    public String toString()
    {
        String returnMessage;
        if(personType == 0)
        {
            returnMessage = "Medarbejderen med telefonnummeret " + getPhone() + " eksisterer ikke";
        }
        else if(personType == 1)
        {
            returnMessage = "Kunden med telefonnummeret " + getPhone() + " eksisterer ikke";
        }
        else
        {
            returnMessage = "Fejltypen er ukendt!";
        }
        
        return returnMessage;
    }

}
