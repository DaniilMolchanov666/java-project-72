package hexlet.code;

import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class App {
    public static Javalin getApp() {

        Logger l = LoggerFactory.getLogger(App.class);
        l.info("hello");

        Javalin javalin = Javalin.create(config -> config.plugins.enableDevLogging());
        addRoutes(javalin);
        javalin.start(8000);
        return javalin;

    }
    public static void addRoutes(Javalin app) {
        app.get("/", ctx -> ctx.res().getWriter().println("Hello"));
    }
    public static void main(String[] args) {
        getApp();
    }
}
