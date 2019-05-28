package exception;

public class PersonCanNotBeCreatedException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public PersonCanNotBeCreatedException() {
		
	}

	public String toString()
	{
		return "Der kunne ikke oprettes en person i systemet, da oplysningerne ikke opfylder kriterierne.";
	}
}
