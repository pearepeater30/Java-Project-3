package org.com1027.coursework.ng00367.q3;

/**
 * Bid class which contains a constructor, a method to get the biValue variable, a method to get the buyer, and a toString() Method.
 * 
 * @author Nathan Gu
 */

public class Bid {
	/** A User variable that stores the User. */
	private User buyer = null;
	/** An Double that stores the bidValue. */
	private double bidValue = 0.0;
	
	/**
     * Parameterized Constructor to create a new Bid. 
     * 
     * @param The Id of the product
     * 
     * @param The name of the product
     * 
     * @param The reserved price of the product

     */
	
	public Bid(User buyer, double bidValue) {
		super();
		/** Checks are made to ensure the buyer is not null and the bidValue is not 0.0 or less. An IllegalArgumentException is thrown if thats 
		 * the case.*/
		if (buyer == null) {
			throw new IllegalArgumentException("Buyer cannot be null");
		}
		if (bidValue <= 0.0) {
			throw new IllegalArgumentException("bidValue cannot be less than or equal to zero");
		}
		else {			
			this.buyer = buyer;
			this.bidValue = bidValue;
		}
	}
	
	/**
     * @return the value of a bid.
     */
	public double getBidValue() {
		return this.bidValue;
	}
	
	/**
     * @return the buyer.
     */
	public User getBuyer() {
		return this.buyer;
	}
	
	/**
     * toString method used to display the bid value and the buyer..
     */
	@Override
	public String toString() {
		return this.buyer.toString() +  " bid " + "£" + this.bidValue;
	}

}
