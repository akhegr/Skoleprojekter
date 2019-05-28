package exception;

import model.Dependent;

public class DependentNotSetException extends Exception {
	private static final long serialVersionUID = 1L;
	private Dependent dependent;
	
	public DependentNotSetException(Dependent dependent) {
		this.dependent = dependent;
	}


	@Override
	public String toString() {
		return "Den plejekrævende: " + dependent.getfName() + " " 
				+ dependent.getlName() + " blev ikke tilføjet til besøget."
				+" Prøv igen med en anden dato, tid eller plejekrævende";
	}
	
}
