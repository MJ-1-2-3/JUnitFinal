package test;

import app.InvalidContactNoException;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.Contact;
import app.ContactManager;

class TestContactManager {

	ContactManager manage = new ContactManager();
	Contact c = new Contact("Mereena", "Joseph", "1234567890");

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {

	}

	@BeforeEach
	void setUp() throws Exception {
		c.firstName = "Mereena";
		c.lastName = "Joseph";
		c.phoneNo = "1234567890";

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAddContactValid() {
		Contact c = new Contact("Lily", "Mary", "1234567890");
		assertTrue(manage.addContact(c));

	}

	@Test
	void testAddContactFirstNull() {
		c.firstName = null;
		assertFalse(manage.addContact(c));

	}

	@Test
	void testAddContactLastNull() {
		c.lastName = null;
		assertFalse(manage.addContact(c));

	}

	@Test
	void testAddContactPhoneNull() {
		c.phoneNo = null;
		assertFalse(manage.addContact(c));

	}

	@Test
	void testAddContactInvalidFirst() {
		c.firstName = "Mereena12";
		assertFalse(manage.addContact(c));

	}

	@Test
	void testAddContactInvalidLast() {
		c.lastName = "2Joseph";
		assertFalse(manage.addContact(c));

	}

	@Test
	void testAddContactDuplicate() {
		Contact c1 = new Contact("Ammu","Joseph", "0000111129");
		manage.addContact(c1);
		Contact c2 = new Contact("Ann", "Saji", "0000111129");
		assertFalse(manage.addContact(c2));

	}

	@Test
	void testViewContactAdded() {
		try {
			Contact c = new Contact("Lincy", "Joseph", "1111567880");
			manage.addContact(c);
			assertTrue(manage.viewContact("1111567880"));
		} catch (InvalidContactNoException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testViewContactNotAdded() {
		assertThrows(InvalidContactNoException.class, () -> manage.viewContact("1100110010"),
				"InvalidContactException is thrown");
	}

	@Test
	void testEditContactAdded() {
		Contact c = new Contact("Tony", "Joseph", "8888444400");
		manage.addContact(c);
		assertTrue(manage.editContact(c.phoneNo, "NewFirst", "NewLast"));
	}

	@Test
	void testEditContactNotAdded() {
		assertFalse(manage.editContact("1100110010", "NewFirst", "NewLast"));
	}

	@Test
	void testDeleteContactAdded() {
		Contact c = new Contact("Joseph", "P", "1234567880");
		manage.addContact(c);
		assertTrue(manage.deleteContact(c.phoneNo));
	}

	@Test
	void testDeleteContactNotAdded() {
		assertFalse(manage.deleteContact("1100110010"));
	}

}
