package hexlet.code.controller;

import hexlet.code.dto.UrlChecks;
import hexlet.code.model.UrlCheck;
import hexlet.code.reporitory.UrlCheckRepository;
import hexlet.code.reporitory.UrlRepository;
import io.javalin.http.Context;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UrlChecksController {
    public static void showPageWithChecks(Context c) throws SQLException {
        long id = c.pathParamAsClass("id", Long.class).get();
        var url = UrlRepository.find(id);

        List<UrlCheck> listOfChecks = UrlCheckRepository.getChecksForUrl(url);
        Optional<String> flash = Optional.empty();

        var urlChecks = new UrlChecks(url, listOfChecks, flash);

        c.render("layout/urlPage.jte", Collections.singletonMap("urlChecks", urlChecks));
    }

    public static void checkUrl(Context c) throws SQLException {
        long id = c.pathParamAsClass("id", Long.class).get();
        var url = UrlRepository.find(id);

        try {
            UrlCheckRepository.saveCheck(url);
            c.sessionAttribute("flash url", "Проверка прошла успешно!");
        } catch (Exception e) {
            c.sessionAttribute("flash url", "Некорректный url!");
        }
        List<UrlCheck> listOfChecks = UrlCheckRepository.getChecksForUrl(url);

        var urlChecks = new UrlChecks(url, listOfChecks,
                Optional.ofNullable(c.consumeSessionAttribute("flash url")));

        c.render("layout/urlPage.jte", Collections.singletonMap("urlChecks", urlChecks));
    }
}
