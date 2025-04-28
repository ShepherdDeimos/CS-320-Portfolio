package contactservice;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ContactServiceTest {

    private ContactService service;
    private Contact contact;

    @Before
    public void setUp() {
        service = new ContactService();
        contact = new Contact("001", "Jane", "Smith", "0987654321", "456 Oak Lane");
        service.addContact(contact);
    }

    @Test
    public void testAddContactSuccess() {
        Contact newContact = new Contact("002", "Tom", "Jones", "1112223333", "789 Pine Road");
        service.addContact(newContact);
        assertEquals("Tom", service.getContactById("002").getFirstName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddDuplicateContactId() {
        service.addContact(contact); // Adding again
    }

    @Test
    public void testDeleteContact() {
        service.deleteContact("001");
        assertNull(service.getContactById("001"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteNonexistentContact() {
        service.deleteContact("999");
    }

    @Test
    public void testUpdateFields() {
        service.updateFirstName("001", "Janet");
        service.updateLastName("001", "Doe");
        service.updatePhone("001", "2223334444");
        service.updateAddress("001", "321 Birch Blvd");

        Contact updated = service.getContactById("001");
        assertEquals("Janet", updated.getFirstName());
        assertEquals("Doe", updated.getLastName());
        assertEquals("2223334444", updated.getPhone());
        assertEquals("321 Birch Blvd", updated.getAddress());
    }

    @Test
    public void testUpdateWithInvalidValues() {
        try {
            service.updatePhone("001", "123");
            fail("Should have thrown exception");
        } catch (IllegalArgumentException e) {}
    }
}
