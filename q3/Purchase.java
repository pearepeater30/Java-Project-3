package org.com1027.coursework.ng00367.q3;

public class Purchase {
	private User buyer = null;
	private int quantityPurchased = 0;
	
	public Purchase (User buyer, int quantityPurchased) {
		super();
		this.buyer = buyer;
		this.quantityPurchased = quantityPurchased;
	}
	
	public User getBuyer() {
		return this.buyer;
	}
	
	public int getQuantityPurchased (){
		return this.quantityPurchased;
	}

	@Override
	public String toString() {
		return buyer.toString() + " bought " + this.quantityPurchased;
	}
	
	
}