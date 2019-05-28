package exception;

public class TransactionFailedException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public TransactionFailedException() {
	}

	public String toString()
	{
		return "Den opstod en fejl i systemet under oprettelsen.";
	}	
}
