package hexlet.code;

import com.zaxxer.hikari.HikariDataSource;
import gg.jte.resolve.ResourceCodeResolver;
import hexlet.code.controller.HomeController;
import hexlet.code.controller.UrlChecksController;
import hexlet.code.controller.UrlController;
import hexlet.code.reporitory.UrlRepository;
import hexlet.code.util.HomeRoutes;
import io.javalin.Javalin;
import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import io.javalin.rendering.template.JavalinJte;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;

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

        UrlRepository.dataSource = config;

        try (var connection = config.getConnection()) {
            var state = connection.createStatement();
            state.execute(Files.readString(Path.of("src/main/resources/schema.sql")));
        }

        JavalinJte.init(createTemplateEngine());
        var app = Javalin.create(c -> {
            c.plugins.enableDevLogging();
        });

        app.get(HomeRoutes.homePage(), HomeController::homePage);
        app.post(HomeRoutes.showAllUrls(), UrlController::savePage);
        app.get(HomeRoutes.showAllUrls(), UrlController::showUrls);
        app.get(HomeRoutes.currentUrl(), UrlChecksController::showPageWithChecks);
        app.post(HomeRoutes.checkUrl(), UrlChecksController::checkUrl);

        return app;
    }

    private static TemplateEngine createTemplateEngine() {
        ClassLoader classLoader = App.class.getClassLoader();
        ResourceCodeResolver codeResolver = new ResourceCodeResolver("jte", classLoader);
        return TemplateEngine.create(codeResolver, ContentType.Html);
    }
}
