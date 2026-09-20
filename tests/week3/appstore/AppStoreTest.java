package week3.appstore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AppStoreTest {
    private AppleAppStore appleStore;
    private GooglePlayStore googleStore;
    private App regularApp;
    private App violenceApp;
    private App nudityApp;

    @BeforeEach
    void setUp() {
        appleStore = new AppleAppStore(Currency.EUR);
        googleStore = new GooglePlayStore(Currency.USD);

        regularApp = new App("Calculator", 2.00, false, false);
        violenceApp = new App("Shooter", 10.00, true, false);
        nudityApp = new App("ArtGallery", 15.00, false, true);
    }

    @Test
    void testEmailValidationNullsIncorrectEmail() {
        User validUser = new User("Alice", "alice@test.com", LocalDate.of(2000, 1, 1));
        User invalidUser = new User("Bob", "invalid-email-address", LocalDate.of(2000, 1, 1));

        assertEquals("alice@test.com", validUser.getEmail());
        assertNull(invalidUser.getEmail());
    }

    @Test
    void testAppleStoreRejectsNudityApps() {
        assertThrows(IllegalArgumentException.class, () -> appleStore.uploadApp(nudityApp));
    }

    @Test
    void testGooglePlayAllowsNudityApps() {
        assertDoesNotThrow(() -> googleStore.uploadApp(nudityApp));
        assertTrue(googleStore.getApps().containsKey("artgallery"));
    }

    @Test
    void testUnderageUserCannotDownloadViolenceApp() {
        googleStore.uploadApp(violenceApp);
        User child = new User("Kid", "kid@test.com", LocalDate.now().minusYears(14));

        DownloadNotAllowedException ex = assertThrows(
                DownloadNotAllowedException.class,
                () -> googleStore.buyApp(child, "Shooter")
        );
        assertTrue(ex.getMessage().contains("requires minimum age 16"));
    }

    @Test
    void testUnderageUserCannotDownloadNudityApp() {
        googleStore.uploadApp(nudityApp);
        User teen = new User("Teen", "teen@test.com", LocalDate.now().minusYears(17));

        assertThrows(DownloadNotAllowedException.class, () -> googleStore.buyApp(teen, "ArtGallery"));
    }

    @Test
    void testEligibleUserCanBuyAppAndRevenueIsCalculated() throws DownloadNotAllowedException {
        appleStore.uploadApp(regularApp);
        User adult = new User("Charlie", "charlie@test.com", LocalDate.of(1995, 3, 10));

        appleStore.buyApp(adult, "Calculator");
        assertEquals(1, appleStore.getPurchases().size());

        // Price: 2.00, Store cut: 70% -> Revenue = 1.40
        assertEquals(1.40, appleStore.calculateTotalRevenue(), 0.001);
        assertEquals(1.40, appleStore.calculateAppRevenue("Calculator"), 0.001);
    }
}