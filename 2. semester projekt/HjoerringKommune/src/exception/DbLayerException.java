package exception;

public class DbLayerException extends Exception {
	private static final long serialVersionUID = 1L;
	private Exception e;
	private String url;
	
	public DbLayerException(Exception e, String url) {
		this.url = url;
		this.e = e;
	}

	public DbLayerException(Exception e) {
		this.e = e;
	}
	
	public String toString()
	{
		return "Der opstod et problem ved forbindelsen til databasen:\n"
				+ e.getMessage() + " " + url;
	}
}
