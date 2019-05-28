package exception;

public class CprNoIsNotCorrectException extends Exception {
	private static final long serialVersionUID = 1L;
	private String cprNo;
	
	public CprNoIsNotCorrectException(String cprNo) {
		this.cprNo = cprNo;
	}

	public String toString()
	{
		return "Det indtastede cpr nr. " + cprNo + " er ikke gyldigt.";
	}	
}
