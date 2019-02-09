
package org.com1027.coursework.ng00367.q3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;
/**
 * Tests for the User class.
 * 
 * @author Stella Kazamia
 */
public class UserTest {
  

  
  
/**
  * Creating a User object with valid input parameter for the user. 
  * Test that the toString() can be retrieved correctly.
  * 
  * 
  */
 @Test
 public void testUserConstruction() {
   User user = new User("Helen");
   assertEquals("H***n", user.toString());
 }
 
 @Test
 public void testDisplayPurchasesAndDisplaySuccessfulbids() {
	 User user1 = new User("Bob");
	 user1.buy(1, 3);
	 user1.buy(2, 7);
	 user1.wonAuction(3, 5.00);
	 user1.wonAuction(4, 10.00);
	 
	 assertThat(user1.displayPurchases(), anyOf(is("1 with quantity 3\n2 with quantity 7\n"), is("2 with quantity 7\n1 with quantity 3\n")) );
	 assertThat(user1.displaySuccessfulBids(), anyOf(is("3 at a cost of 5.0\n4 at a cost of 10.0\n"), is("4 at a cost of 10.0\\n3 at a cost of 5.0\\n")) );
	 
	 
	 
 }
 
 /**
  * Creating a User object with invalid input parameter for the user. 
  * Test that the exception thrown.
  * 
  * 
  */
 @Test(expected=IllegalArgumentException.class)
 public void testUserInvalidConstructions() {
   User user = new User(null);
 }
}