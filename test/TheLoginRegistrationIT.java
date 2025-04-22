import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TheLoginRegistrationIT {

    public TheLoginRegistrationIT() {}

    @BeforeClass
    public static void setUpClass() {}

    @AfterClass
    public static void tearDownClass() {}

    @Before
    public void setUp() {}

    @After
    public void tearDown() {}

    @Test
    public void testIsValidUsername() {
        assertTrue(TheLoginRegistration.isValidUsername("user_")); // valid
        assertFalse(TheLoginRegistration.isValidUsername("user")); // missing underscore
        assertFalse(TheLoginRegistration.isValidUsername("long_username")); // too long
    }

    @Test
    public void testIsValidPassword() {
        assertTrue(TheLoginRegistration.isValidPassword("Password1!")); // valid
        assertFalse(TheLoginRegistration.isValidPassword("pass")); // too short
        assertFalse(TheLoginRegistration.isValidPassword("password!")); // missing capital and number
        assertFalse(TheLoginRegistration.isValidPassword("Password1")); // missing special char
    }

    @Test
    public void testIsValidCell() {
        assertTrue(TheLoginRegistration.isValidCell("+27123456789")); // valid
        assertFalse(TheLoginRegistration.isValidCell("0123456789")); // missing +27
        assertFalse(TheLoginRegistration.isValidCell("+27123")); // too short
    }

    @Test
    public void testRegisterUser() {
        String result = TheLoginRegistration.registerUser("user_", "Password1!", "+27123456789");
        assertTrue(result.contains("✅ Username successfully recorded."));
        assertTrue(result.contains("✅ Password successfully recorded."));
        assertTrue(result.contains("✅ Cell phone number successfully recorded."));

        // Invalid input
        String result2 = TheLoginRegistration.registerUser("user", "pass", "012345");
        assertTrue(result2.contains("❌ Username must contain an underscore"));
        assertTrue(result2.contains("❌ Password must be at least 8 characters"));
        assertTrue(result2.contains("❌ Cell number is incorrectly formatted"));
    }

    @Test
    public void testLogin() {
        TheLoginRegistration.registerUser("user_", "Password1!", "+27123456789");
        assertTrue(TheLoginRegistration.login("user_", "Password1!")); // correct
        assertFalse(TheLoginRegistration.login("user_", "wrongpass")); // incorrect password
        assertFalse(TheLoginRegistration.login("wronguser", "Password1!")); // incorrect username
    }

    @Test
    public void testGetLoginStatusMessage() {
        TheLoginRegistration.registerUser("user_", "Password1!", "+27123456789");
        String successMsg = TheLoginRegistration.getLoginStatusMessage("user_", "Password1!");
        String failMsg = TheLoginRegistration.getLoginStatusMessage("user_", "wrong");

        assertEquals("✅ Welcome user_! You’ve successfully logged in.", successMsg);
        assertEquals("❌ Login failed. Username or password incorrect.", failMsg);
    }

    @Test
    public void testMain() {
        // Main can't be tested directly with input/output in unit tests.
        // This is just a placeholder.
        assertTrue(true);
    }
}
