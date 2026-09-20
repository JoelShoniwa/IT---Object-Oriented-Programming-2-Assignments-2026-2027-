package week3.appstore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AppStore {
    // 30% ceded to developer means store keeps 70%
    protected static final double STORE_REVENUE_PERCENTAGE = 0.70;

    private final String name;
    private final Currency currency;
    private final Map<String, App> apps;
    private final List<Purchase> purchases;

    public AppStore(String name, Currency currency) {
        this.name = name;
        this.currency = currency;
        this.apps = new HashMap<>();
        this.purchases = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Map<String, App> getApps() {
        return Collections.unmodifiableMap(apps);
    }

    public List<Purchase> getPurchases() {
        return Collections.unmodifiableList(purchases);
    }

    public abstract void uploadApp(App app) throws IllegalArgumentException;

    protected void registerApp(App app) {
        apps.put(app.getName().toLowerCase(), app);
    }

    public void buyApp(User user, String appName) throws DownloadNotAllowedException {
        App app = apps.get(appName.toLowerCase());

        if (app == null) {
            throw new DownloadNotAllowedException("App '" + appName + "' is not available in " + name);
        }

        if (user.getAge() < app.getMinimumAge()) {
            throw new DownloadNotAllowedException("User " + user.getName() + " is " + user.getAge()
                    + " years old, but '" + app.getName() + "' requires minimum age " + app.getMinimumAge());
        }

        purchases.add(new Purchase(user, app));
    }

    public double calculateTotalRevenue() {
        double grossRevenue = 0.0;
        for (Purchase purchase : purchases) {
            grossRevenue += purchase.getPricePaid();
        }
        return Math.round(grossRevenue * STORE_REVENUE_PERCENTAGE * 100.0) / 100.0;
    }

    public double calculateAppRevenue(String appName) {
        double grossRevenue = 0.0;
        for (Purchase purchase : purchases) {
            if (purchase.getApp().getName().equalsIgnoreCase(appName)) {
                grossRevenue += purchase.getPricePaid();
            }
        }
        return Math.round(grossRevenue * STORE_REVENUE_PERCENTAGE * 100.0) / 100.0;
    }
}