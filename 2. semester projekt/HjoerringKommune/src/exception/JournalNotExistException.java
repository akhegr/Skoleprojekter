package exception;

public class JournalNotExistException extends Exception {
	private static final long serialVersionUID = 1L;
	private String cprNo;
	
	public JournalNotExistException(String cprNo) {
		this.cprNo = cprNo;
	}

	public String toString()
	{
		return "Der blev ikke fundet en person med cpr nr. " + cprNo + " i databasen.";
	}
}
