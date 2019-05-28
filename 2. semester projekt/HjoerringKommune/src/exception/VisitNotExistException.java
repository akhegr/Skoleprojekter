package exception;

public class VisitNotExistException extends Exception {
	private static final long serialVersionUID = 1L;

	public VisitNotExistException() {

	}

	public String toString()
	{
		return "Der blev ikke fundet et besøg i databasen.";
	}
}
