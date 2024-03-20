import hexlet.code.App;
import hexlet.code.model.Url;
import hexlet.code.reporitory.UrlCheckRepository;
import hexlet.code.reporitory.UrlRepository;
import hexlet.code.util.HomeRoutes;
import io.javalin.Javalin;
import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.assertThat;

import io.javalin.testtools.JavalinTest;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;

public class TestUrl {

    Javalin app;

    @BeforeEach
    public void startServer() throws SQLException, IOException {
        app = App.getApp();
        UrlRepository.save(new Url("https://www.google.com", Instant.now()));
    }

    @Test
    public void testHomePage() throws InterruptedException, IOException {
        JavalinTest.test(app, (server, client) -> {
            var res = client.get(HomeRoutes.homePage());
            assertThat(res.isSuccessful());
        });
    }

    @Test
    public void urlAddTest() {
        JavalinTest.test(app, (server, client) -> {
            var res = client.post(HomeRoutes.showAllUrls(), "url=https://www.mail.com");
            assertThat(res.isSuccessful());
            assertThat(res.body().string().contains("https://www.mail.com"));
            assertThat(UrlRepository.find(1).getName().equals("https://www.mail.com"));
        });
    }

    @Test
    public void incorrectUrlAddTest() {
        JavalinTest.test(app, (server, client) -> {
            var res = client.post(HomeRoutes.showAllUrls(), "url=www.google.com");
            assertThat(res.body().string().contains("Некорректный адрес!"));
        });
    }

    @Test
    public void showAllUrlsTest() {
        JavalinTest.test(app, (server, client) -> {
            var res = client.get(HomeRoutes.showAllUrls());
            assertThat(res.isSuccessful());
        });
    }

    @Test
    public void addAlreadyExistingUrlTest() {
        JavalinTest.test(app, (server, client) -> {
            var url = new Url("url=https://www.google.com", Instant.now());
            client.post(HomeRoutes.showAllUrls(), url.getName());
            assertThat(!UrlRepository.getUrlsFromDB().contains(url));
        });
    }

    @Test
    public void urlPageCheckTest() {
        JavalinTest.test(app, (server, client) -> {
            var res = client.get("/urls/" + 1);
            assertThat(res.isSuccessful());
        });
    }

    @Test
    public void addUrlCheckTest() {
        JavalinTest.test(app, (server, client) -> {
            var res = client.post("/urls/" + 1 + "/checks", "url=https://www.google.com");
            var url = UrlRepository.find(1);
            assertThat(!UrlCheckRepository.getChecksForUrl(url).isEmpty());
        });
    }

    @Test
    public void addUrlCheckForIncorrectUrlTest() {
        JavalinTest.test(app, (server, client) -> {
            var incorrectUrl = new Url("https://www.e.com", Instant.now());
            UrlRepository.save(incorrectUrl);
            long id = incorrectUrl.getId();
            var res = client.post("/urls/" + id + "/checks");
            assertThat(res.body().string().contains("Некорректный url!"));
            assertThat(UrlCheckRepository.getChecksForUrl(incorrectUrl).isEmpty());
        });
    }

    @AfterEach
    public void after() {
        UrlRepository.getUrlsFromDB().clear();
        app.stop();
    }
}


