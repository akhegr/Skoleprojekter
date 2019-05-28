package exception;

public class CarePackageNotExistException extends Exception {
	private static final long serialVersionUID = 1L;
	private int id;
	
	public CarePackageNotExistException(int id) {
		this.id = id;
	}

	public String toString()
	{
		return "Der blev ikke fundet en plejepakke med nummeret " + id;
	}
}
