package se.jonananas.teaching.codeSmells;

import org.junit.Ignore;
import org.junit.Test;

public class General {

	private String querySingleResult(String sql) {
		return null;
	}
	
	class Person {

		public boolean isTall() {
			return false;
		}
		
	}
	
	/*
	 * G1
	 * Multiple languages in one source file
	 * @see address_book_process.php
	 */
	
	public String numberOfOrdersAsHtml() {
		String sql = "SELECT count(*) from Orders o";
		String numberOfOrders = querySingleResult(sql);
		String html = "<script>alert('This is a very important alert');</script>";
		html+= "<strong>Number of orders: " + numberOfOrders + "</strong>";
		return html;
	}
	
	
	/*
	 * G3
	 * Incorrect behavior at boundaries
	 * Don't be lazy! Write test for it!
	 */
	
	
	/*
	 * G4
	 * Overridden safeties
	 * - Disabled/Ignored compiler warnings
	 * "Chernobyl melted down because the plant manager overrode each of the safety mechanisms"
	 */
	@Test
	@Ignore
	public void shouldReturnEmptyStringOnUnknownEntity() {
		
	}
	
	/*
	 * G5
	 * Duplication
	 */
	
	/*
	 * G7 Base Classes Depending on their derivatives
	 */
	
	/*
	 * G29
	 * Avoid negative conditionals
	 */
	public String makeHeightIndication(Person person) {
		if (!person.isTall()) {
			return "Person is short";
		} else {
			return "Person is tall";
		}
	}
	
}
