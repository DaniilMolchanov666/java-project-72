package hexlet.code.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
public class UrlCheck {

    private long id, urlId;

    private int statusCode;

    private String title, h1, description;

    private Timestamp createdAt;

    public UrlCheck(long urlId) {
        this.urlId = urlId;
    }

}
