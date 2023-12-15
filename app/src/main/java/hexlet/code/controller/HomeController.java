package hexlet.code.controller;

import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import hexlet.code.model.Urls;
import hexlet.code.reporitory.UrlCheckRepository;
import hexlet.code.reporitory.UrlRepository;
import io.javalin.http.Context;
import org.jsoup.helper.HttpConnection;

import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.UnresolvedAddressException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HomeController {

    //TODO Настроить правильно дату

    public static void homePage(Context c) {
        c.render("home.jte");
    }

    public static void savePage(Context c) throws MalformedURLException, SQLException, ParseException {
        String s = c.formParamAsClass("url", String.class).get();

        URL URL = new URL(s);

        String url = URL.getProtocol() + "://" + URL.getHost()
                + (URL.getPort() < 0 ? "": ":" + URL.getPort());

        var newUrl = new Url(url, new Timestamp(new Date().getTime()).toInstant());

        UrlRepository.save(newUrl);
        var urls = new Urls(UrlRepository.getUrlsFromDB());

        c.render("show.jte", Collections.singletonMap("urls", urls));
    }

    public static void showUrls(Context context) {
        var urls = new Urls(UrlRepository.getUrlsFromDB());
        context.render("show.jte", Collections.singletonMap("urls", urls));

    }

    public static void showPage(Context c) throws SQLException, IOException {
        long id = c.pathParamAsClass("id", Long.class).get();
        var url = UrlRepository.find(id);
        c.render("urlPage.jte", Collections.singletonMap("url", url));
    }

    public static void showChecks(Context c) throws SQLException, IOException {
        long id = c.pathParamAsClass("id", Long.class).get();
        var url = UrlRepository.find(id);
        UrlCheckRepository.saveCheck(url);
        List<UrlCheck> listOfChecks = UrlCheckRepository.getChecks(url);
        url.setListOfChecks(listOfChecks);
        c.render("urlPage.jte", Collections.singletonMap("url", url));
        url.setFlash("");
    }
}
