package week3.appstore;

public class AppleAppStore extends AppStore {

    public AppleAppStore(Currency currency) {
        super("Apple App Store", currency);
    }

    @Override
    public void uploadApp(App app) {
        if (app.containsNudity()) {
            throw new IllegalArgumentException("Apple App Store policy forbids apps containing nudity: " + app.getName());
        }
        registerApp(app);
    }
}