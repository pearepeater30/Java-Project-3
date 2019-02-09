package org.com1027.coursework.ng00367.q3;

import java.util.HashMap;

/**
 * AuctionHouse class that contains a constructor, a method to check the existence of a product, a method to display all products that are sold, a method to display all
 * unsold products, a method to end an auction for a product, a method to place bids for a product and a method to register a product to all products for 
 * sale.
 * 
 * @author Nathan Gu
 */
public class AuctionHouse {
	/** A hashmap which uses the product as a key and the user of a product for products on sale */
	private HashMap<Product, User> forSaleProducts = new HashMap<Product, User>();
	/** A hashmap which uses the product as a key and the user of a product for products that have been sold */
	private HashMap<Product, User> soldProducts = new HashMap<Product, User>();
	/** A hashmap which uses the product as a key and the user of a product for products that have not met their reserve price */
	private HashMap<Product, User> unsoldProducts = new HashMap<Product, User>();
	
	/**
     * Method used to buy a BuyNowProduct
     * 
     * @param BuyNowProduct that is desired to be bought
     * 
     * @param User who wants to buy the product
     * 
     * @param quantity of products want to buy
     * @return Returns whether the product has been found (True/False)
     */
	
	public boolean buyNow(BuyNowProduct product, User buyer, int quantity) throws IllegalArgumentException {
		boolean ableToBuy = false;
		if (product == null) {
			throw new IllegalArgumentException("The Product cannot be of value null");
		}
		if (buyer == null) {
			throw new IllegalArgumentException("The User cannot be of value null");
		}
		/** A check is made to see if the product already exists in either forSaleProducts, soldProducts or unsoldProducts.*/
		if (quantity <= 0.0) {
			throw new IllegalArgumentException("The quantity cannot be less than or equal to zero");
		}
		/** * A check is then made sure so that product actually exists in forSaleProducts and if that is true, then the product is placed in the soldProducts
		 *  hashmap and also placed in the buyer's buy purchases hashmap*/
		if (forSaleProducts.containsKey(product)) {
			ableToBuy = product.attemptToPurchase(buyer, quantity);
			buyer.buy(product.getProductId(), quantity);
		}
		/** * Another check is then made sure so that product actually exists in forSaleProducts and if that is false, then the product is placed in the 
		 * unsoldProducts hashmap*/
	
		return ableToBuy;
		
	}
	
	/**
     * Checks the existence of a product as a parameter through all the hashmaps
     * 
     * @param The desired Product to be found
     *
     * @return Returns whether the product has been found (True/False)
     */
	public boolean checkExistence (Product product) {
		/** This method checks to see if the product  (key) is found in each HashMap. If the product is found in any of the HashMaps, then return True.
		 * If not found at all, return false. */
		return forSaleProducts.containsKey(product) || soldProducts.containsKey(product) || unsoldProducts.containsKey(product);
	}
	
	/**
     * Method used to gather all the products that have been sold and stores it in a StringBuffer. It loops through each product in the soldProducts
     * HashMap and stores the product ID and the Highest Bidder and the price they auctioned using the toString() method.
     * 
     * 
     *
     * @return The stringBuffer is returned
     */
	
	public String displaySoldProducts() {
		StringBuffer stringBuffer = new StringBuffer();
		for (Product product : this.soldProducts.keySet()) {
			stringBuffer.append(product.getProductId());
			stringBuffer.append(" - ");
			stringBuffer.append(product.displayUserInfoForProduct());
			stringBuffer.append("\n");
		}
		return stringBuffer.toString();
		
	}
	
	/**
     * Method used to gather all the products that have been not been sold and stores it in a StringBuffer. It loops through each product in the 
     * unsoldProductsHashMap and stores the product ID and the name of the product.
     * 
     * The data is stored in the format: "productId - productName"
     * 
     * @return The stringBuffer is returned
     */
	
	public String displayUnsoldProducts() {
		StringBuffer stringBuffer = new StringBuffer();
		for (Product product : this.unsoldProducts.keySet()) {
			stringBuffer.append(product.getProductId());
			stringBuffer.append(" - ");
			stringBuffer.append(product.getProductName());
			stringBuffer.append("\n");
		}
		return stringBuffer.toString();
		
	}
	
	/**
     * Method used to end an auction (i.e by moving a product from the forSaleProducts to either the soldProducts HashMap or unsoldProducts Hashmap).
     * 
     * @param the product that you want to end the auction of.
     */
	
	public void endAuction(Product product) throws IllegalArgumentException {
		/** Checks are made to ensure that the product parameter is not that of a value null and that the product actually exists in the forsaleProducts 
		 * hashmap. If either are false, then an IllegalArgumentException is thrown.*/
		if (product == null) {
			throw new IllegalArgumentException("The Product cannot be of value null");
		}
		if (!this.forSaleProducts.containsKey(product)) {
			throw new IllegalArgumentException("This product does not exist");
		}
		/** The buyer of the product is saved in a variable by using the product as a key and looking up the user using said key in the forSaleProducts.*/
		User buyer = this.forSaleProducts.get(product);
		/**  A check is made to see if product is a BuyNowProduct type. If it is, The product is placed in the soldProducts hashmap. 
		 * Then the product is cast as a BuyNowProduct. The a BuyNowProduct object is created which then allows us to place the remaining unpurchased
		 * products into the unSoldProduct hashmap.*/
		if(product instanceof BuyNowProduct) {
			this.soldProducts.put(product, buyer);
			BuyNowProduct buyNowProduct = (BuyNowProduct) product;
			BuyNowProduct unSoldProduct = new BuyNowProduct(product.getProductId(), product.getProductName(), product.getCurrentPrice(), 
			buyNowProduct.getQuantity() - buyNowProduct.howManyPurchases());
			this.unsoldProducts.put(unSoldProduct, unsoldProducts.get(product));
		}
		
		/** If it is not a BuyNowProduct, a check is then made by to see if the product does not have any bids or if the highest bid for a product does 
		 * not match the reserved price. If either of these checks return true, then the products are placed in the unsoldProducts HashMap. */
		else {
			if(product.isProductSold()) {
				soldProducts.put(product, buyer);
				forSaleProducts.get(product).wonAuction(product.getProductId(), product.getCurrentPrice());
			}
		
			if (product.getCurrentPrice() == 0 || !product.isProductSold()) {			
				this.unsoldProducts.put(product, buyer);
			}								
			
		}
		/** The product is removed from the forSaleProducts hashmap */
		forSaleProducts.remove(product);
		
	}
	
	/**
     * Method used to place bid. 
     * 
     * @param The product user wants to place the bid for.
     * 
     * @param The user who places the bid.
     * 
     * @param The value of the bid user wants to place.
     * 
     * @return The bidPlaced variable is returned, which determines whether the bid was placed or not.
     */
	
	public boolean placeBid (BiddableProduct product, User user, double bidValue) throws IllegalArgumentException {
		/** The variable bidPlaced is created which initially determines that the bid being placed as false.*/
		boolean bidPlaced = false;
		/** Checks are made so that the parameters product and user are not null and that bidvalue is not of value 0.0 or less. Otherwise an 
		 * IllegalAargumentException is thrown.*/
		if (product == null) {
			throw new IllegalArgumentException("The Product cannot be of value null");
		}
		if (user == null) {
			throw new IllegalArgumentException("The User cannot be of value null");
		}
		if (bidValue <= 0.0) {
			throw new IllegalArgumentException("The bidValue cannot be less than or equal to zero");
		}
		/** * A check is then made sure so that product actually exists in forSaleProducts and if that is true*/
		if (forSaleProducts.containsKey(product)) {
			bidPlaced = product.attemptToPurchase(user, bidValue);;
		}
		return bidPlaced;
	}
	
	/**
     * Method used to register a product into the forSaleProducts HashMap.
     * 
     * @return The registerPlaced variable is returned, which determines whether the register was successful or not.
     * 
     * @param The product user wants to register
     * 
     * @param The user who wants to register
     */
	
	public boolean register(Product product, User user) throws IllegalArgumentException {
		/** The variable registerPlaced is created which initially determines that the product is registered as false. */
		boolean registerPlaced = false;
		/**Checks are made so that the parameters product and user are not null otherwise an IllegalArgumentException is thrown.*/
		if (product == null) {
			throw new IllegalArgumentException("The Product cannot be of value null");
		}
		if (user == null) {
			throw new IllegalArgumentException("The User cannot be of value null");
		}
		/** A check is made to see if the product already exists in either forSaleProducts, soldProducts or unsoldProducts.*/
		if (!this.checkExistence(product)) {
			/**If not then the product and the user are registered placed into the forSaleProducts HashMap and the registerPlace variable is set True.*/
			forSaleProducts.put(product, user);
			registerPlaced = true;
		}
		return registerPlaced;
		
	}
}
