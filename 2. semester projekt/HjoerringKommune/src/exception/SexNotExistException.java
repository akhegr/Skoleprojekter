package exception;

public class SexNotExistException extends Exception {
	private static final long serialVersionUID = 1L;
	private String sex;
	
	public SexNotExistException(String sex) {
		this.sex = sex;
	}

	public String toString()
	{
		return "Det indtastede køn " + sex + " er ikke gyldigt.\n De gyldige køn er M og K.";
	}
}
