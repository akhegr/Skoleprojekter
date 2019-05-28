package exception;

public class PersonWithCprNoNotExistException extends Exception {
	private static final long serialVersionUID = 1L;
	private String cprNo;
	
	public PersonWithCprNoNotExistException(String cprNo) {
		this.cprNo = cprNo;
	}

	public String toString()
	{
		return "Der findes ikke en journal for personen med cpr nummeret: " + cprNo;
	}	
}
