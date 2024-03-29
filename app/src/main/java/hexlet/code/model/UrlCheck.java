package hexlet.code.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.management.ConstructorParameters;
import java.sql.Timestamp;


@Getter
@Setter
@NoArgsConstructor
public class UrlCheck {

    private long id;

    private long urlId;

    private int statusCode;

    private String title;

    private String h1;

    private String description;

    private Timestamp createdAt;

    public UrlCheck(long urlId) {
        this.urlId = urlId;
    }

}
