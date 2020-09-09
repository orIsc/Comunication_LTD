package com.hit.dm;

public class SurfingPackage {
	
	private String spName;
	private String supplier;
	private String infrastructure;
	private int price;
	
	public SurfingPackage(String supplier, String infrastructure, int price, String spName) {
		super();
		this.spName = spName;
		this.supplier = supplier;
		this.infrastructure = infrastructure;
		this.price = price;
	}
	
	public String getSpName() {
		return spName;
	}

	public void setSpName(String spId) {
		this.spName = spName;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getInfrastructure() {
		return infrastructure;
	}

	public void setInfrastructure(String infrastructure) {
		this.infrastructure = infrastructure;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "SurfingPackage [spName=" + spName + ", supplier=" + supplier + ", infrastructure=" + infrastructure
				+ ", price=" + price + "]";
	}
	
	
}
