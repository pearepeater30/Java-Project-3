package org.com1027.coursework.ng00367.q3;

import java.util.HashMap;

/**
 * User class which contains a constructor, and a toString() method.
 * @author Nathan Gu
 */

public class User {
	/** An String that is used to store the name of the User. */
	private String name = null;
	private HashMap<Integer, Integer> purchases = new HashMap<Integer, Integer>();
	private HashMap<Integer, Double> successfulBids = new HashMap<Integer, Double>();
	
	/**
     * Parameterized Constructor to create a new User. 
     * 
     * @param The name of the user.
     */
	public User (String name) {
		super();
		/** Checks are made to ensure the name is not null. An IllegalArgumentException is thrown if thats the case.*/
		if (name == null) {
			throw new IllegalArgumentException("name cannot be null");
		}
		else {
			this.name = name;
		}
	}
	/**
     * Method to place bouhgt items into the purchases hashmap. 
     * 
     * @param ID of product.
     * 
     * @param quantity of product.
     */
	public void buy (int productID, int quantity) {
		if (productID == 0) {
			throw new IllegalArgumentException("productID cannot be of value 0");
		}
		if (quantity <= 0) {
			throw new IllegalArgumentException("quantity cannot be of value 0 or less");
		}
		else {
			this.purchases.put(productID, quantity);
		}
	}
	
	public String displayAllPurchases() {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("All Purchased Products: ");
		stringbuffer.append("\n");
		stringbuffer.append("Purchases: ");
		stringbuffer.append("\n");
		stringbuffer.append(this.displayPurchases());
		stringbuffer.append("Successful Bids: ");
		stringbuffer.append("\n");
		stringbuffer.append(this.displaySuccessfulBids());
		return stringbuffer.toString();
	}
	/**
     * toString method used to display the name of the user..
     */
	
	public String displayPurchases() {
		StringBuffer stringbuffer = new StringBuffer();
		for (int i : this.purchases.keySet()) {
			if (this.purchases.size()>0) {
				stringbuffer.append(i +" with quantity " + this.purchases.get(i));
				stringbuffer.append("\n");
			}
			else {
				stringbuffer.append("No items purchased");
			}
		}
		
		return stringbuffer.toString();
	}
	
	public String displaySuccessfulBids() {
		StringBuffer stringbuffer = new StringBuffer();
		for (int j : this.successfulBids.keySet()) {
			if (this.successfulBids.size()>0) {
				stringbuffer.append(j +" at a cost of " + this.successfulBids.get(j));
				stringbuffer.append("\n");
			}
			else {
				stringbuffer.append("no successful bids were found");
			}
		}
		return stringbuffer.toString();
	}

	@Override
	public String toString() {
		String buyer = this.name.toString();
		char first_letter = buyer.charAt(0);
		char last_letter = buyer.charAt(buyer.length()-1);
		return first_letter + "***" + last_letter;
	}
	/**
     * Method to place items won in an auction. 
     * 
     * @param ID of product.
     * 
     * @param the winning price of the auction.
     */
	public void wonAuction(int productID, double winningPrice) {
		if (productID == 0) {
			throw new IllegalArgumentException("productID cannot be of value 0");
		}
		if (winningPrice <= 0.0) {
			throw new IllegalArgumentException("winningPrice cannot be of value 0 or less");
		}
		else {
			this.successfulBids.put(productID, winningPrice);
		}
	}
	


}
