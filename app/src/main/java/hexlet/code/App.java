package hexlet.code;

import io.javalin.Javalin;
import java.io.IOException;
import java.sql.SQLException;
public class App {

    private static int getPort() {
        String port = System.getenv().getOrDefault("PORT", "7070");
        return Integer.parseInt(port);
    }
    public static void main(String[] args) throws SQLException, IOException {
        var app = getApp();
        app.start(getPort());
    }

    public static Javalin getApp() throws IOException, SQLException {
        return Javalin.create(config -> {config.plugins.enableDevLogging();})
                .get("/", context -> context.result("Hello World!"));
    }
}
