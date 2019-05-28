package exception;

public class SexNotExistException extends Exception {
	private static final long serialVersionUID = 1L;
	private String sex;
	
	public SexNotExistException(String sex) {
		this.sex = sex;
	}

	public String toString()
	{
		return "Det indtastede k�n " + sex + " er ikke gyldigt.\n De gyldige k�n er M og K.";
	}
}
