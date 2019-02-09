package org.com1027.coursework.ng00367.q3;

import java.util.ArrayList;

public class BiddableProduct extends Product{
	
	private double reservedPrice;
	private ArrayList<Bid> bids = new ArrayList<Bid>();

	public BiddableProduct(int productId, String productName, double reservedPrice) {
		super(productId, productName);
		this.reservedPrice = reservedPrice;
		
		// TODO Auto-generated constructor stub
		
	}
	
	public boolean attemptToPurchase(User user, double bidValue) throws IllegalArgumentException {
		boolean purchaseSuccessful = false;
		if (user == null) {
			throw new IllegalArgumentException("User cannot be of value null");
		}
		if (bidValue <= 0.0) {
			throw new IllegalArgumentException("bidValue cannot be of value zero or less");
		}
		
		if(bids.size() == 0 || bidValue > bids.get(bids.size()-1).getBidValue()) {
			this.bids.add(new Bid(user, bidValue));
			purchaseSuccessful = true;
		}
		return purchaseSuccessful;
	}	

	@Override
	public String displayHistory() {
		// TODO Auto-generated method stub
		StringBuffer stringbuffer = new StringBuffer();
		if (bids.size() != 0) {
			stringbuffer.append(this.getProductId() + ": " + this.getProductName() + " = " );
			stringbuffer.append("\n");
			for (int i = bids.size()-1; i >=0; i--) {
				stringbuffer.append(bids.get(i).toString());
				stringbuffer.append("\n");
			}
		}
		else {
			stringbuffer.append(this.getProductId() + ": " + this.getProductName() + " = no bids");
		}
		
		return stringbuffer.toString();
	}

	@Override
	public String displayUserInfoForProduct() {
		// TODO Auto-generated method stub
		String UserInfo = "";
		if (bids.size() != 0) {
			UserInfo = this.bids.get(bids.size()-1).toString();
		}
		return UserInfo;
	}

	@Override
	public double getCurrentPrice() {
		double CurrentPrice = 0;
		if (bids.size() > 0) {
			return bids.get(bids.size()-1).getBidValue();
		}
		return CurrentPrice;
	}

	@Override
	public boolean isProductSold() {
		// TODO Auto-generated method stub
		boolean productSold = false;
		if (this.getCurrentPrice()>=this.reservedPrice) {
			productSold = true;
		}
		return productSold;
	}
	
}
