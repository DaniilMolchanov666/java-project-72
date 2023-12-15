package gg.jte.generated.ondemand;
import hexlet.code.model.*;
import gg.jte.Content;
public final class JteshowGenerated {
	public static final String JTE_NAME = "show.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,29,29,29,35,35,36,36,38,38,38,39,39,39,39,39,39,39,40,40,40,42,42,47,47,47,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, Urls urls) {
		jteOutput.writeContent("\n<html lang=\"ru\">\n<head>\n    <div class=\"p-3 mb-2 bg-dark text-white\">\n        <meta charset=\"utf-8\" />\n        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css\"\n              rel=\"stylesheet\"\n              integrity=\"sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We\"\n              crossorigin=\"anonymous\">\n        <title>Анализатор страниц</title>\n    </div>\n</head>\n        <body>\n        <h1>\n            Сайты\n        </h1>\n        <table class=\"table\">\n            <thead>\n            <tr>\n                <th scope=\"col\">id</th>\n                <th scope=\"col\">Email</th>\n                <th scope=\"col\">created at</th>\n            </tr>\n            </thead>\n            <tbody>\n            ");
		if (urls.getUrls().isEmpty()) {
			jteOutput.writeContent("\n                <tr>\n                    <th scope=\"row\">  </th>\n                    <td>  </td>\n                    <td>  </td>\n                </tr>\n            ");
		}
		jteOutput.writeContent("\n            ");
		for (Url url: urls.getUrls()) {
			jteOutput.writeContent("\n                <tr>\n                    <th scope=\"row\"> ");
			jteOutput.setContext("th", null);
			jteOutput.writeUserContent(url.getId());
			jteOutput.writeContent(" </th>\n                    <td> <a href=\"/urls/");
			jteOutput.setContext("a", "href");
			jteOutput.writeUserContent(url.getId());
			jteOutput.setContext("a", null);
			jteOutput.writeContent("\">");
			jteOutput.setContext("a", null);
			jteOutput.writeUserContent(url.getName());
			jteOutput.writeContent("</a> </td>\n                    <td> ");
			jteOutput.setContext("td", null);
			jteOutput.writeUserContent(url.getCreatedAt().toString());
			jteOutput.writeContent(" </td>\n                </tr>\n            ");
		}
		jteOutput.writeContent("\n            </tbody>\n        </table>\n        </body>\n</html>\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		Urls urls = (Urls)params.get("urls");
		render(jteOutput, jteHtmlInterceptor, urls);
	}
}
