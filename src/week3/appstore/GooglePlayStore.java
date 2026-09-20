package week3.appstore;

public class GooglePlayStore extends AppStore {

    public GooglePlayStore(Currency currency) {
        super("Google Play Store", currency);
    }

    @Override
    public void uploadApp(App app) {
        registerApp(app);
    }
}