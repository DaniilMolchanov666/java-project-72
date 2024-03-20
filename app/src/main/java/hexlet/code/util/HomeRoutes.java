package hexlet.code.util;

public class HomeRoutes {
    public static String homePage() {
        return "/";
    }

    public static String showAllUrls() {
        return "/urls";
    }

    public static String currentUrl() {
        return "/urls/{id}";
    }

    public static String checkUrl() {
        return "/urls/{id}/checks";
    }
}
