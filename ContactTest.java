package contactservice;

import org.junit.Test;
import static org.junit.Assert.*;

public class ContactTest {

    @Test
    public void testValidContactCreation() {
        Contact contact = new Contact("123", "John", "Doe", "1234567890", "123 Elm Street");
        assertEquals("123", contact.getContactId());
        assertEquals("John", contact.getFirstName());
        assertEquals("Doe", contact.getLastName());
        assertEquals("1234567890", contact.getPhone());
        assertEquals("123 Elm Street", contact.getAddress());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidContactIdTooLong() {
        new Contact("12345678901", "John", "Doe", "1234567890", "123 Elm Street");
    }

    @Test
    public void testSettersValidation() {
        Contact contact = new Contact("123", "John", "Doe", "1234567890", "123 Elm Street");

        try {
            contact.setFirstName(null);
            fail("Should have thrown exception");
        } catch (IllegalArgumentException e) {}

        try {
            contact.setPhone("123");
            fail("Should have thrown exception");
        } catch (IllegalArgumentException e) {}

        try {
            contact.setAddress("This address is way too long to be accepted.");
            fail("Should have thrown exception");
        } catch (IllegalArgumentException e) {}
    }
}
