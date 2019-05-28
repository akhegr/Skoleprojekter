package exception;

public class CreateVisitDialogClosedException extends Exception {
	private static final long serialVersionUID = 1L;

	public CreateVisitDialogClosedException() {
	}
	
	public String toString() {
		return "Besøget er ikke oprettet, da der ikke blev valgt en plejekrævende.";
	}
}
