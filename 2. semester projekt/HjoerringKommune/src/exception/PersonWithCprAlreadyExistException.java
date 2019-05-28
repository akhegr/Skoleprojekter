package exception;

public class PersonWithCprAlreadyExistException extends Exception {
	private static final long serialVersionUID = 1L;
	private String cprNo;
	
	public PersonWithCprAlreadyExistException(String cprNo) {
		this.cprNo = cprNo;
	}

	public String toString()
	{
		return "Der er allerede oprettet en plejekrævende med cpr nummeret: " + cprNo;
	}	
}
