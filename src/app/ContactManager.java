package app;

import java.util.HashMap;
import interfaces.ContactInterface;

public class ContactManager implements ContactInterface {
	public static HashMap<String, Contact> hm = new HashMap<>();

	@Override
	public boolean addContact(Contact c) {

		if (c.firstName == null || c.lastName == null || c.phoneNo == null)
			return false;

		if (!c.firstName.matches("^[a-zA-Z][a-zA-Z]*$") || !c.lastName.matches("^[a-zA-Z][a-zA-Z]*$"))
			return false;

		if (!c.phoneNo.matches("\\d{10}"))
			return false;

		try {
			long num = Long.parseLong(c.phoneNo.trim());
		} catch (NumberFormatException e) {
			return false;
		}
		
		if(hm.containsKey(c.phoneNo))
			return false;

		hm.put(c.phoneNo, c);
		return true;
	}

	@Override
	public boolean editContact(String phoneNo, String firstName, String lastName) {
		if (hm.containsKey(phoneNo)) {
			Contact c = hm.get(phoneNo);
			c.firstName = firstName;
			c.lastName = lastName;
			return true;
		}
		return false;
	}

	public boolean viewContact(String phoneNo) throws InvalidContactNoException {
		if (hm.containsKey(phoneNo)) {
			Contact c = hm.get(phoneNo);
			System.out.println("Details : " + c.phoneNo + " " + c.firstName + " " + c.lastName);
			return true;
		}
		throw new InvalidContactNoException("Phone Number is not present");


	}

	@Override
	public boolean deleteContact(String phoneNo) {
		if (hm.containsKey(phoneNo)) {
			Contact c = hm.get(phoneNo);
			System.out.println("Deleted record: " + c.phoneNo + " " + c.firstName + " " + c.lastName);
			hm.remove(phoneNo);
			return true;
		}
		return false;

	}

}
