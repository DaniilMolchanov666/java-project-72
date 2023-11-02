package hexlet.code;

import com.zaxxer.hikari.HikariDataSource;
import gg.jte.resolve.ResourceCodeResolver;
import hexlet.code.controller.HomeController;
import io.javalin.Javalin;
import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import io.javalin.rendering.template.JavalinJte;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class App {

    private static int getPort() {
        String port = System.getenv().getOrDefault("PORT", "8000");
        return Integer.parseInt(port);
    }
    public static void main(String[] args) throws SQLException, IOException {
        var app = getApp();
        app.start(getPort());
    }

    public static Javalin getApp() throws IOException, SQLException {

        HikariDataSource config = new HikariDataSource();

        config.setDataSourceClassName("org.h2.jdbcx.JdbcDataSource");
        config.addDataSourceProperty("URL", "jdbc:h2:./database");
        config.addDataSourceProperty("user", "sa");
        config.addDataSourceProperty("password", "sa");

        try (var connection = config.getConnection()) {
            var state = connection.createStatement();
            state.execute(Files.readString(Path.of("src/main/resources/schema.sql")));

            Timestamp time = Timestamp.valueOf(LocalDateTime.now());
            time.setNanos(0);
        }

        JavalinJte.init(createTemplateEngine());
        return Javalin.create(c -> {
            c.plugins.enableDevLogging();
        }).get("/", HomeController::homePage);
    }

    private static TemplateEngine createTemplateEngine() {
        ClassLoader classLoader = App.class.getClassLoader();
        ResourceCodeResolver codeResolver = new ResourceCodeResolver("templates", classLoader);
        return TemplateEngine.create(codeResolver, ContentType.Html);
    }
}
