package exception;

public class PersonNotExistException extends Exception {
	private static final long serialVersionUID = 1L;
	private String cprNo;
	
	public PersonNotExistException(String cprNo) {
		this.cprNo = cprNo;
	}

	public String toString()
	{
		return "Der blev ikke fundet en person i databasen med cpr nr. " + cprNo;
	}	
}
