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
		return "Den plejekr�vende: " + dependent.getfName() + " " 
				+ dependent.getlName() + " blev ikke tilf�jet til bes�get."
				+" Pr�v igen med en anden dato, tid eller plejekr�vende";
	}
	
}
