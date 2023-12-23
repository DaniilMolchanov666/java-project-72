package hexlet.code.controller;

import hexlet.code.model.Url;
import hexlet.code.dto.Urls;
import hexlet.code.reporitory.UrlRepository;
import hexlet.code.util.HomeRoutes;
import io.javalin.http.Context;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class UrlController {

    // TODO настроить формат вывода даты
    public static void savePage(Context c) throws MalformedURLException, SQLException {
        String s = c.formParamAsClass("url", String.class).get();
        URL URL;

        try {
            URL = new URL(s);
        } catch (MalformedURLException e) {
            c.sessionAttribute("incorrect url", "Некорректный адрес!");
            c.render("layout/home.jte", Collections.singletonMap("c", c));
            return;
        }

        String url = URL.getProtocol() + "://" + URL.getHost()
                + (URL.getPort() < 0 ? "": ":" + URL.getPort());

        var newUrl = new Url(url, new Timestamp(new Date().getTime()).toInstant());

        if (UrlRepository.getUrlsFromDB().isEmpty()) {
            c.sessionAttribute("flash", Optional.empty());
        }

        if (UrlRepository.getUrlsFromDB().contains(newUrl)) {
            c.sessionAttribute("flash", "Данная страница уже существует!");
        } else {
            c.sessionAttribute("flash", "Страница успешно создана!");
        }
//        var urls = new Urls(UrlRepository.getUrlsFromDB(), Optional.ofNullable(c.consumeSessionAttribute("flash")));
//        c.render("layout/show.jte", Collections.singletonMap("urls", urls));
        UrlRepository.save(newUrl);
        c.redirect(HomeRoutes.showAllUrls());
    }

    public static void showUrls(Context с) {
        List<Url> listOfUrls = UrlRepository.getUrlsFromDB();
        Optional<String> flashMessage = Optional.ofNullable(с.consumeSessionAttribute("flash"));

        var urls = new Urls(listOfUrls, flashMessage);

        с.render("layout/show.jte", Collections.singletonMap("urls", urls));
    }
}
