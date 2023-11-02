package hexlet.code.controller;

import gg.jte.Content;
import io.javalin.http.Context;

public class HomeController {
    public static void homePage(Context c) {
        c.render("home.jte");
    }
}
