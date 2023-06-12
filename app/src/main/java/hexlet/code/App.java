package hexlet.code;

import domain.Url;
import domain.query.QUrl;
import io.ebean.DB;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.InstantSource;

public final class App {

    public static Javalin getApp() {

        Javalin javalin = Javalin.create(config -> config.plugins.enableDevLogging());
        addRoutes(javalin);
        javalin.start(8000);
        return javalin;
    }
    public static void addRoutes(Javalin app) {
        app.get("/", ctx ->
        {
            ctx.res().getWriter().print("Hello");
            Url u = new Url("Hello", Instant.now());
            u.save();
        });
    }
    public static void main(String[] args) {
        Javalin app = getApp();
        app.start();
    }
}
