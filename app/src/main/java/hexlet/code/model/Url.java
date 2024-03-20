package hexlet.code.model;

import lombok.Getter;
import lombok.Setter;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class Url {

    private long id;

    private String name;

    private Instant createdAt;

    private List<UrlCheck> listOfChecks;

    public Url(long id, String name, Instant createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }

    public Url(String name, Instant createdAt) {
        this.name = name;
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Url url = (Url) o;
        return Objects.equals(name, url.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
