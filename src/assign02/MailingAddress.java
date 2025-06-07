package assign02;

/**
 * This class represents a U.S. mailing address.
 * 
 * @author CS 2420 course staff
 * @version 2025-05-15
 */
public class MailingAddress {
	
	private String street;
	private String city;
	private String state;
	private int zipCode;
	
	/**
	 * Creates a mailing address, given the street, city, state, and zip code.
	 * 
	 * @param street
	 * @param city
	 * @param state
	 * @param zipCode
	 */
	public MailingAddress(String street, String city, String state, int zipCode) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}
	
	/**
	 * Two mailing addresses are considered equal if they have the same street, city, state, and zip code.
	 * 
	 * @param other - the object being compared with this mailing address
	 * @return true if the other object is a MailingAddress type and is equal to this mailing address, 
	 *         false otherwise
	 */
	public boolean equals(Object other) {
		if(!(other instanceof MailingAddress)) {
			return false;
		}
		
		MailingAddress otherAddress = (MailingAddress) other;

		return (
			this.street.equals(otherAddress.street) &&
			this.city.equals(otherAddress.city) &&
			this.state.equals(otherAddress.state) &&
			this.zipCode == otherAddress.zipCode
		);
	}
	
	/**
	 * Returns a textual representation of this mailing address.
	 */
	public String toString() {
		return this.street + "\n" + this.city + ", " + this.state + "  " + this.zipCode;
	}
}