package hexlet.code.controller;

import io.javalin.http.Context;

import java.util.Collections;

public class HomeController {

    public static void homePage(Context c) {
        c.sessionAttribute("incorrect url", null);
        c.render("layout/home.jte", Collections.singletonMap("c", c));
    }

}
