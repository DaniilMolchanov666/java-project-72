package hexlet.code.model;

import java.util.ArrayList;
import java.util.List;

public final class Urls {
    private List<Url> urls;
    public Urls(List<Url> urls) {
        this.urls = urls;
    }
    public List<Url> getUrls() {
        return urls;
    }

    public void save(Url url) {
        urls.add(url);
    }
}
