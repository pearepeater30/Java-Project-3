package org.com1027.coursework.ng00367.q3;

import java.util.ArrayList;

public class BuyNowProduct extends Product{
	
	private double price = 0.0;
	private int quantity = 0;
	private ArrayList<Purchase>purchases = new ArrayList<Purchase>();

	public BuyNowProduct(int productId, String productName, double price, int quantity) throws IllegalArgumentException {
		super(productId, productName);
		// TODO Auto-generated constructor stub
		if (price <= 0) {
			throw  new IllegalArgumentException("price cannot be of value zero or less");
		}
		if (quantity <= 0) {
			throw new IllegalArgumentException("quantity cannot be of value zero or less");
		}
		this.price = price;
		this.quantity = quantity;
	}

	public boolean attemptToPurchase(User user, int quantity) throws IllegalArgumentException {
		boolean purchaseAttempt = false;
		int totalPurchase = 0;
		if (user == null) {
			throw new IllegalArgumentException("user cannot be of value null");
		}
		if (quantity <= 0) {
			throw new IllegalArgumentException("quantity");
		}
		for (Purchase purchase : this.purchases) {
			totalPurchase += purchase.getQuantityPurchased();
		}
		if ((this.quantity - totalPurchase) >= quantity) {
			this.purchases.add(new Purchase(user, quantity));
			purchaseAttempt = true;
		}
		return purchaseAttempt;
		
	}

	@Override
	public String displayHistory() {
		// TODO Auto-generated method stub
		StringBuffer stringbuffer = new StringBuffer();
		if (purchases.size() != 0) {
			stringbuffer.append(this.getProductId() + ": " + this.getProductName() + " quantity: " + this.quantity );
			stringbuffer.append("\n");
			stringbuffer.append("buy now history: ");
			stringbuffer.append("\n");
			for (Purchase purchase : purchases) {
				stringbuffer.append(purchase.toString());
				stringbuffer.append("\n");
			}
		}
		else {
			stringbuffer.append(this.getProductId() + ": " + this.getProductName() + " quantity: " + this.quantity );
			stringbuffer.append("\n");
			stringbuffer.append("no purchases");
		}
		
		return stringbuffer.toString();
	}

	@Override
	public String displayUserInfoForProduct() {
		// TODO Auto-generated method stub
		String UserInfo = "";
		if (purchases.size() != 0) {
			UserInfo = this.purchases.get(purchases.size()-1).toString();
		}
		return UserInfo;
	}

	@Override
	public double getCurrentPrice() {
		// TODO Auto-generated method stub
		return this.price;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public int howManyPurchases() {
		return this.purchases.size();
	}

	@Override
	public boolean isProductSold() {
		// TODO Auto-generated method stub
		boolean productSold = false;
		int totalPurchase = 0;
		for (Purchase purchase : this.purchases) {
			totalPurchase += purchase.getQuantityPurchased();
		}
		if (this.getQuantity() == totalPurchase) {
			productSold = true;
		}
		return productSold;
	}
	
	
	
}
