package interfaces;

import app.Contact;
import app.InvalidContactNoException;

public interface ContactInterface {

	boolean addContact(Contact c) throws InvalidContactNoException;
	boolean deleteContact(String phoneNo);
	boolean editContact(String phoneNo, String firstName, String lastName);
	boolean viewContact(String phoneNo) throws InvalidContactNoException;


}
