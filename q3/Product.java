package org.com1027.coursework.ng00367.q3;

public abstract class Product {
	private int productId = 0;
	private String productName = null;
	public Product(int productId, String productName) {
		super();
		if (productId == 0) {
			throw new IllegalArgumentException("productId cannot be 0");
		}
		if (productName == null) {
			throw new IllegalArgumentException("productName cannot be null");
		}
		this.productId = productId;
		this.productName = productName;
	}
	
	public abstract String displayHistory();
	
	public abstract String displayUserInfoForProduct();
	
	public abstract double getCurrentPrice();
	
	public abstract boolean isProductSold();
	
	public int getProductId() {
		return this.productId;
	}
	
	public String getProductName() {
		return this.productName;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + "]";
	}
}
