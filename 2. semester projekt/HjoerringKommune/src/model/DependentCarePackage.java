package model;

public class DependentCarePackage {
	private int dependentId;
	private int carePackageId;
	
	public DependentCarePackage(int dependentId, int carePackageId) {
		setDependentId(dependentId);
		setCarePackageId(carePackageId);
	}

	public int getDependentId() {
		return dependentId;
	}

	public void setDependentId(int dependentId) {
		this.dependentId = dependentId;
	}

	public int getCarePackageId() {
		return carePackageId;
	}

	public void setCarePackageId(int carePackageId) {
		this.carePackageId = carePackageId;
	}
	
}
