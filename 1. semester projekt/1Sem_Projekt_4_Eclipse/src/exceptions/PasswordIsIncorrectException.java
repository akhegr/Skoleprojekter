package exceptions;

public class PasswordIsIncorrectException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int personType;
    int phone;
    
    public PasswordIsIncorrectException(int phone)
    {
        this.phone = phone;
    }

    public int getPhone()
    {
        return phone;
    }
    
    public String toString()
    {
        return "Det indtastede kodeord stemmer ikke overens med telefonnummeret: " + getPhone() + ".";
    }

}