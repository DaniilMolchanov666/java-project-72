package gg.jte.generated.ondemand.layout;
import gg.jte.Content;
import io.javalin.http.Context;;
public final class JtehomeGenerated {
	public static final String JTE_NAME = "layout/home.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,26,26,26,28,28,28,30,30,46,46,46,51,51,51,2,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, Context c, Content content) {
		jteOutput.writeContent("\n<html lang=\"ru\">\n<head>\n    <div class=\"p-3 mb-2 bg-dark text-white\">\n    <meta charset=\"utf-8\" />\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css\"\n          rel=\"stylesheet\"\n          integrity=\"sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We\"\n          crossorigin=\"anonymous\">\n    <title>Анализатор страниц</title>\n        <ul class=\"nav nav-underline\">\n            <li class=\"nav-item\">\n                <a class=\"nav-link active\" aria-current=\"page\" href=\"/\">Анализатор страниц</a>\n            </li>\n            <li class=\"nav-item\">\n                <a class=\"nav-link\" href=\"/\">Главная</a>\n            </li>\n            <li class=\"nav-item\">\n                <a class=\"nav-link\" href=\"/urls\">Сайты</a>\n            </li>\n        </ul>\n        ");
		if (c.sessionAttribute("incorrect url") != null) {
			jteOutput.writeContent("\n            <div class=\"alert alert-danger\" role=\"alert\">\n                ");
			jteOutput.setContext("div", null);
			jteOutput.writeUserContent(c.sessionAttribute("incorrect url").toString());
			jteOutput.writeContent("\n            </div>\n        ");
		}
		jteOutput.writeContent("\n    <h1> Анализатор страниц </h1>\n    <h6>Бесплатно проверяйте сайты на SEO пригодность</h6>\n        <form action=\"/urls\" method=\"post\">\n            <div>\n                <label>\n                    <p1>\n                    <input type=\"text\" name=\"url\" placeholder=\"Ссылка\" size=\"50\"/>\n                    <button type=\"submit\" class=\"btn btn-primary\">Проверить</button>\n                    </p1>\n                </label>\n            </div>\n        </form>\n    </div>\n    <body>\n    <p1>\n        ");
		jteOutput.setContext("p1", null);
		jteOutput.writeUserContent(content);
		jteOutput.writeContent("\n    </p1>\n    </body>\n</head>\n</html>\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		Context c = (Context)params.get("c");
		Content content = (Content)params.get("content");
		render(jteOutput, jteHtmlInterceptor, c, content);
	}
}
